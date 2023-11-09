package com.homeboss.logic.commands.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.AddressBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.homeboss.logic.commands.customer.CustomerEditCommand;
import com.homeboss.logic.commands.customer.CustomerEditCommand.CustomerEditDescriptor;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.user.User;
import com.homeboss.testutil.CustomerBuilder;
import com.homeboss.testutil.CustomerEditDescriptorBuilder;
import com.homeboss.testutil.UpdateUserDescriptorBuilder;
import com.homeboss.testutil.UserBuilder;

public class UserUpdateCommandTest {

    @TempDir
    public Path tempDir;
    private Model model = new ModelManager(
        TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(), true);
    private final Logger logger = Logger.getLogger(UserUpdateCommandTest.class.getName());

    @BeforeEach
    public void setUp() {
        Path source = Paths.get("src/test/data/Authentication", "authentication.json");
        Path target = tempDir.resolve("tempAuthentication.json");
        // copy the authentication file to the temp directory
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            logger.info("Error copying authentication.json to tempDir");
        }

        UserPrefs tempPrefs = new UserPrefs();
        tempPrefs.setAuthenticationFilePath(target);

        model.setUserPrefs(tempPrefs); // should store user based on the authentication file in tempDir

    }

    @Test
    public void execute_allFieldsSpecified_success() {

        User updatedUser = model.getStoredUser();
        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder(updatedUser).build();
        UserUpdateCommand updateCommand = new UserUpdateCommand(descriptor);

        String expectedMessage = UserUpdateCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new DeliveryBook(model.getDeliveryBook()),
                new UserPrefs(model.getUserPrefs()), model.getUserLoginStatus());
        expectedModel.setLoggedInUser(updatedUser);

        CommandTestUtil.assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        User updatedUser = new UserBuilder().withUsername(CommandTestUtil.VALID_USERNAME_FOODBEAR)
                .withSecretQuestion(CommandTestUtil.VALID_SECRET_QUESTION_FOODBEAR).withAnswer(
                CommandTestUtil.VALID_ANSWER_FOODBEAR).build();
        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder(updatedUser).build();
        UserUpdateCommand updateCommand = new UserUpdateCommand(descriptor);

        String expectedMessage = UserUpdateCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new DeliveryBook(model.getDeliveryBook()),
                new UserPrefs(model.getUserPrefs()), model.getUserLoginStatus());
        expectedModel.setLoggedInUser(updatedUser);

        CommandTestUtil.assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        // succeed because the check is done by the parser in the previous step
        User updatedUser = new UserBuilder().build();
        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder().build();
        UserUpdateCommand updateCommand = new UserUpdateCommand(descriptor);

        String expectedMessage = UserUpdateCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new DeliveryBook(model.getDeliveryBook()),
                new UserPrefs(model.getUserPrefs()), model.getUserLoginStatus());
        expectedModel.setLoggedInUser(updatedUser);

        CommandTestUtil.assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_allFieldsSpecifiedButLoggedOut_failure() {
        // set state of model to be logged out
        model.setLogoutSuccess();

        Customer editedCustomer = new CustomerBuilder().build();
        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder(editedCustomer).build();
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_someFieldsSpecifiedButLoggedOut_failure() {
        // set state of model to be logged out
        model.setLogoutSuccess();
        User updatedUser = new UserBuilder().build();
        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder(updatedUser).build();
        UserUpdateCommand updateCommand = new UserUpdateCommand(descriptor);

        CommandTestUtil.assertCommandFailure(updateCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void equals() {
        final UserUpdateCommand standardCommand = new UserUpdateCommand(CommandTestUtil.DESC_AARON);

        // same values -> returns true
        UserUpdateCommand.UserUpdateDescriptor copyDescriptor = new UserUpdateCommand.UserUpdateDescriptor(
            CommandTestUtil.DESC_AARON);
        UserUpdateCommand commandWithSameValues = new UserUpdateCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new UserLogoutCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UserUpdateCommand(CommandTestUtil.DESC_FOODBEAR)));
    }

    @Test
    public void toStringMethod() {
        UserUpdateCommand.UserUpdateDescriptor userUpdateDescriptor = new UserUpdateCommand.UserUpdateDescriptor();
        UserUpdateCommand updateCommand = new UserUpdateCommand(userUpdateDescriptor);
        String expected = UserUpdateCommand.class.getCanonicalName() + "{updateUserDescriptor="
                + userUpdateDescriptor + "}";
        assertEquals(expected, updateCommand.toString());
    }
}
