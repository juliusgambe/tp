package com.homeboss.logic.commands.user;

import static java.util.Objects.requireNonNull;

import com.homeboss.logic.commands.Command;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.user.User;

/**
 * Deletes the user's account.
 * Available both when logged in and logged out.
 * If logged in, the user will be logged out after the account is deleted.
 */
public class UserDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete account";
    public static final String MESSAGE_SUCCESS = "User deleted successfully.";
    public static final String MESSAGE_NO_ACCOUNT = "No accounts found. Please register an account first.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        User storedUser = model.getStoredUser();

        // No user to delete
        if (storedUser == null) {
            throw new CommandException(MESSAGE_NO_ACCOUNT);
        }

        model.deleteUser();
        return new CommandResult(MESSAGE_SUCCESS, true);
    }

}
