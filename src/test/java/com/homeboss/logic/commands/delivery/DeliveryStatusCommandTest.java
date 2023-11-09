package com.homeboss.logic.commands.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.GABRIELS_MILK;
import static com.homeboss.testutil.TypicalDeliveries.GAMBES_RICE;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.ClearCommand;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.DeliveryStatus;
import com.homeboss.testutil.DeliveryBuilder;

public class DeliveryStatusCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(), true);

    @Test
    public void execute_allFieldsValid_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.COMPLETED;
        Delivery expectedDelivery = new DeliveryBuilder(GABRIELS_MILK).withStatus(deliveryStatus).build();
        DeliveryStatusCommand deliveryStatusCommand =
            new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(), deliveryStatus);

        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_EDIT_DELIVERY_SUCCESS,
            Messages.format(expectedDelivery));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(),
            true);
        expectedModel.setDelivery(model.getDeliveryBook().getById(GABRIELS_MILK.getDeliveryId()).get(),
            expectedDelivery);

        CommandTestUtil.assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidTargetId_throwsCommandException() {
        DeliveryStatus deliveryStatus = DeliveryStatus.COMPLETED;
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(-1, deliveryStatus);
        CommandTestUtil.assertCommandFailure(deliveryStatusCommand, model, Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_allFieldsValidLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        DeliveryStatus deliveryStatus = DeliveryStatus.COMPLETED;
        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(), deliveryStatus);

        CommandTestUtil.assertCommandFailure(deliveryStatusCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_invalidTargetIdLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        DeliveryStatus deliveryStatus = DeliveryStatus.COMPLETED;
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(-1, deliveryStatus);
        CommandTestUtil.assertCommandFailure(deliveryStatusCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void equals() {
        final DeliveryStatusCommand standardCommand =
            new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(), DeliveryStatus.COMPLETED);

        // same values -> returns true
        DeliveryStatusCommand commandWithSameValue =
            new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(), DeliveryStatus.COMPLETED);
        assertTrue(standardCommand.equals(commandWithSameValue));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeliveryStatusCommand(GAMBES_RICE.getDeliveryId(),
            DeliveryStatus.COMPLETED)));

        // different status -> returns false
        assertFalse(standardCommand.equals(new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(),
            DeliveryStatus.CANCELLED)));
    }

    @Test
    public void toStringMethod() {
        String expected = DeliveryStatusCommand.class.getCanonicalName()
            + "{targetId=" + GABRIELS_MILK.getDeliveryId() + ", status="
            + DeliveryStatus.COMPLETED + "}";
        DeliveryStatusCommand deliveryStatusCommand =
            new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(), DeliveryStatus.COMPLETED);
        assertEquals(expected, deliveryStatusCommand.toString());
    }
}
