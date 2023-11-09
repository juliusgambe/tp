package com.homeboss.logic.commands;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.delivery.DeliveryDeleteCommand;
import com.homeboss.logic.commands.delivery.DeliveryListCommand;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.DeliveryStatus;
import com.homeboss.testutil.TypicalIndexes;
import com.homeboss.testutil.TypicalPersons;

public class DeliveryDeleteCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
            new UserPrefs(), true);

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Delivery deliveryToDelete = model.getFilteredDeliveryList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        DeliveryDeleteCommand deleteCommand = new DeliveryDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeliveryDeleteCommand.MESSAGE_DELETE_DELIVERY_SUCCESS,
                Messages.format(deliveryToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), true);
        expectedModel.deleteDelivery(deliveryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_equals() {

        DeliveryDeleteCommand deleteCommand = new DeliveryDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        DeliveryDeleteCommand otherDeleteCommand = new DeliveryDeleteCommand(TypicalIndexes.INDEX_SECOND_PERSON);
        DeliveryListCommand deliveryListCommand = new DeliveryListCommand(DeliveryStatus.CREATED, null,
                null, null);

        assertTrue(deleteCommand.equals(deleteCommand));
        assertFalse(deleteCommand.equals(deliveryListCommand));
        assertFalse(deleteCommand.equals(otherDeleteCommand));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeliveryList().size() + 1000);
        DeliveryDeleteCommand deleteCommand = new DeliveryDeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showDeliveryAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Delivery deliveryToDelete = model.getFilteredDeliveryList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        DeliveryDeleteCommand deleteCommand = new DeliveryDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeliveryDeleteCommand.MESSAGE_DELETE_DELIVERY_SUCCESS,
                Messages.format(deliveryToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), true);
        expectedModel.deleteDelivery(deliveryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_validIndexUnfilteredListLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        DeliveryDeleteCommand deleteCommand = new DeliveryDeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDelivery(Model model) {
        model.updateFilteredDeliveryList(p -> false);

        assertTrue(model.getFilteredDeliveryList().isEmpty());
    }
}
