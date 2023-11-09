package com.homeboss.logic.parser.delivery;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.homeboss.logic.commands.delivery.DeliveryStatusCommand;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.delivery.DeliveryStatus;

/**
 * Parses input arguments and creates a new DeliveryStatusCommand object
 */
public class DeliveryStatusCommandParser implements Parser<DeliveryStatusCommand> {

    private static final Pattern ARGUMENT_FORMAT = Pattern.compile(
        "^(?<id>\\d+)\\s+(?<status>\\w+)$"
    );

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryStatusCommand
     * and returns an DeliveryStatusCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryStatusCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryStatusCommand.MESSAGE_USAGE));
        }
        final Matcher matcher = ARGUMENT_FORMAT.matcher(args.trim().toUpperCase());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryStatusCommand.MESSAGE_USAGE));
        }

        final String status = matcher.group("status");
        final String id = matcher.group("id");

        DeliveryStatus deliveryStatus = ParserUtil.parseDeliveryStatus(status);

        int deliveryId = ParserUtil.parseId(id);

        return new DeliveryStatusCommand(deliveryId, deliveryStatus);
    }

}
