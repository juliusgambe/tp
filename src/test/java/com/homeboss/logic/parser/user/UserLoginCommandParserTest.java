package com.homeboss.logic.parser.user;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_USER;
import static com.homeboss.testutil.TypicalUsers.AARON;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.user.UserLoginCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;
import com.homeboss.testutil.UserBuilder;

public class UserLoginCommandParserTest {

    private UserLoginCommandParser parser = new UserLoginCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        User expectedUser = new UserBuilder(AARON).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.USERNAME_DESC_AARON
                + CommandTestUtil.PASSWORD_DESC_AARON, new UserLoginCommand(expectedUser));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedUserString = CommandTestUtil.USERNAME_DESC_FOODBEAR
                + CommandTestUtil.PASSWORD_DESC_FOODBEAR;

        // multiple usernames
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.USERNAME_DESC_AARON
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER));

        // multiple password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PASSWORD_DESC_AARON
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PASSWORD));

        // multiple fields repeated
        CommandParserTestUtil.assertParseFailure(parser,
                validExpectedUserString + CommandTestUtil.USERNAME_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER, PREFIX_PASSWORD));

        // invalid value followed by valid value

        // invalid username
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_USERNAME_DESC
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER));

        // invalid password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_PASSWORD_DESC
                        + validExpectedUserString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PASSWORD));

        // valid value followed by invalid value

        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, validExpectedUserString
                        + CommandTestUtil.INVALID_USERNAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_USER));

        // invalid email
        CommandParserTestUtil.assertParseFailure(parser, validExpectedUserString
                        + CommandTestUtil.INVALID_PASSWORD_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PASSWORD));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserLoginCommand.MESSAGE_USAGE);

        // missing username prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_PASSWORD_AARON, expectedMessage);

        // missing password prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_USERNAME_AARON, expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_USERNAME_AARON
                        + CommandTestUtil.VALID_PASSWORD_AARON,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid username
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_USERNAME_DESC
                + CommandTestUtil.PASSWORD_DESC_AARON, Username.MESSAGE_CONSTRAINTS);

        // invalid password
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.USERNAME_DESC_AARON
                + CommandTestUtil.INVALID_PASSWORD_DESC, Password.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_USERNAME_DESC
                        + CommandTestUtil.INVALID_PASSWORD_DESC,
                Username.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.USERNAME_DESC_AARON + CommandTestUtil.PASSWORD_DESC_AARON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserLoginCommand.MESSAGE_USAGE));
    }
}
