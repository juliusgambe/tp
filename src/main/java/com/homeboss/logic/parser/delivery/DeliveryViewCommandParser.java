package com.homeboss.logic.parser.delivery;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.logic.commands.delivery.DeliveryViewCommand;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;

/**
 * Parses input arguments and creates a new DeliveryViewCommand object
 */
public class DeliveryViewCommandParser implements Parser<DeliveryViewCommand> {

    private static final Pattern ARGUMENT_FORMAT = Pattern.compile(
        "^(?<id>\\d+)$"
    );

    @Override
    public DeliveryViewCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
        }

        final Matcher matcher = ARGUMENT_FORMAT.matcher(args.trim().toUpperCase());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
        }

        final String id = matcher.group("id");

        int deliveryId = ParserUtil.parseId(id);

        return new DeliveryViewCommand(deliveryId);
    }
}
