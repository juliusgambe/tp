package com.homeboss.logic.parser.customer;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.commands.customer.CustomerDeleteCommand;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomerDeleteCommand object
 */
public class CustomerDeleteCommandParser implements Parser<CustomerDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CustomerDeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CustomerDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CustomerDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomerDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
