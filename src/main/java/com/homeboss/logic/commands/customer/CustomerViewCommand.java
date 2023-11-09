package com.homeboss.logic.commands.customer;

import java.util.Optional;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.person.Customer;

/**
 * Represents a CustomerViewCommand which displays a single customer.
 */
public class CustomerViewCommand extends CustomerCommand {

    public static final String COMMAND_WORD = CustomerCommand.COMMAND_WORD + " view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays a single customer.\n\n"
            + "Parameters: "
            + "CUSTOMER_ID\n\n"
            + "Example: " + COMMAND_WORD + " "
            + "1";

    public static final String MESSAGE_SUCCESS = "Customer displayed: %1$s";

    private final int customerId;

    /**
     * Creates a DeliveryViewCommand to display the specified {@code Delivery}.
     */
    public CustomerViewCommand(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(Messages.MESSAGE_USER_NOT_AUTHENTICATED);
        }

        Optional<Customer> customer = model.getCustomer(customerId);

        if (customer.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        return new CommandResult(Messages.format(customer.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerViewCommand // instanceof handles nulls
                && customerId == ((CustomerViewCommand) other).customerId); // state check
    }
}
