package com.homeboss.logic.commands.delivery;

import static com.homeboss.logic.Messages.MESSAGE_USER_NOT_AUTHENTICATED;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_DATE;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_SORT;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_STATUS;
import static com.homeboss.model.Model.PREDICATE_SHOW_ALL_DELIVERIES;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

import com.homeboss.logic.Sort;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.delivery.Date;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.DeliveryStatus;

/**
 * Lists all deliveries in the delivery list to the user.
 */
public class DeliveryListCommand extends DeliveryCommand {
    public static final String COMMAND_WORD = DeliveryCommand.COMMAND_WORD + " " + "list";
    public static final String MESSAGE_SUCCESS = "Listed Deliveries";
    public static final String MESSAGE_EMPTY = "There are currently no deliveries to be listed.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all deliveries or the deliveries filtered and "
        + "sorted by the specified filters or sort.\n\n"
        + "Parameters: \n"
        + "Any of the fields can be specified.\n"
        + "[" + PREFIX_STATUS + " STATUS] "
        + "[" + PREFIX_CUSTOMER_ID + " CUSTOMER_ID] "
        + "[" + PREFIX_DATE + " DATE] "
        + "[" + PREFIX_SORT + " SORT]\n\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_STATUS + " CREATED " + PREFIX_SORT + " ASC "
        + PREFIX_CUSTOMER_ID + " 1 " + PREFIX_DATE + " 2020-10-10";
    private DeliveryStatus status;
    private final Integer customerId;
    private final Date deliveryDate;
    private Sort sortType = Sort.DESC;


    /**
     * Constructor for a DeliveryList Command with status filter and sort type.
     *
     * @param status   status to filter by.
     * @param sortType asc or desc.
     */
    public DeliveryListCommand(DeliveryStatus status, Integer customerId, Date deliveryDate, Sort sortType) {
        this.status = status;
        this.customerId = customerId;
        this.deliveryDate = deliveryDate;

        if (sortType != null) {
            this.sortType = sortType;
        }
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(MESSAGE_USER_NOT_AUTHENTICATED);
        }


        Predicate<Delivery> filters = PREDICATE_SHOW_ALL_DELIVERIES;


        if (status != null) {
            // filter by status
            filters = filters.and(delivery -> delivery.getStatus().equals(status));
        }

        if (customerId != null) {
            // filter by customer id
            filters = filters.and(delivery -> delivery.getCustomer().getCustomerId() == customerId);
        }

        if (deliveryDate != null) {
            // filter by expected delivery date
            filters = filters.and(delivery -> delivery.getDeliveryDate().equals(deliveryDate));
        }

        model.updateFilteredDeliveryList(filters);

        if (model.getFilteredDeliveryList().size() == 0) {
            return new CommandResult(MESSAGE_EMPTY, true);
        }

        // sort by expected delivery date
        model.sortFilteredDeliveryList(
            sortType.equals(Sort.ASC) ? Comparator.comparing(Delivery::getDeliveryDate) : Comparator.comparing(
                    Delivery::getDeliveryDate)
                .reversed());

        //TODO: UI
        return new CommandResult(MESSAGE_SUCCESS, true);
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof DeliveryListCommand)) {
            return false;
        }

        DeliveryListCommand otherDeliveryListCommand = (DeliveryListCommand) other;

        if (this.status != otherDeliveryListCommand.status) {
            return false;
        }

        if (!Objects.equals(this.customerId, otherDeliveryListCommand.customerId)) {
            return false;
        }

        if (this.deliveryDate != null && otherDeliveryListCommand.deliveryDate != null
            && !this.deliveryDate.equals(otherDeliveryListCommand.deliveryDate)) {
            return false;
        }

        return sortType.equals(otherDeliveryListCommand.sortType);
    }
}
