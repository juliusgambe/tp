package com.homeboss.logic.commands.user;

import static com.homeboss.model.Model.PREDICATE_SHOW_NO_CUSTOMERS;
import static com.homeboss.model.Model.PREDICATE_SHOW_NO_DELIVERIES;
import static java.util.Objects.requireNonNull;

import com.homeboss.logic.commands.Command;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;

/**
 * Logs out the user.
 */
public class UserLogoutCommand extends Command {

    public static final String COMMAND_WORD = "logout";
    public static final String MESSAGE_SUCCESS = "Bye!";
    public static final String MESSAGE_ALREADY_LOGGED_OUT = "You are already logged out!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Logged out user cannot log out again
        if (!model.getUserLoginStatus()) {
            throw new CommandException(MESSAGE_ALREADY_LOGGED_OUT);
        }

        // Set status in model to logged out, and update filtered lists
        model.setLogoutSuccess();
        model.updateFilteredPersonList(PREDICATE_SHOW_NO_CUSTOMERS);
        model.updateFilteredDeliveryList(PREDICATE_SHOW_NO_DELIVERIES);
        // Display the updated empty list
        model.setUiListCustomer();
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
