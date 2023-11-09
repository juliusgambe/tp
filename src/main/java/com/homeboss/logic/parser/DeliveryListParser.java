package com.homeboss.logic.parser;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_DATE;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_SORT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_STATUS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.homeboss.logic.Sort;
import com.homeboss.logic.commands.delivery.DeliveryListCommand;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.delivery.Date;
import com.homeboss.model.delivery.DeliveryStatus;


/**
 * Parses input arguments and creates a new DeliveryListCommand object.
 */
public class DeliveryListParser implements Parser<DeliveryListCommand> {

    @Override
    public DeliveryListCommand parse(String userInput) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_STATUS, PREFIX_SORT, PREFIX_CUSTOMER_ID, PREFIX_DATE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryListCommand.MESSAGE_USAGE));
        }
        Optional<String> sort = argMultimap.getValue(PREFIX_SORT);
        Optional<String> inputStatus = argMultimap.getValue(PREFIX_STATUS);
        Optional<String> inputCustomerId = argMultimap.getValue(PREFIX_CUSTOMER_ID);
        Optional<String> inputDate = argMultimap.getValue(PREFIX_DATE);
        DeliveryStatus status = null;
        Integer customerId = null;
        Date deliveryDate = null;
        Sort sortString = ParserUtil.parseSort(sort.orElse("desc"));

        if (inputStatus.isPresent()) {
            status = ParserUtil.parseDeliveryStatus(inputStatus.get());
        }

        if (inputCustomerId.isPresent()) {
            customerId = ParserUtil.parseId(inputCustomerId.get());
        }

        if (inputDate.isPresent()) {

            if (inputDate.get().equalsIgnoreCase("today")) {
                deliveryDate = new Date(LocalDate.now().format(DateTimeFormatter.ofPattern(Date.FORMAT)));
            } else {
                deliveryDate = ParserUtil.parseDate(inputDate.get());
            }
        }


        return new DeliveryListCommand(status, customerId, deliveryDate, sortString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeliveryListParser); // instanceof handles nulls

    }
}
