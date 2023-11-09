package com.homeboss.logic.parser.user;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_USER;

import java.util.stream.Stream;

import com.homeboss.logic.commands.user.UserLoginCommand;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.Prefix;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.user.Password;
import com.homeboss.model.user.User;
import com.homeboss.model.user.Username;

/**
 * Parses input arguments and creates a new UserLoginCommand object
 */
public class UserLoginCommandParser implements Parser<UserLoginCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UserLoginCommand
     * and returns an UserLoginCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UserLoginCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_USER, PREFIX_PASSWORD);

        if (!arePrefixesPresent(argMultimap, PREFIX_USER, PREFIX_PASSWORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserLoginCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_USER, PREFIX_PASSWORD);
        Username username = ParserUtil.parseUsername(argMultimap.getValue(PREFIX_USER).get());
        Password password = ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get());

        User user = new User(username, password, true);

        return new UserLoginCommand(user);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
