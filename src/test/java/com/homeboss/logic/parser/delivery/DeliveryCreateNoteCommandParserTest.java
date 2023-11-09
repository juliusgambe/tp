package com.homeboss.logic.parser.delivery;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_NOTE;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.delivery.DeliveryCreateNoteCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.model.delivery.Note;

public class DeliveryCreateNoteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryCreateNoteCommand.MESSAGE_USAGE);

    private DeliveryCreateNoteCommandParser parser = new DeliveryCreateNoteCommandParser();

    @Test
    public void parse_allFieldsValid_success() {
        DeliveryCreateNoteCommand expectedCommand = new DeliveryCreateNoteCommand(1,
                new Note(CommandTestUtil.VALID_NOTE));
        CommandParserTestUtil.assertParseSuccess(parser, "1 " + PREFIX_NOTE + " "
                + CommandTestUtil.VALID_NOTE, expectedCommand);
    }

    @Test
    public void parse_allFieldsValidExtraSpaces_success() {
        DeliveryCreateNoteCommand expectedCommand = new DeliveryCreateNoteCommand(1,
                new Note(CommandTestUtil.VALID_NOTE));
        CommandParserTestUtil.assertParseSuccess(parser, "   1 " + PREFIX_NOTE + "   "
                + CommandTestUtil.VALID_NOTE, expectedCommand);
    }


    @Test
    public void parse_invalidNote_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1 " + PREFIX_NOTE + " "
                + CommandTestUtil.INVALID_NOTE, Note.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingNotePrefix_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1 " + CommandTestUtil.VALID_NOTE,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIdNegative_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "-1 " + PREFIX_NOTE + " "
                + CommandTestUtil.VALID_NOTE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIdNan_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "NaN " + PREFIX_NOTE + " "
                + CommandTestUtil.VALID_NOTE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingId_failure() {
        CommandParserTestUtil.assertParseFailure(parser, PREFIX_NOTE + " " + CommandTestUtil.VALID_NOTE,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidOrder_failure() {
        CommandParserTestUtil.assertParseFailure(parser, PREFIX_NOTE + " " + CommandTestUtil.VALID_NOTE
                + " 1", MESSAGE_INVALID_FORMAT);
    }
}
