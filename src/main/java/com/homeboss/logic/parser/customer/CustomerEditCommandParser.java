package com.homeboss.logic.parser.customer;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_NAME;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.commands.customer.CustomerEditCommand;
import com.homeboss.logic.commands.customer.CustomerEditCommand.CustomerEditDescriptor;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomerEditCommand object
 */
public class CustomerEditCommandParser implements Parser<CustomerEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CustomerEditCommand
     * and returns an CustomerEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomerEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CustomerEditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        CustomerEditDescriptor customerEditDescriptor = new CustomerEditDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            customerEditDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            customerEditDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            customerEditDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            customerEditDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (!customerEditDescriptor.isAnyFieldEdited()) {
            throw new ParseException(CustomerEditCommand.MESSAGE_NOT_EDITED);
        }

        return new CustomerEditCommand(index, customerEditDescriptor);
    }
}
