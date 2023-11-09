package com.homeboss.logic.commands.user;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;
import com.homeboss.testutil.TypicalDeliveries;
import com.homeboss.testutil.TypicalPersons;

public class UserLoginCommandTest {
    @Test
    public void execute_userAlreadyLoggedIn_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
                TypicalDeliveries.getTypicalDeliveryBook(),
                new UserPrefs(), true);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);
        UserLoginCommand userLoginCommand = new UserLoginCommand(user);

        CommandTestUtil.assertCommandFailure(userLoginCommand, model, UserLoginCommand.MESSAGE_ALREADY_LOGGED_IN);
    }

    @Test
    public void execute_userLogin_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
                TypicalDeliveries.getTypicalDeliveryBook(),
                new UserPrefs(), false);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);
        UserLoginCommand userLoginCommand = new UserLoginCommand(user);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), model.getUserLoginStatus());
        expectedModel.setLoggedInUser(user);
        expectedModel.setLoginSuccess();
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);

        assertCommandSuccess(userLoginCommand, model, UserLoginCommand.MESSAGE_SUCCESS,
                expectedModel, true);
    }

    @Test
    public void execute_userLoginWrongCredentials_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
                TypicalDeliveries.getTypicalDeliveryBook(),
                new UserPrefs(), false);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);

        User otherUser = new User(new Username("username"), new Password("password123"), false);
        UserLoginCommand userLoginCommand = new UserLoginCommand(otherUser);

        CommandTestUtil.assertCommandFailure(userLoginCommand, model, UserLoginCommand.MESSAGE_WRONG_CREDENTIALS);
    }

    @Test
    public void execute_userLoginButNoStoredUserFound_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
                TypicalDeliveries.getTypicalDeliveryBook(),
                new UserPrefs(), false);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(null);
        UserLoginCommand userLoginCommand = new UserLoginCommand(user);

        CommandTestUtil.assertCommandFailure(userLoginCommand, model,
                UserLoginCommand.MESSAGE_NO_REGISTERED_ACCOUNT_FOUND);
    }

    @Test
    public void equals() {
        User user = new User(new Username("username"), new Password("password"), false);
        UserLoginCommand userLoginCommand = new UserLoginCommand(user);

        // same object -> returns true
        assertTrue(userLoginCommand.equals(userLoginCommand));

        // same values -> returns true
        UserLoginCommand userLoginCommandCopy = new UserLoginCommand(user);
        assertTrue(userLoginCommand.equals(userLoginCommandCopy));

        // different types -> returns false
        assertFalse(userLoginCommand.equals(1));

        // null -> returns false
        assertFalse(userLoginCommand.equals(null));

        // different person -> returns false
        User user2 = new User(new Username("username2"), new Password("password2"), false);
        UserLoginCommand userLoginCommand2 = new UserLoginCommand(user2);
        assertFalse(userLoginCommand.equals(userLoginCommand2));
    }
}
