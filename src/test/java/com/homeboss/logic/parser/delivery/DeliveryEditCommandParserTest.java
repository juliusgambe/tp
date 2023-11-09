package com.homeboss.logic.parser.delivery;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.delivery.DeliveryEditCommand;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.model.person.Name;
import com.homeboss.testutil.DeliveryEditDescriptorBuilder;
import com.homeboss.testutil.TypicalIndexes;
import org.junit.jupiter.api.Test;

import com.homeboss.model.delivery.DeliveryDate;
import com.homeboss.model.delivery.DeliveryStatus;
import com.homeboss.model.delivery.Note;


public class DeliveryEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeliveryEditCommand.MESSAGE_USAGE);
    private DeliveryEditCommandParser parser = new DeliveryEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", DeliveryEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_MILK, MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0" + CommandTestUtil.NAME_DESC_MILK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //in valid name
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        //invalid customer id
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_CUSTOMER_ID_DESC, ParserUtil.MESSAGE_INVALID_INDEX);
        //invalid delivery date
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_FORMAT_DELIVERY_DATE_DESC, DeliveryDate.MESSAGE_CONSTRAINTS);

        //invalid status
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_STATUS_DESC, DeliveryStatus.MESSAGE_CONSTRAINTS);

        //invalid note
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NOTE_DESC, Note.MESSAGE_CONSTRAINTS);

        //invalid name followed by valid customer id
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.CUSTOMER_ID_DESC_MILK, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_CUSTOMER_ID_DESC + CommandTestUtil.INVALID_NOTE_DESC,
               Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_JAMES_MILK + CommandTestUtil.DELIVERY_DATE_DESC_MILK
                + CommandTestUtil.VALID_STATUS_DESC + CommandTestUtil.CUSTOMER_ID_DESC_MILK + CommandTestUtil.VALID_NOTE_DESC;

        DeliveryEditCommand.DeliveryEditDescriptor descriptor =
                new DeliveryEditDescriptorBuilder().withDeliveryName(CommandTestUtil.VALID_NAME_JAMES_MILK)
                        .withDeliveryDate(CommandTestUtil.VALID_DELIVERY_DATE_1).withStatus(
                        CommandTestUtil.VALID_STATUS_CREATED)
                        .withCustomerId(CommandTestUtil.VALID_CUSTOMER_ID_1).withNote(CommandTestUtil.VALID_NOTE).build();

        DeliveryEditCommand expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);


        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() { //not done
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_JAMES_MILK + CommandTestUtil.VALID_STATUS_DESC;

        DeliveryEditCommand.DeliveryEditDescriptor descriptor =
                new DeliveryEditDescriptorBuilder().withDeliveryName(CommandTestUtil.VALID_NAME_JAMES_MILK)
                .withStatus(CommandTestUtil.VALID_STATUS_CREATED).build();
        DeliveryEditCommand expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // delivery name
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_JAMES_MILK;
        DeliveryEditCommand.DeliveryEditDescriptor descriptor =
                new DeliveryEditDescriptorBuilder().withDeliveryName(CommandTestUtil.VALID_NAME_JAMES_MILK).build();
        DeliveryEditCommand expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // delivery date
        userInput = targetIndex.getOneBased() + CommandTestUtil.DELIVERY_DATE_DESC_MILK;
        descriptor = new DeliveryEditDescriptorBuilder().withDeliveryDate(CommandTestUtil.VALID_DELIVERY_DATE_1).build();
        expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_STATUS_DESC;
        descriptor = new DeliveryEditDescriptorBuilder().withStatus(CommandTestUtil.VALID_STATUS_CREATED).build();
        expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // customer id
        userInput = targetIndex.getOneBased() + CommandTestUtil.CUSTOMER_ID_DESC_RICE;
        descriptor =
                new DeliveryEditDescriptorBuilder().withCustomerId(CommandTestUtil.VALID_CUSTOMER_ID_2).build();
        expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // note
        userInput = targetIndex.getOneBased() + CommandTestUtil.VALID_NOTE_DESC;
        descriptor = new DeliveryEditDescriptorBuilder().withNote(CommandTestUtil.VALID_NOTE).build();
        expectedCommand = new DeliveryEditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_MILK + CommandTestUtil.INVALID_NAME_DESC;

        CommandParserTestUtil.assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(
            CliSyntax.PREFIX_NAME));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_CUSTOMER_ID_DESC + CommandTestUtil.CUSTOMER_ID_DESC_MILK;

        CommandParserTestUtil.assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(
            CliSyntax.PREFIX_CUSTOMER_ID));

        // mulltiple valid fields repeated
        userInput =
                targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_MILK + CommandTestUtil.CUSTOMER_ID_DESC_MILK + CommandTestUtil.NAME_DESC_JAMES_MILK
                        + CommandTestUtil.DELIVERY_DATE_DESC_MILK + CommandTestUtil.CUSTOMER_ID_DESC_RICE + CommandTestUtil.DELIVERY_DATE_DESC_RICE;

        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_CUSTOMER_ID, CliSyntax.PREFIX_DATE));

        //multiple invalid values

        userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_NOTE_DESC + CommandTestUtil.INVALID_CUSTOMER_ID_DESC + CommandTestUtil.INVALID_NOTE_DESC
                + CommandTestUtil.INVALID_CUSTOMER_ID_DESC + CommandTestUtil.INVALID_DELIVERY_NAME_DESC + CommandTestUtil.INVALID_DELIVERY_NAME_DESC;

        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NOTE, CliSyntax.PREFIX_CUSTOMER_ID, CliSyntax.PREFIX_NAME));
    }

}
