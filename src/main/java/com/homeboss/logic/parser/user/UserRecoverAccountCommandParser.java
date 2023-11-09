package com.homeboss.logic.parser.user;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import com.homeboss.logic.commands.user.UserRecoverAccountCommand;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.Prefix;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.user.Password;

/**
 * Parses input arguments and creates a new UserRecoverAccountCommand object
 */
public class UserRecoverAccountCommandParser implements Parser<UserRecoverAccountCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UserRecoverAccountCommand
     * and returns an UserRecoverAccountCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UserRecoverAccountCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_ANSWER, CliSyntax.PREFIX_PASSWORD,
                CliSyntax.PREFIX_PASSWORD_CONFIRM);

        if (arePrefixesAbsent(argMultimap, CliSyntax.PREFIX_ANSWER, CliSyntax.PREFIX_PASSWORD,
            CliSyntax.PREFIX_PASSWORD_CONFIRM)) {
            return new UserRecoverAccountCommand();
        }

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ANSWER, CliSyntax.PREFIX_PASSWORD,
            CliSyntax.PREFIX_PASSWORD_CONFIRM)
            || !argMultimap.getPreamble().isEmpty()) {
            // if not viewing secret question, then all prefixes must be present
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UserRecoverAccountCommand.MESSAGE_USAGE));
        }
        // all prefixes present
        // check if password and password confirm are present and matches
        argMultimap.verifyNoDuplicatePrefixesFor(
            CliSyntax.PREFIX_ANSWER, CliSyntax.PREFIX_PASSWORD, CliSyntax.PREFIX_PASSWORD_CONFIRM);
        Password password = ParserUtil.parsePassword(argMultimap.getValue(CliSyntax.PREFIX_PASSWORD).get());
        Password confirmPassword = ParserUtil.parsePassword(
            argMultimap.getValue(CliSyntax.PREFIX_PASSWORD_CONFIRM).get());
        String answer = ParserUtil.parseAnswer(argMultimap.getValue(CliSyntax.PREFIX_ANSWER).get());
        if (!password.equals(confirmPassword)) {
            throw new ParseException(UserRecoverAccountCommand.MESSAGE_PASSWORD_MISMATCH);
        }
        return new UserRecoverAccountCommand(answer, password);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes contains empty {@code Optional} values in the given
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }
}
