package com.homeboss.logic.commands.user;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.model.Model.PREDICATE_SHOW_NO_CUSTOMERS;
import static com.homeboss.model.Model.PREDICATE_SHOW_NO_DELIVERIES;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;
import static com.homeboss.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;

public class UserLogoutCommandTest {
    @Test
    public void execute_userAlreadyLoggedOut_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), false);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);
        UserLogoutCommand userLogoutCommand = new UserLogoutCommand();

        CommandTestUtil.assertCommandFailure(userLogoutCommand, model, UserLogoutCommand.MESSAGE_ALREADY_LOGGED_OUT);
    }

    @Test
    public void execute_userLogout_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), true);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);
        UserLogoutCommand userLogoutCommand = new UserLogoutCommand();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), model.getUserLoginStatus());
        expectedModel.setLoggedInUser(user);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_NO_CUSTOMERS);
        expectedModel.updateFilteredDeliveryList(PREDICATE_SHOW_NO_DELIVERIES);
        expectedModel.setUiListCustomer();
        expectedModel.setLogoutSuccess();

        assertCommandSuccess(userLogoutCommand, model, UserLogoutCommand.MESSAGE_SUCCESS, expectedModel,
                true);
    }
}
