package com.homeboss.logic.commands.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;

public class UserRegisterCommandTest {


    @Test
    public void execute_nullModel_throwsNullPointerException() {
        User user = new User(new Username("username"), new Password("password"), false);
        assertThrows(NullPointerException.class, () -> new UserRegisterCommand(user).execute(null));
    }

    @Test
    public void execute_userAlreadyLoggedIn_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), true);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);
        UserRegisterCommand userRegisterCommand = new UserRegisterCommand(user);

        CommandTestUtil.assertCommandFailure(userRegisterCommand, model,
                String.format(UserRegisterCommand.MESSAGE_ALREADY_HAVE_ACCOUNT, user.getUsername()));
    }

    @Test
    public void execute_storedUserExists_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), false);
        User user = new User(new Username("username"), new Password("password"), false);
        model.setLoggedInUser(user);
        UserRegisterCommand userRegisterCommand = new UserRegisterCommand(user);

        CommandTestUtil.assertCommandFailure(userRegisterCommand, model,
                String.format(UserRegisterCommand.MESSAGE_ALREADY_HAVE_ACCOUNT, user.getUsername()));
    }

    @Test // expect success when storedUser is null
    public void execute_storedUserDoesNotExist_success() {
        // Create a Model with an initial state where the user is not logged in and
        // there's no stored user.
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
                new UserPrefs(), false);

        // Create a User instance that you want to register

        User user = new User(new Username("username"), new Password("password"), false);

        // Create the UserRegisterCommand with the user
        UserRegisterCommand userRegisterCommand = new UserRegisterCommand(user);

        // Execute the command
        CommandResult result;

        model.setLoggedInUser(null);
        CommandTestUtil.assertCommandSuccess(userRegisterCommand, model, UserRegisterCommand.MESSAGE_SUCCESS, model, true);
    }

    @Test
    public void equals() {
        User user = new User(new Username("username"), new Password("password"), false);
        UserRegisterCommand userRegisterCommand = new UserRegisterCommand(user);

        // same object -> returns true
        assertTrue(userRegisterCommand.equals(userRegisterCommand));

        // same values -> returns true
        UserRegisterCommand userRegisterCommandCopy = new UserRegisterCommand(user);
        assertTrue(userRegisterCommand.equals(userRegisterCommandCopy));

        // different types -> returns false
        assertFalse(userRegisterCommand.equals(1));

        // null -> returns false
        assertFalse(userRegisterCommand.equals(null));

        // different person -> returns false
        User user2 = new User(new Username("username2"), new Password("password2"), false);
        UserRegisterCommand userRegisterCommand2 = new UserRegisterCommand(user2);
        assertFalse(userRegisterCommand.equals(userRegisterCommand2));
    }
}
