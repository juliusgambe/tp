package com.homeboss.logic.parser;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.logic.commands.delivery.DeliveryDeleteCommand;

/**
 * Parses input arguments and creates a new DeliveryDeleteCommand object
 */
public class DeliveryDeleteCommandParser implements Parser<DeliveryDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CustomerDeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeliveryDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
