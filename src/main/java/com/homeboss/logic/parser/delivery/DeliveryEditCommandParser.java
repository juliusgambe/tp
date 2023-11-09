package com.homeboss.logic.parser.delivery;

import static java.util.Objects.requireNonNull;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.delivery.DeliveryEditCommand;
import com.homeboss.logic.parser.*;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;

/**
 * Parses input arguments and creates a new DeliveryEditCommand object
 */
public class DeliveryEditCommandParser implements Parser<DeliveryEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryEditCommand
     * and returns an DeliveryEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryEditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_CUSTOMER_ID,
                        CliSyntax.PREFIX_DATE, CliSyntax.PREFIX_STATUS, CliSyntax.PREFIX_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeliveryEditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_CUSTOMER_ID,
                CliSyntax.PREFIX_DATE, CliSyntax.PREFIX_STATUS, CliSyntax.PREFIX_NOTE);

        DeliveryEditCommand.DeliveryEditDescriptor deliveryEditDescriptor =
                new DeliveryEditCommand.DeliveryEditDescriptor();

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            deliveryEditDescriptor.setDeliveryName(ParserUtil
                    .parseDeliveryName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_CUSTOMER_ID).isPresent()) {
            deliveryEditDescriptor.setCustomerId(ParserUtil.parseId(argMultimap.getValue(CliSyntax.PREFIX_CUSTOMER_ID).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            deliveryEditDescriptor.setDeliveryDate(ParserUtil
                    .parseDeliveryDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_STATUS).isPresent()) {
            deliveryEditDescriptor.setStatus(ParserUtil
                    .parseDeliveryStatus(argMultimap.getValue(CliSyntax.PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_NOTE).isPresent()) {
            deliveryEditDescriptor.setNote(ParserUtil
                    .parseNote(argMultimap.getValue(CliSyntax.PREFIX_NOTE).get()));
        }

        if (!deliveryEditDescriptor.isAnyFieldEdited()) {
            throw new ParseException(DeliveryEditCommand.MESSAGE_NOT_EDITED);
        }

        return new DeliveryEditCommand(index, deliveryEditDescriptor);
    }
}
