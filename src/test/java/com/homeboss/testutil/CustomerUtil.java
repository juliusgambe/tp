package com.homeboss.testutil;

import com.homeboss.logic.commands.customer.CustomerAddCommand;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.model.person.Customer;
import com.homeboss.logic.commands.customer.CustomerEditCommand.CustomerEditDescriptor;

/**
 * A utility class for Person.
 */
public class CustomerUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Customer customer) {
        return CustomerAddCommand.COMMAND_WORD + " " + getPersonDetails(customer);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Customer customer) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + customer.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + customer.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_EMAIL + customer.getEmail().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + customer.getAddress().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code CustomerEditDescriptor}'s details.
     */

    public static String getEditPersonDescriptorDetails(CustomerEditDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(CliSyntax.PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(CliSyntax.PREFIX_ADDRESS).append(address.value).append(" "));
        return sb.toString();
    }
}
