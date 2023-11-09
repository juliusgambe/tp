package com.homeboss.logic.parser.user;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import com.homeboss.logic.commands.user.UserRegisterCommand;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.Prefix;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;

/**
 * Parses input arguments and creates a new UserRegisterCommand object
 */
public class UserRegisterCommandParser implements Parser<UserRegisterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UserRegisterCommand
     * and returns an UserRegisterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UserRegisterCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD,
                CliSyntax.PREFIX_PASSWORD_CONFIRM, CliSyntax.PREFIX_SECRET_QUESTION, CliSyntax.PREFIX_ANSWER);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD,
            CliSyntax.PREFIX_PASSWORD_CONFIRM, CliSyntax.PREFIX_SECRET_QUESTION, CliSyntax.PREFIX_ANSWER)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserRegisterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
            CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD, CliSyntax.PREFIX_PASSWORD_CONFIRM,
            CliSyntax.PREFIX_SECRET_QUESTION, CliSyntax.PREFIX_ANSWER);
        Username username = ParserUtil.parseUsername(argMultimap.getValue(CliSyntax.PREFIX_USER).get());
        Password password = ParserUtil.parsePassword(argMultimap.getValue(CliSyntax.PREFIX_PASSWORD).get());
        Password confirmPassword = ParserUtil.parsePassword(
            argMultimap.getValue(CliSyntax.PREFIX_PASSWORD_CONFIRM).get());
        String secretQuestion = ParserUtil.parseSecretQuestion(
            argMultimap.getValue(CliSyntax.PREFIX_SECRET_QUESTION).get());
        String answer = ParserUtil.parseAnswer(argMultimap.getValue(CliSyntax.PREFIX_ANSWER).get());

        if (!password.equals(confirmPassword)) {
            throw new ParseException(UserRegisterCommand.MESSAGE_PASSWORD_MISMATCH);
        }

        User user = new User(username, password, true, secretQuestion, answer);

        return new UserRegisterCommand(user);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
