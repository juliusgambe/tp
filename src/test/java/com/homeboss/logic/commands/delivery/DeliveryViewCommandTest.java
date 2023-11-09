package com.homeboss.logic.commands.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.util.Optional;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;

class DeliveryViewCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(), true);

    @Test
    public void execute_allFieldsValid_success() throws CommandException {
        DeliveryViewCommand deliveryViewCommand = new DeliveryViewCommand(1);
        Optional<Delivery> delivery = model.getDelivery(1);
        String expectedMessage = Messages.format(delivery.get());

        assertCommandSuccess(deliveryViewCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidTargetId_throwsCommandException() {
        DeliveryViewCommand deliveryViewCommand = new DeliveryViewCommand(-1);
        CommandTestUtil.assertCommandFailure(deliveryViewCommand, model, Messages.MESSAGE_INVALID_DELIVERY_ID);
    }

    @Test
    public void execute_allFieldsValidLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        DeliveryViewCommand deliveryViewCommand = new DeliveryViewCommand(1);
        CommandTestUtil.assertCommandFailure(deliveryViewCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_invalidTargetIdLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        DeliveryViewCommand deliveryViewCommand = new DeliveryViewCommand(-1);
        CommandTestUtil.assertCommandFailure(deliveryViewCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void equals() {
        DeliveryViewCommand deliveryViewCommand = new DeliveryViewCommand(1);
        DeliveryViewCommand deliveryViewCommandCopy = new DeliveryViewCommand(1);
        DeliveryViewCommand deliveryViewCommandDifferent = new DeliveryViewCommand(2);

        // same object -> returns true
        assertTrue(deliveryViewCommand.equals(deliveryViewCommandCopy));

        // different object -> returns false
        assertFalse(deliveryViewCommand.equals(deliveryViewCommandDifferent));

    }
}
