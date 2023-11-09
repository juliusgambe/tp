package com.homeboss.logic.commands.user;

import static com.homeboss.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static java.util.Objects.requireNonNull;

import com.homeboss.logic.commands.Command;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.model.Model;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;

/**
 * Recover the user account if they have forgotten their password.
 */
public class UserRecoverAccountCommand extends Command {

    public static final String COMMAND_WORD = "recover account";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Recover your account for HomeBoss.\n\n"
        + "Parameters: ["
        + CliSyntax.PREFIX_ANSWER + " ANSWER " + CliSyntax.PREFIX_PASSWORD + " NEW_PASSWORD "
        + CliSyntax.PREFIX_PASSWORD_CONFIRM
        + " NEW_PASSWORD]\n\n"
        + "Example 1: " + COMMAND_WORD + "\n\n"
        + "Example 2: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_ANSWER + " yourAnswer "
        + CliSyntax.PREFIX_PASSWORD + " yourNewPassword "
        + CliSyntax.PREFIX_PASSWORD_CONFIRM + " yourNewPassword";
    public static final String MESSAGE_SUCCESS_WITHOUT_FLAGS = "Your secret question is: %s";
    public static final String MESSAGE_SUCCESS_WITH_FLAGS = "Your account has been recovered successfully."
        + " Welcome back to HomeBoss.";
    public static final String MESSAGE_WRONG_ANSWER = "Wrong answer to secret question. Either try again"
        + " or delete account (permanent loss of stored data).";
    public static final String MESSAGE_PASSWORD_MISMATCH = "Passwords do not match. Please try again.";

    private boolean isShowSecretQuestion = true;
    private String answer;
    private Password newPassword;

    /**
     * Creates a UserRecoverAccountCommand to recover the specified {@code User}
     */
    public UserRecoverAccountCommand(String answer, Password newPassword) {
        isShowSecretQuestion = false;
        this.answer = answer;
        this.newPassword = newPassword;
    }

    /**
     * Creates a UserRecoverAccountCommand to show the secret question of the specified {@code User}
     */
    public UserRecoverAccountCommand() {
        // empty constructor
    }

    /**
     * Executes the recover account command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that indicates success.
     * @throws CommandException if the user is already logged in or the user credentials are wrong.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // ensure stored user is not null
        User storedUser = model.getStoredUser();

        if (storedUser == null) {
            throw new CommandException(UserDeleteCommand.MESSAGE_NO_ACCOUNT);
        }

        if (isShowSecretQuestion) {
            String output = String.format(MESSAGE_SUCCESS_WITHOUT_FLAGS, storedUser.getSecretQuestion());
            output += "\n\n" + "Please answer the question using the following command:\n"
                + UserRecoverAccountCommand.MESSAGE_USAGE;
            return new CommandResult(output);
        }

        if (!storedUser.getAnswer().equalsIgnoreCase(answer)) {
            throw new CommandException(MESSAGE_WRONG_ANSWER);
        }

        // answer is correct
        // reset password
        User newUser = new User(storedUser.getUsername(), newPassword, true,
            storedUser.getSecretQuestion(), storedUser.getAnswer());
        model.resetPassword(newUser);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(MESSAGE_SUCCESS_WITH_FLAGS, true);
    }

    public boolean isShowSecretQuestion() {
        return isShowSecretQuestion;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UserRecoverAccountCommand // instanceof handles nulls
            && isShowSecretQuestion == ((UserRecoverAccountCommand) other).isShowSecretQuestion
            && answer.equals(((UserRecoverAccountCommand) other).answer)
            && newPassword.equals(((UserRecoverAccountCommand) other).newPassword));
    }
}
