package com.homeboss.logic.commands.customer;

import static java.util.Objects.requireNonNull;
import static com.homeboss.logic.Messages.MESSAGE_USER_NOT_AUTHENTICATED;

import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.model.person.Customer;
import com.homeboss.logic.Messages;
import com.homeboss.model.Model;

/**
 * Adds a person to the address book.
 */
public class CustomerAddCommand extends CustomerCommand {

    public static final String COMMAND_WORD = CustomerCommand.COMMAND_WORD + " " + "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to the HomeBoss database."
        + "\n\nParameters: "
        + CliSyntax.PREFIX_NAME + " NAME "
        + CliSyntax.PREFIX_PHONE + " PHONE "
        + CliSyntax.PREFIX_EMAIL + " EMAIL "
        + CliSyntax.PREFIX_ADDRESS + " ADDRESS\n\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_NAME + " John Doe "
        + CliSyntax.PREFIX_PHONE + " 98765432 "
        + CliSyntax.PREFIX_EMAIL + " johnd@example.com "
        + CliSyntax.PREFIX_ADDRESS + " 311, Clementi Ave 2, #02-25";

    public static final String MESSAGE_SUCCESS = "New customer added:\n\n%1$s";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in HomeBoss";

    private final Customer toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Customer}
     */
    public CustomerAddCommand(Customer customer) {
        requireNonNull(customer);
        toAdd = customer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            // reset the customer count to the previous value
            Customer.setCustomerCount(toAdd.getCustomerId() - 1);
            throw new CommandException(MESSAGE_USER_NOT_AUTHENTICATED);
        }

        if (model.hasPerson(toAdd)) {
            // reset the customer count to the previous value
            Customer.setCustomerCount(toAdd.getCustomerId() - 1);
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)), true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerAddCommand)) {
            return false;
        }

        CustomerAddCommand otherCustomerAddCommand = (CustomerAddCommand) other;
        return toAdd.equals(otherCustomerAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("toAdd", toAdd)
            .toString();
    }
}
