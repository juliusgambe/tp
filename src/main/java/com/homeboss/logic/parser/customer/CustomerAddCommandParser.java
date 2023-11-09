package com.homeboss.logic.parser.customer;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_NAME;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import com.homeboss.logic.commands.customer.CustomerAddCommand;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.Prefix;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.person.Address;
import com.homeboss.model.person.Customer;
import com.homeboss.model.person.Email;
import com.homeboss.model.person.Name;
import com.homeboss.model.person.Phone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CustomerAddCommandParser implements Parser<CustomerAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomerAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomerAddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Customer customer = new Customer(name, phone, email, address);

        return new CustomerAddCommand(customer);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
