package com.homeboss.logic.parser.delivery;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.parser.CommandParserTestUtil;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.delivery.DeliveryStatusCommand;
import com.homeboss.model.delivery.DeliveryStatus;

public class DeliveryStatusCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryStatusCommand.MESSAGE_USAGE);

    private DeliveryStatusCommandParser parser = new DeliveryStatusCommandParser();

    @Test
    public void parse_allFields_success() {
        // CREATED
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CREATED);
        CommandParserTestUtil.assertParseSuccess(parser, " 1 " + CommandTestUtil.VALID_STATUS_CREATED_VIEW, deliveryStatusCommand);

        // SHIPPED
        deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.SHIPPED);

        CommandParserTestUtil.assertParseSuccess(parser, " 1 " + CommandTestUtil.VALID_STATUS_SHIPPED_VIEW, deliveryStatusCommand);

        // COMPLETED
        deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.COMPLETED);
        CommandParserTestUtil.assertParseSuccess(parser, " 1 " + CommandTestUtil.VALID_STATUS_COMPLETED_VIEW, deliveryStatusCommand);

        // CANCELLED
        deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CANCELLED);
        CommandParserTestUtil.assertParseSuccess(parser, " 1 " + CommandTestUtil.VALID_STATUS_CANCELLED_VIEW, deliveryStatusCommand);

    }

    @Test
    public void parse_allFieldsExtraSpaceBetween_success() {
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CREATED);
        CommandParserTestUtil.assertParseSuccess(parser, "1    " + CommandTestUtil.VALID_STATUS_CREATED, deliveryStatusCommand);
    }

    @Test
    public void parse_allFieldsExtraSpaceBefore_success() {
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CREATED);
        CommandParserTestUtil.assertParseSuccess(parser, " 1 " + CommandTestUtil.VALID_STATUS_CREATED, deliveryStatusCommand);
    }

    @Test
    public void parse_allFieldsExtraSpaceAfter_success() {
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CREATED);
        CommandParserTestUtil.assertParseSuccess(parser, "1 " + CommandTestUtil.VALID_STATUS_CREATED + " ", deliveryStatusCommand);
    }

    @Test
    public void parse_statusLowerCase_success() {
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CREATED);

        CommandParserTestUtil.assertParseSuccess(parser, "1 " + CommandTestUtil.VALID_STATUS_CREATED_VIEW.toLowerCase(), deliveryStatusCommand);

    }

    @Test
    public void parse_statusUpperCase_success() {
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(1, DeliveryStatus.CREATED);

        CommandParserTestUtil.assertParseSuccess(parser, "1 " + CommandTestUtil.VALID_STATUS_CREATED_VIEW.toUpperCase(), deliveryStatusCommand);

    }

    @Test
    public void parse_invalidStatus_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1 " + CommandTestUtil.INVALID_STATUS, DeliveryStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidAllStatus_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1 ALL", DeliveryStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingStatus_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_extraWordBeforeStatus_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1 word " + CommandTestUtil.INVALID_STATUS, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_negativeId_failure() {
        CommandParserTestUtil.assertParseFailure(parser,
            CommandTestUtil.INVALID_ID_NEGATIVE + " " + CommandTestUtil.INVALID_STATUS, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nanId_failure() {
        CommandParserTestUtil.assertParseFailure(parser,
            CommandTestUtil.INVALID_ID_NAN + " " + CommandTestUtil.INVALID_STATUS, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_extraNumberAfterId_failure() {
        CommandParserTestUtil.assertParseFailure(parser,
            "1 1 " + CommandTestUtil.INVALID_STATUS, MESSAGE_INVALID_FORMAT);
    }
}
