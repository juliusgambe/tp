package com.homeboss.logic.parser.delivery;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.delivery.DeliveryAddCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.logic.parser.DeliveryAddCommandParser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.model.delivery.DeliveryDate;
import com.homeboss.model.delivery.DeliveryName;

public class DeliveryAddCommandParserTest {
    private DeliveryAddCommandParser parser = new DeliveryAddCommandParser();


    @Test
    public void parse_allFieldsPresent_success() {
        DeliveryAddCommand.DeliveryAddDescriptor expectedDescriptor = new DeliveryAddCommand.DeliveryAddDescriptor(
                CommandTestUtil.DESC_MILK);

        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.VALID_NAME_GABRIELS_MILK
                + CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.DELIVERY_DATE_DESC_MILK, new DeliveryAddCommand(expectedDescriptor));
    }

    @Test
    public void parse_missingPrefix_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryAddCommand.MESSAGE_USAGE);
        // missing customer prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_MILK
                + CommandTestUtil.VALID_CUSTOMER_ID_1
                + CommandTestUtil.DELIVERY_DATE_DESC_MILK, expectedMessage);

        // missing expected delivery date prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_MILK
                + CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.VALID_DELIVERY_DATE_1, expectedMessage);
    }

    @Test
    public void parse_missingPreamble_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryAddCommand.MESSAGE_USAGE);

        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.DELIVERY_DATE_DESC_MILK, expectedMessage);

    }

    @Test
    public void parse_duplicatePrefix_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryAddCommand.MESSAGE_USAGE);

        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.DELIVERY_DATE_DESC_MILK, expectedMessage);
    }

    @Test
    public void parse_noArgs_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryAddCommand.MESSAGE_USAGE);

        CommandParserTestUtil.assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // delivery add <name> --customer <customer id> --date <expected delivery date>

        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_DELIVERY_NAME
                + CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.DELIVERY_DATE_DESC_MILK, DeliveryName.MESSAGE_CONSTRAINTS);

        // invalid format expected delivery date
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_GABRIELS_MILK
                + CommandTestUtil.CUSTOMER_ID_DESC_MILK
                + CommandTestUtil.INVALID_FORMAT_DELIVERY_DATE_DESC, DeliveryDate.MESSAGE_CONSTRAINTS);

        // invalid customer id
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_GABRIELS_MILK
                + CommandTestUtil.INVALID_CUSTOMER_ID_DESC
                + CommandTestUtil.DELIVERY_DATE_DESC_MILK, ParserUtil.MESSAGE_INVALID_INDEX);

    }
}
