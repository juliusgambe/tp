package com.homeboss.logic.commands.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.util.Optional;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.ClearCommand;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

public class CustomerViewCommandTest {

    private Model model = new ModelManager(
        TypicalPersons.getTypicalAddressBook(),
        getTypicalDeliveryBook(),
        new UserPrefs(),
        true
    );

    @Test
    public void execute_allFieldsValid_success() throws CommandException {
        CustomerViewCommand customerViewCommand = new CustomerViewCommand(1);
        Optional<Customer> customer = model.getCustomer(1);
        String expectedMessage = Messages.format(customer.get());

        assertCommandSuccess(customerViewCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidTargetId_throwsCommandException() {
        CustomerViewCommand customerViewCommand = new CustomerViewCommand(-1);
        CommandTestUtil.assertCommandFailure(customerViewCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_allFieldsValidLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        CustomerViewCommand customerViewCommand = new CustomerViewCommand(1);
        CommandTestUtil.assertCommandFailure(customerViewCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_invalidTargetIdLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        CustomerViewCommand customerViewCommand = new CustomerViewCommand(-1);
        CommandTestUtil.assertCommandFailure(customerViewCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void equals() {
        CustomerViewCommand customerViewCommand = new CustomerViewCommand(1);
        CustomerViewCommand customerViewCommandCopy = new CustomerViewCommand(1);
        CustomerViewCommand customerViewCommandDifferent = new CustomerViewCommand(2);

        // same object -> returns true
        assertTrue(customerViewCommand.equals(customerViewCommand));

        // null -> returns false
        assertFalse(customerViewCommand.equals(null));

        // different types -> returns false
        assertFalse(customerViewCommand.equals(new ClearCommand()));

        // same param -> returns true
        assertTrue(customerViewCommand.equals(customerViewCommandCopy));

        // different object -> returns false
        assertFalse(customerViewCommand.equals(customerViewCommandDifferent));

    }

}
