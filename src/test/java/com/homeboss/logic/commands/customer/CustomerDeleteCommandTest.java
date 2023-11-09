package com.homeboss.logic.commands.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.TypicalIndexes;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class CustomerDeleteCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(), true);

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToDelete = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        CustomerDeleteCommand deleteCommand = new CustomerDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CustomerDeleteCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
            Messages.format(customerToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
            new UserPrefs(), model.getUserLoginStatus());
        expectedModel.deletePerson(customerToDelete);
        expectedModel.deleteDeliveryByCustomer(customerToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CustomerDeleteCommand deleteCommand = new CustomerDeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Customer customerToDelete = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        CustomerDeleteCommand deleteCommand = new CustomerDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CustomerDeleteCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
            Messages.format(customerToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
            new UserPrefs(), model.getUserLoginStatus());
        expectedModel.deletePerson(customerToDelete);
        expectedModel.deleteDeliveryByCustomer(customerToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, true);
    }


    @Test
    public void equals() {
        CustomerDeleteCommand deleteFirstCommand = new CustomerDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        CustomerDeleteCommand deleteSecondCommand = new CustomerDeleteCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        CustomerDeleteCommand deleteFirstCommandCopy = new CustomerDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        CustomerDeleteCommand deleteCommand = new CustomerDeleteCommand(targetIndex);
        String expected = CustomerDeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
