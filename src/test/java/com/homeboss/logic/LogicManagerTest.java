package com.homeboss.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.homeboss.logic.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static com.homeboss.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static com.homeboss.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.Assert;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.customer.CustomerAddCommand;
import com.homeboss.logic.commands.customer.CustomerListCommand;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.ReadOnlyBook;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.storage.JsonAddressBookStorage;
import com.homeboss.storage.JsonDeliveryBookStorage;
import com.homeboss.storage.JsonUserPrefsStorage;
import com.homeboss.storage.StorageManager;
import com.homeboss.testutil.CustomerBuilder;
import com.homeboss.ui.ListItem;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        model.setLoginSuccess();
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonDeliveryBookStorage deliveryBookStorage =
                new JsonDeliveryBookStorage(temporaryFolder.resolve("deliveryBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, deliveryBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "customer delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = CustomerListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, CustomerListCommand.MESSAGE_EMPTY, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), model.getUserLoginStatus());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        Assert.assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e               the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an AddressBookStorage that throws the IOException e when saving
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(prefPath) {
            @Override
            public void saveBook(ReadOnlyBook<Customer> addressBook, Path filePath) throws IOException {
                throw e;
            }
        };
        JsonDeliveryBookStorage deliveryBookStorage =
                new JsonDeliveryBookStorage(temporaryFolder.resolve("deliveryBook.json"));

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, deliveryBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveAddressBook method by executing an add command
        String addCommand = CustomerAddCommand.COMMAND_WORD + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
            + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY;

        Customer expectedCustomer = new CustomerBuilder(TypicalPersons.AMY)
            .withCustomerId(Customer.getCustomerCount()).build();


        ModelManager expectedModel = new ModelManager();
        // sets the expected model to be in logged in state
        expectedModel.setLoginSuccess();
        expectedModel.addPerson(expectedCustomer);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getUiListCustomer() {
        ObservableList<ListItem> customers = this.model.getFilteredDeliveryList().stream()
                .map(delivery -> new ListItem(String.format("[%d] %s", delivery.getDeliveryId(), delivery.getName()),
                        delivery.getOrderDate().toString(), delivery.getDeliveryDate().toString()))
                .collect(Collectors.toCollection(
                        FXCollections::observableArrayList));

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
        assertEquals(logic.getUiList(), customers);

        ObservableList<ListItem> deliveries = this.model.getFilteredDeliveryList().stream().map(
                        delivery -> new ListItem(String.format("[%d] %s", delivery.getDeliveryId(), delivery.getName()),
                                delivery.getOrderDate().toString(), delivery.getDeliveryDate().toString()))
                .collect(Collectors.toCollection(
                        FXCollections::observableArrayList));

        model.updateFilteredDeliveryList(Model.PREDICATE_SHOW_ALL_DELIVERIES);
        assertEquals(logic.getUiList(), deliveries);

        // sort
        model.sortFilteredDeliveryList(Comparator.comparing(Delivery::getName));

        assertEquals(logic.getUiList(), deliveries);
    }

    @Test
    public void getLoginStatus() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAuthenticationFilePath(temporaryFolder.resolve("authentication.json"));
        model.setUserPrefs(userPrefs);
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonDeliveryBookStorage deliveryBookStorage =
                new JsonDeliveryBookStorage(temporaryFolder.resolve("deliveryBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, deliveryBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        assertEquals(logic.getLoginStatus(), model.getLoginStatus());
    }
}
