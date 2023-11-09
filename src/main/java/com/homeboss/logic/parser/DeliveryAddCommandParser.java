package com.homeboss.logic.parser;


import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import com.homeboss.logic.commands.delivery.DeliveryAddCommand;
import com.homeboss.logic.commands.delivery.DeliveryAddCommand.DeliveryAddDescriptor;
import com.homeboss.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeliveryAddCommand object
 */
public class DeliveryAddCommandParser implements Parser<DeliveryAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryAddCommand
     * and returns a DeliveryAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryAddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_CUSTOMER_ID, CliSyntax.PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_CUSTOMER_ID, CliSyntax.PREFIX_DATE)
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryAddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_CUSTOMER_ID, CliSyntax.PREFIX_DATE);

        DeliveryAddDescriptor deliveryAddDescriptor = new DeliveryAddDescriptor();

        if (!argMultimap.getPreamble().isEmpty()) {
            deliveryAddDescriptor.setDeliveryName(ParserUtil
                .parseDeliveryName(argMultimap.getPreamble()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_CUSTOMER_ID).isPresent()) {
            deliveryAddDescriptor.setCustomerId(ParserUtil
                .parseId(argMultimap.getValue(CliSyntax.PREFIX_CUSTOMER_ID).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            deliveryAddDescriptor.setDeliveryDate(ParserUtil
                .parseDeliveryDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get()));
        }

        return new DeliveryAddCommand(deliveryAddDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
