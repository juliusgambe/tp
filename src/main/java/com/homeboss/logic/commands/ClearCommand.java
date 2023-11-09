package com.homeboss.logic.commands;

import static java.util.Objects.requireNonNull;
import static com.homeboss.logic.Messages.MESSAGE_USER_NOT_AUTHENTICATED;
import static com.homeboss.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Database has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(MESSAGE_USER_NOT_AUTHENTICATED);
        }

        model.setAddressBook(new AddressBook());
        model.setDeliveryBook(new DeliveryBook());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
