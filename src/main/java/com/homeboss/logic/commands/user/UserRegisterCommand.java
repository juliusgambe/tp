package com.homeboss.logic.commands.user;

import static com.homeboss.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static java.util.Objects.requireNonNull;

import com.homeboss.logic.commands.Command;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.model.Model;
import com.homeboss.model.user.User;

/**
 * Logs in the user and allows the user to access other functionalities.
 */
public class UserRegisterCommand extends Command {

    public static final String COMMAND_WORD = "register";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Register an account for HomeBoss.\n\n"
        + "Parameters: "
        + CliSyntax.PREFIX_USER + " USERNAME " + CliSyntax.PREFIX_PASSWORD + " PASSWORD "
        + CliSyntax.PREFIX_PASSWORD_CONFIRM
        + " CONFIRM_PASSWORD " + CliSyntax.PREFIX_SECRET_QUESTION + " SECRET_QUESTION " + CliSyntax.PREFIX_ANSWER
        + " ANSWER\n\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_USER + " yourUsername "
        + CliSyntax.PREFIX_PASSWORD + " yourPassword "
        + CliSyntax.PREFIX_PASSWORD_CONFIRM + " yourPassword "
        + CliSyntax.PREFIX_SECRET_QUESTION + " yourSecretQuestion "
        + CliSyntax.PREFIX_ANSWER + " yourAnswer";
    public static final String MESSAGE_SUCCESS = "Registration successful. Welcome to HomeBoss!";
    public static final String MESSAGE_PASSWORD_MISMATCH = "Passwords do not match. Please try again.";
    public static final String MESSAGE_ALREADY_HAVE_ACCOUNT = "You have an account already with username %s. ";
    public static final String MESSAGE_EMPTY_SECRET_QUESTION = "Secret question cannot be empty.";
    public static final String MESSAGE_EMPTY_ANSWER = "Answer cannot be empty.";

    private final User user;

    /**
     * Creates a UserRegisterCommand to log in the specified {@code User}
     */
    public UserRegisterCommand(User user) {
        requireNonNull(user);
        this.user = user;
    }

    /**
     * Executes the register user command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that indicates success.
     * @throws CommandException if the user is already logged in or the user credentials are wrong.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        User storedUser = model.getStoredUser();

        // Logged in user cannot register
        if (storedUser != null) {
            throw new CommandException(String.format(MESSAGE_ALREADY_HAVE_ACCOUNT, storedUser.getUsername()));
        }

        model.registerUser(this.user);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(MESSAGE_SUCCESS, true);
    }

    /**
     * Returns true if both users have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserRegisterCommand)) {
            return false;
        }

        UserRegisterCommand otherUserRegisterCommand = (UserRegisterCommand) other;
        return user.equals(otherUserRegisterCommand.user);
    }
}
