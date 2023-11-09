package com.homeboss.logic.commands.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.commands.ClearCommand;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.CustomerEditDescriptorBuilder;
import com.homeboss.testutil.TypicalIndexes;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.customer.CustomerEditCommand.CustomerEditDescriptor;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class CustomerEditCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
        getTypicalDeliveryBook(), new UserPrefs(), true);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = new CustomerBuilder().build();
        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder(editedCustomer).build();
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(CustomerEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            Messages.format(editedCustomer));

        System.out.println(editedCustomer);
        System.out.println(editCommand);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(), model.getUserLoginStatus());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedCustomer);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Customer lastCustomer = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        CustomerBuilder personInList = new CustomerBuilder(lastCustomer);
        Customer editedCustomer = personInList.withName(CommandTestUtil.VALID_NAME_BOB).withPhone(
            CommandTestUtil.VALID_PHONE_BOB).build();

        CustomerEditCommand.CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder()
            .withName(CommandTestUtil.VALID_NAME_BOB).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        CustomerEditCommand editCommand = new CustomerEditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(CustomerEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            Messages.format(editedCustomer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(), model.getUserLoginStatus());
        expectedModel.setPerson(lastCustomer, editedCustomer);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new CustomerEditDescriptor());
        Customer editedCustomer = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(CustomerEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            Messages.format(editedCustomer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(), model.getUserLoginStatus());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Customer customerInFilteredList = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        Customer editedCustomer = new CustomerBuilder(customerInFilteredList)
            .withCustomerId(customerInFilteredList.getCustomerId()).withName(CommandTestUtil.VALID_NAME_BOB).build();
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new CustomerEditDescriptorBuilder().withCustomerId(customerInFilteredList.getCustomerId())
                .withName(CommandTestUtil.VALID_NAME_BOB).build());


        String expectedMessage = String.format(CustomerEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            Messages.format(editedCustomer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(), model.getUserLoginStatus());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedCustomer);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Customer firstCustomer = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder(firstCustomer)
            .build();
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, CustomerEditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder()
            .withName(CommandTestUtil.VALID_NAME_BOB).build();
        CustomerEditCommand editCommand = new CustomerEditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListLoggedOut_failure() {
        // set state of model to be logged out
        model.setLogoutSuccess();
        Customer editedCustomer = new CustomerBuilder().build();
        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder(editedCustomer)
            .build();
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListLoggedOut_failure() {
        // set state of model to be logged out
        model.setLogoutSuccess();
        // customerId = 1
        Index indexLastPerson = Index.fromOneBased(1);

        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder()
            .withCustomerId(1).withName(CommandTestUtil.VALID_NAME_BOB)
            .withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        CustomerEditCommand editCommand = new CustomerEditCommand(indexLastPerson, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredListLoggedOut_failure() {
        // set state of model to be logged out
        model.setLogoutSuccess();
        CustomerEditCommand editCommand = new CustomerEditCommand(TypicalIndexes.INDEX_SECOND_PERSON,
            new CustomerEditCommand.CustomerEditDescriptor());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void equals() {
        final CustomerEditCommand standardCommand = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        CustomerEditDescriptor copyDescriptor = new CustomerEditDescriptor(CommandTestUtil.DESC_AMY);
        CustomerEditCommand commandWithSameValues = new CustomerEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new CustomerEditCommand(
            TypicalIndexes.INDEX_SECOND_PERSON, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new CustomerEditCommand(
            TypicalIndexes.INDEX_FIRST_PERSON, CommandTestUtil.DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        CustomerEditDescriptor customerEditDescriptor = new CustomerEditDescriptor();
        CustomerEditCommand editCommand = new CustomerEditCommand(index, customerEditDescriptor);
        String expected = CustomerEditCommand.class.getCanonicalName() + "{id=" + index
            + ", customerEditDescriptor=" + customerEditDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
