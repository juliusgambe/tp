package com.homeboss.logic.commands.customer;

import static java.util.Objects.requireNonNull;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;

/**
 * Lists all customers in the address book to the user.
 */
public class CustomerListCommand extends CustomerCommand {

    public static final String COMMAND_WORD = CustomerCommand.COMMAND_WORD + " " + "list";
    public static final String MESSAGE_SUCCESS = "Listed Customers";
    public static final String MESSAGE_EMPTY = "There are currently no customers to be listed.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(Messages.MESSAGE_USER_NOT_AUTHENTICATED);
        }

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);

        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(MESSAGE_EMPTY, true);
        }

        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
