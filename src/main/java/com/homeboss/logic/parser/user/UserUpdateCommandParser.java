package com.homeboss.logic.parser.user;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.user.UserUpdateCommand;
import com.homeboss.logic.commands.user.UserUpdateCommand.UserUpdateDescriptor;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.user.Password;

/**
 * Parses input arguments and creates a new UserUpdateCommand object
 */
public class UserUpdateCommandParser implements Parser<UserUpdateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UserUpdateCommand
     * and returns a UserUpdateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public UserUpdateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD,
                        CliSyntax.PREFIX_PASSWORD_CONFIRM, CliSyntax.PREFIX_SECRET_QUESTION,
                        CliSyntax.PREFIX_ANSWER);

        argMultimap.verifyNoDuplicatePrefixesFor(
                CliSyntax.PREFIX_USER, CliSyntax.PREFIX_PASSWORD, CliSyntax.PREFIX_PASSWORD_CONFIRM,
                CliSyntax.PREFIX_SECRET_QUESTION, CliSyntax.PREFIX_ANSWER);

        UserUpdateCommand.UserUpdateDescriptor userUpdateDescriptor =
                new UserUpdateCommand.UserUpdateDescriptor();

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    UserUpdateCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_USER).isPresent()) {
            userUpdateDescriptor.setUsername(
                    ParserUtil.parseUsername(argMultimap.getValue(CliSyntax.PREFIX_USER).get()));
        }

        userUpdateDescriptor = parsePasswords(argMultimap, userUpdateDescriptor);

        userUpdateDescriptor = parseSecretQuestionAndAnswer(argMultimap, userUpdateDescriptor);

        if (!userUpdateDescriptor.isAnyFieldEdited()) {
            throw new ParseException(
                    String.format(UserUpdateCommand.MESSAGE_MISSING_FIELDS,
                            UserUpdateCommand.MESSAGE_USAGE));
        }

        return new UserUpdateCommand(userUpdateDescriptor);
    }

    /**
     * Parses the password and confirm password fields, and checks if are either both present or both absent.
     *
     * @param argMultimap          the argument multimap that stores the user input with the respective prefixes
     * @param userUpdateDescriptor the {@code UserUpdateDescriptor} to be updated
     * @return the updated {@code UserUpdateDescriptor}
     * @throws ParseException if the password and confirm password fields are not both present or both absent,
     *                        and if they are both present but does not match each other
     */
    public UserUpdateDescriptor parsePasswords(ArgumentMultimap argMultimap,
                                               UserUpdateDescriptor userUpdateDescriptor) throws ParseException {
        // Either one of password or confirm password is missing.
        if (argMultimap.getValue(CliSyntax.PREFIX_PASSWORD).isPresent()
                && argMultimap.getValue(CliSyntax.PREFIX_PASSWORD_CONFIRM).isEmpty()) {
            throw new ParseException(UserUpdateCommand.MESSAGE_PASSWORD_OR_CONFIRM_PASSWORD_MISSING);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PASSWORD_CONFIRM).isPresent()
                && argMultimap.getValue(CliSyntax.PREFIX_PASSWORD).isEmpty()) {
            throw new ParseException(UserUpdateCommand.MESSAGE_PASSWORD_OR_CONFIRM_PASSWORD_MISSING);
        }
        // Both password and confirm password are present.
        if (argMultimap.getValue(CliSyntax.PREFIX_PASSWORD).isPresent()
                && argMultimap.getValue(CliSyntax.PREFIX_PASSWORD_CONFIRM).isPresent()) {
            Password password = ParserUtil.parsePassword(
                    argMultimap.getValue(CliSyntax.PREFIX_PASSWORD).get());
            Password confirmPassword = ParserUtil.parsePassword(
                    argMultimap.getValue(CliSyntax.PREFIX_PASSWORD_CONFIRM).get());
            // Passwords mismatch
            if (!password.equals(confirmPassword)) {
                throw new ParseException(UserUpdateCommand.MESSAGE_PASSWORD_MISMATCH);
            }
            userUpdateDescriptor.setPassword(password);
        }
        return userUpdateDescriptor;
    }

    /**
     * Parses the secret question and answer fields, and checks if are either both present or both absent.
     *
     * @param argMultimap          the argument multimap that stores the user input with the respective prefixes
     * @param userUpdateDescriptor the {@code UserUpdateDescriptor} to be updated
     * @return the updated {@code UserUpdateDescriptor}
     * @throws ParseException if the secret question and answer fields are not both present or both absent.
     */
    public UserUpdateDescriptor parseSecretQuestionAndAnswer(ArgumentMultimap argMultimap,
                                                             UserUpdateDescriptor userUpdateDescriptor)
            throws ParseException {
        // Either one of secret question or answer is missing.
        if (argMultimap.getValue(CliSyntax.PREFIX_SECRET_QUESTION).isPresent()
                && argMultimap.getValue(CliSyntax.PREFIX_ANSWER).isEmpty()) {
            throw new ParseException(UserUpdateCommand.MESSAGE_QUESTION_OR_ANSWER_MISSING);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ANSWER).isPresent()
                && argMultimap.getValue(CliSyntax.PREFIX_SECRET_QUESTION).isEmpty()) {
            throw new ParseException(UserUpdateCommand.MESSAGE_QUESTION_OR_ANSWER_MISSING);
        }
        // Both secret question and answer are present.
        if (argMultimap.getValue(CliSyntax.PREFIX_SECRET_QUESTION).isPresent()
                && argMultimap.getValue(CliSyntax.PREFIX_ANSWER).isPresent()) {
            userUpdateDescriptor.setSecretQuestion(ParserUtil.parseSecretQuestion(
                    argMultimap.getValue(CliSyntax.PREFIX_SECRET_QUESTION).get()));
            userUpdateDescriptor.setAnswer(
                    ParserUtil.parseAnswer(argMultimap.getValue(CliSyntax.PREFIX_ANSWER).get()));
        }
        return userUpdateDescriptor;
    }
}
