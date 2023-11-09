package com.homeboss.logic.parser.delivery;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_NOTE;
import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import com.homeboss.logic.commands.delivery.DeliveryCreateNoteCommand;
import com.homeboss.logic.parser.ArgumentMultimap;
import com.homeboss.logic.parser.ArgumentTokenizer;
import com.homeboss.logic.parser.Parser;
import com.homeboss.logic.parser.ParserUtil;
import com.homeboss.logic.parser.Prefix;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.delivery.Note;

/**
 * Parses input arguments and creates a new DeliveryCreateNoteCommand object
 */
public class DeliveryCreateNoteCommandParser implements Parser<DeliveryCreateNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryCreateNoteCommand
     * and returns an DeliveryCreateNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryCreateNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryCreateNoteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NOTE);

        int id;

        try {
            id = ParserUtil.parseId(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryCreateNoteCommand.MESSAGE_USAGE), pe);
        }

        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());

        return new DeliveryCreateNoteCommand(id, note);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
