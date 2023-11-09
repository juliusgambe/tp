package com.homeboss.logic.parser.customer;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import com.homeboss.logic.parser.CommandParserTestUtil;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.customer.CustomerFindCommand;
import com.homeboss.model.person.NameContainsKeywordsPredicate;

public class CustomerFindCommandParserTest {

    private CustomerFindCommandParser parser = new CustomerFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomerFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        CustomerFindCommand expectedCustomerFindCommand =
                new CustomerFindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedCustomerFindCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedCustomerFindCommand);
    }

}
