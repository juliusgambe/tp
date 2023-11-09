package com.homeboss.logic.parser.delivery;

import java.util.Arrays;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.delivery.DeliveryFindCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;
import org.junit.jupiter.api.Test;

import com.homeboss.model.delivery.DeliveryNameContainsKeywordsPredicate;

public class DeliveryFindCommandParserTest {

    private DeliveryFindCommandParser parser = new DeliveryFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "         ",
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeliveryFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeliveryFindCommand() {
        // no leading and trailing whitespaces
        DeliveryFindCommand expectedFindCommand =
            new DeliveryFindCommand(new DeliveryNameContainsKeywordsPredicate(Arrays.asList("Gabriel", "Gambe")));
        CommandParserTestUtil.assertParseSuccess(parser, "Gabriel Gambe", expectedFindCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Gabriel \n \t Gambe  \t", expectedFindCommand);
    }
}
