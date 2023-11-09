package com.homeboss.logic.parser.customer;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.customer.CustomerDeleteCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.testutil.TypicalIndexes;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take
 * the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class CustomerDeleteCommandParserTest {

    private CustomerDeleteCommandParser parser = new CustomerDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "0001", new CustomerDeleteCommand(
                TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomerDeleteCommand.MESSAGE_USAGE));
    }
}
