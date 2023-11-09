package com.homeboss.logic.parser.delivery;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.delivery.DeliveryViewCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;


public class DeliveryViewCommandParserTest {

    private DeliveryViewCommandParser parser = new DeliveryViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeliveryViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeliveryViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.VALID_VIEW_DELIVERY_ID,
                new DeliveryViewCommand(1));
    }

    @Test
    public void parse_validArgsExtraSpaceBetween_returnsDeliveryViewCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, " " + CommandTestUtil.VALID_VIEW_DELIVERY_ID,
                new DeliveryViewCommand(1));
    }

    @Test
    public void parse_invalidArgs_returnsDeliveryViewCommand() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeliveryViewCommand.MESSAGE_USAGE));
    }
}
