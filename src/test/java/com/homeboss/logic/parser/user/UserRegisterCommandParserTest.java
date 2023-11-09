package com.homeboss.logic.parser.user;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.testutil.TypicalUsers.AARON;
import static com.homeboss.testutil.TypicalUsers.FOODBEAR;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.user.UserRegisterCommand;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;
import com.homeboss.testutil.UserBuilder;

public class UserRegisterCommandParserTest {

    private final UserRegisterCommandParser parser = new UserRegisterCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        User expectedUserAaron = new UserBuilder(AARON).build();
        User expectedUserFoodBear = new UserBuilder(FOODBEAR).build();

        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.USERNAME_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON
                        // second password is for confirmation
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON
                        + CommandTestUtil.SECRET_QUESTION_DESC_AARON
                        + CommandTestUtil.ANSWER_DESC_AARON, new UserRegisterCommand(expectedUserAaron));

        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.USERNAME_DESC_FOODBEAR
                        + CommandTestUtil.PASSWORD_DESC_FOODBEAR
                        // second password is for confirmation
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_FOODBEAR
                        + CommandTestUtil.SECRET_QUESTION_DESC_FOODBEAR
                        + CommandTestUtil.ANSWER_DESC_FOODBEAR, new UserRegisterCommand(expectedUserFoodBear));
    }

    @Test
    public void parse_missingFields_failure() {
        // missing secret question prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserRegisterCommand.MESSAGE_USAGE));

        // missing answer prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserRegisterCommand.MESSAGE_USAGE));

        // missing question and answer prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserRegisterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedUserString = CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON
                + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON
                + CommandTestUtil.ANSWER_DESC_AARON;

        // multiple usernames
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.USERNAME_DESC_AARON
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_USER));

        // multiple password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PASSWORD_DESC_AARON
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PASSWORD));

        // multiple fields repeated
        CommandParserTestUtil.assertParseFailure(parser,
                validExpectedUserString + CommandTestUtil.USERNAME_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD));

        // multiple confirm password
        CommandParserTestUtil.assertParseFailure(parser,
                validExpectedUserString + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PASSWORD_CONFIRM));

        // invalid value followed by valid value

        // invalid username
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_USERNAME_DESC + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_USER));

        // invalid password
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_PASSWORD_DESC + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PASSWORD));

        // valid value followed by invalid value

        CommandParserTestUtil.assertParseFailure(parser,
                validExpectedUserString + CommandTestUtil.INVALID_USERNAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_USER));

        CommandParserTestUtil.assertParseFailure(parser,
                validExpectedUserString + CommandTestUtil.INVALID_PASSWORD_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PASSWORD));
    }

    @Test
    // test password mismatch
    public void parse_passwordMismatch_failure() {
        String expectedMessage = UserRegisterCommand.MESSAGE_PASSWORD_MISMATCH;

        // password mismatch
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_FOODBEAR + CommandTestUtil.SECRET_QUESTION_DESC_AARON
                        + CommandTestUtil.ANSWER_DESC_AARON,
                expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserRegisterCommand.MESSAGE_USAGE);

        // missing username prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_PASSWORD_AARON, expectedMessage);

        // missing password prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_USERNAME_AARON, expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.VALID_USERNAME_AARON + CommandTestUtil.VALID_PASSWORD_AARON,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid username
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.PASSWORD_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON + CommandTestUtil.SECRET_QUESTION_DESC_AARON
                        + CommandTestUtil.ANSWER_DESC_AARON,
                Username.MESSAGE_CONSTRAINTS);

        // invalid password
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.INVALID_PASSWORD_DESC
                        + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC + CommandTestUtil.SECRET_QUESTION_DESC_AARON
                        + CommandTestUtil.ANSWER_DESC_AARON, Password.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_USERNAME_DESC + CommandTestUtil.INVALID_PASSWORD_DESC
                        + CommandTestUtil.INVALID_PASSWORD_CONFIRM_DESC + CommandTestUtil.SECRET_QUESTION_DESC_AARON
                        + CommandTestUtil.ANSWER_DESC_AARON,
                Username.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.USERNAME_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON
                        + CommandTestUtil.SECRET_QUESTION_DESC_AARON + CommandTestUtil.ANSWER_DESC_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserRegisterCommand.MESSAGE_USAGE));
    }
}
