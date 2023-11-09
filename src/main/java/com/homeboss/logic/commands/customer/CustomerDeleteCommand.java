package com.homeboss.logic.commands.customer;

import static java.util.Objects.requireNonNull;

import com.homeboss.commons.core.index.Index;
import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.person.Customer;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class CustomerDeleteCommand extends CustomerCommand {

    public static final String COMMAND_WORD = CustomerCommand.COMMAND_WORD + " " + "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the customer ID used in the displayed customer list.\n\n"
            + "Parameters: CUSTOMER_ID (must be a positive integer)\n\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer:\n\n%1$s";

    private final Index targetIndex;

    public CustomerDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(Messages.MESSAGE_USER_NOT_AUTHENTICATED);
        }

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);

        Customer customerToDelete = model.getCustomerUsingFilteredList(targetIndex.getOneBased());

        if (customerToDelete != null) {
            model.deletePerson(customerToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS,
                    Messages.format(customerToDelete)), true);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerDeleteCommand)) {
            return false;
        }

        CustomerDeleteCommand otherDeleteCommand = (CustomerDeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
