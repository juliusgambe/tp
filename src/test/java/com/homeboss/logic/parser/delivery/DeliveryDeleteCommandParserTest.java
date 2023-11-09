package com.homeboss.logic.parser.delivery;

import com.homeboss.logic.Messages;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.logic.parser.DeliveryDeleteCommandParser;
import com.homeboss.testutil.TypicalIndexes;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.delivery.DeliveryDeleteCommand;

public class DeliveryDeleteCommandParserTest {
    private DeliveryDeleteCommandParser parser = new DeliveryDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "0001", new DeliveryDeleteCommand(
            TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeliveryDeleteCommand.MESSAGE_USAGE));
    }
}


