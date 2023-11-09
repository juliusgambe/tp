package com.homeboss.logic.parser.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.user.UserRecoverAccountCommand;
import com.homeboss.logic.parser.CommandParserTestUtil;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.user.Password;

public class UserRecoverAccountCommandParserTest {

    private final UserRecoverAccountCommandParser parser = new UserRecoverAccountCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.ANSWER_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON
                        // second password is for confirmation
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                new UserRecoverAccountCommand(
                        CommandTestUtil.VALID_ANSWER_AARON, new Password(CommandTestUtil.VALID_PASSWORD_AARON)));

        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.ANSWER_DESC_FOODBEAR
                        + CommandTestUtil.PASSWORD_DESC_FOODBEAR
                        // second password is for confirmation
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_FOODBEAR,
                new UserRecoverAccountCommand(
                        CommandTestUtil.VALID_ANSWER_FOODBEAR, new Password(CommandTestUtil.VALID_PASSWORD_FOODBEAR)));
    }

    @Test
    public void parse_allFieldAbsent_success() throws ParseException {
        // all prefixes are absent
        UserRecoverAccountCommand expectedCommand = parser.parse("");
        assertTrue(expectedCommand.isShowSecretQuestion());
    }

    @Test
    public void parse_someFieldsAbsent_failure() {
        // missing answer
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.PASSWORD_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        UserRecoverAccountCommand.MESSAGE_USAGE));

        // missing first password
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.ANSWER_DESC_AARON
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_AARON,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        UserRecoverAccountCommand.MESSAGE_USAGE));

        // missing confirm password
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.ANSWER_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        UserRecoverAccountCommand.MESSAGE_USAGE));


    }

    // test password mismatch
    @Test
    public void parse_passwordMismatch_failure() {
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.ANSWER_DESC_AARON
                        + CommandTestUtil.PASSWORD_DESC_AARON
                        // second password is for confirmation
                        + CommandTestUtil.PASSWORD_CONFIRM_DESC_FOODBEAR,
                UserRecoverAccountCommand.MESSAGE_PASSWORD_MISMATCH);
    }

}
