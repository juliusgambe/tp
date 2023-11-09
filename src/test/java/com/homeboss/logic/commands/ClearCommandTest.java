package com.homeboss.logic.commands;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;
import static com.homeboss.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.setLoginSuccess();
        expectedModel.setLoginSuccess();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, true);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), true);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), true);
        expectedModel.setAddressBook(new AddressBook());
        expectedModel.setDeliveryBook(new DeliveryBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, true);
    }

    @Test
    public void execute_emptyAddressBookLoggedOut_failure() {
        Model model = new ModelManager();
        model.setLogoutSuccess();

        assertCommandFailure(new ClearCommand(), model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_nonEmptyAddressBookLoggedOut_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), false);

        assertCommandFailure(new ClearCommand(), model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

}
