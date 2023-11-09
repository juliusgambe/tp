package com.homeboss.logic.parser.user;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.user.UserUpdateCommand;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.testutil.UpdateUserDescriptorBuilder;
import org.junit.jupiter.api.Test;

import com.homeboss.model.user.Password;
import com.homeboss.model.user.Username;

public class UserUpdateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(UserUpdateCommand.MESSAGE_MISSING_FIELDS, UserUpdateCommand.MESSAGE_USAGE);

    private UserUpdateCommandParser parser = new UserUpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // password specified, but no confirm password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PASSWORD_DESC_AARON, UserUpdateCommand.MESSAGE_PASSWORD_OR_CONFIRM_PASSWORD_MISSING);

        // confirm password specified, but no password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON, UserUpdateCommand.MESSAGE_PASSWORD_OR_CONFIRM_PASSWORD_MISSING);

        // secret question specified, but no answer
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.SECRET_QUESTION_DESC_AARON, UserUpdateCommand.MESSAGE_QUESTION_OR_ANSWER_MISSING);

        // answer specified, but no secret question
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.ANSWER_DESC_AARON, UserUpdateCommand.MESSAGE_QUESTION_OR_ANSWER_MISSING);

        // password and secret question specified, but no confirm password and answer
        // password pair is checked first
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON,
                UserUpdateCommand.MESSAGE_PASSWORD_OR_CONFIRM_PASSWORD_MISSING);

        // answer and confirm password specified, but no password and secret question
        // password pair is checked first
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.ANSWER_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                UserUpdateCommand.MESSAGE_PASSWORD_OR_CONFIRM_PASSWORD_MISSING);

    }

    @Test
    public void parse_invalidValue_failure() {
        // There is no invalid secret question and answer
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_USERNAME_DESC, Username.MESSAGE_CONSTRAINTS); // invalid username
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_PASSWORD_DESC + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC,
                Password.MESSAGE_CONSTRAINTS); // invalid password

        // invalid username followed by valid password and confirm password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                Username.MESSAGE_CONSTRAINTS);

        // valid username followed by invalid password and confirm password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.INVALID_PASSWORD_DESC + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC,
                Password.MESSAGE_CONSTRAINTS);

        // valid username followed by mismatched password and confirm password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_FOODBEAR,
                UserUpdateCommand.MESSAGE_PASSWORD_MISMATCH);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.INVALID_PASSWORD_DESC
                        + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC, Username.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.USERNAME_DESC_AARON,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UserUpdateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON
                + CommandTestUtil.SECRET_QUESTION_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON;

        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder().withUsername(
                CommandTestUtil.VALID_USERNAME_AARON)
                .withPassword(CommandTestUtil.VALID_PASSWORD_AARON).withSecretQuestion(
                CommandTestUtil.VALID_SECRET_QUESTION_AARON)
                .withAnswer(CommandTestUtil.VALID_ANSWER_AARON).build();
        UserUpdateCommand expectedCommand = new UserUpdateCommand(descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON;

        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder().withUsername(
                CommandTestUtil.VALID_USERNAME_AARON)
                .withSecretQuestion(CommandTestUtil.VALID_SECRET_QUESTION_AARON).withAnswer(
                CommandTestUtil.VALID_ANSWER_AARON).build();
        UserUpdateCommand expectedCommand = new UserUpdateCommand(descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // username
        String userInput = CommandTestUtil.USERNAME_DESC_AARON;
        UserUpdateCommand.UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder().withUsername(
            CommandTestUtil.VALID_USERNAME_AARON).build();
        UserUpdateCommand expectedCommand = new UserUpdateCommand(descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // password pair
        userInput = CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON;
        descriptor = new UpdateUserDescriptorBuilder().withPassword(CommandTestUtil.VALID_PASSWORD_AARON).build();
        expectedCommand = new UserUpdateCommand(descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // secret question and answer pair
        userInput = CommandTestUtil.SECRET_QUESTION_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON;
        descriptor = new UpdateUserDescriptorBuilder().withSecretQuestion(CommandTestUtil.VALID_SECRET_QUESTION_AARON)
                .withAnswer(CommandTestUtil.VALID_ANSWER_AARON).build();
        expectedCommand = new UserUpdateCommand(descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {

        // valid followed by invalid
        String userInput = CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.INVALID_USERNAME_DESC;

        CommandParserTestUtil.assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(
            CliSyntax.PREFIX_USER));

        // invalid followed by valid
        userInput = CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.USERNAME_DESC_AARON;

        CommandParserTestUtil.assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(
            CliSyntax.PREFIX_USER));

        // multiple valid fields repeated
        userInput = CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON
                + CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON
                + CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON;

        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(
                    CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD, CliSyntax.PREFIX_PASSWORD_CONFIRM,
                        CliSyntax.PREFIX_SECRET_QUESTION, CliSyntax.PREFIX_ANSWER));

        // multiple invalid values
        userInput = CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.INVALID_PASSWORD_DESC + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC
                + CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.INVALID_PASSWORD_DESC + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC;

        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(
                    CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD, CliSyntax.PREFIX_PASSWORD_CONFIRM));
    }
}
