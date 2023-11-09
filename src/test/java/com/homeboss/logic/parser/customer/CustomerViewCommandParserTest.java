package com.homeboss.logic.parser.customer;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.customer.CustomerViewCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;

public class CustomerViewCommandParserTest {

    private CustomerViewCommandParser parser = new CustomerViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        CustomerViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCustomerViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.VALID_VIEW_CUSTOMER_ID_1,
                new CustomerViewCommand(1));
    }

    @Test
    public void parse_validArgsExtraSpaceBeforeAfter_returnsCustomerViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, " " + CommandTestUtil.VALID_VIEW_CUSTOMER_ID_1 + " ",
                new CustomerViewCommand(1));
    }

    @Test
    public void parse_invalidArgsNegative_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "-1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        CustomerViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsNaN_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        CustomerViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsExtraNumber_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "1 1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        CustomerViewCommand.MESSAGE_USAGE));
    }
}
