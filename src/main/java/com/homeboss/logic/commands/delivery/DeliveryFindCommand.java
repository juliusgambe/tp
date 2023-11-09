package com.homeboss.logic.commands.delivery;

import static java.util.Objects.requireNonNull;

import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.delivery.DeliveryNameContainsKeywordsPredicate;

/**
 * Finds and lists all deliveries in delivery book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class DeliveryFindCommand extends DeliveryCommand {

    public static final String COMMAND_WORD = DeliveryCommand.COMMAND_WORD + " find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all deliveries whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with ID numbers.\n\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\n"
            + "Example: " + COMMAND_WORD + " chocolate vanilla";

    private final DeliveryNameContainsKeywordsPredicate predicate;

    public DeliveryFindCommand(DeliveryNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(Messages.MESSAGE_USER_NOT_AUTHENTICATED);
        }
        model.updateFilteredDeliveryList(Model.PREDICATE_SHOW_ALL_DELIVERIES);

        model.updateFilteredDeliveryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DELIVERY_LISTED_OVERVIEW,
                        model.getFilteredDeliveryList().size()), true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeliveryFindCommand)) {
            return false;
        }
        DeliveryFindCommand otherDeliveryFindCommand = (DeliveryFindCommand) other;
        return predicate.equals(otherDeliveryFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
