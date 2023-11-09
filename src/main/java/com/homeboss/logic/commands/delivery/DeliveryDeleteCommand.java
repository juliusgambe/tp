package com.homeboss.logic.commands.delivery;

import static java.util.Objects.requireNonNull;

import com.homeboss.commons.core.index.Index;
import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.delivery.Delivery;

/**
 * Deletes a delivery identified using it's displayed index from the address book.
 */
public class DeliveryDeleteCommand extends DeliveryCommand {

    public static final String COMMAND_WORD = DeliveryCommand.COMMAND_WORD + " " + "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the delivery identified by the delivery ID used in the displayed delivery list.\n\n"
            + "Parameters: DELIVERY_ID (must be a positive integer)\n\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DELIVERY_SUCCESS = "Deleted Delivery:\n\n%1$s";

    private final Index targetIndex;

    public DeliveryDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(Messages.MESSAGE_USER_NOT_AUTHENTICATED);
        }

        model.updateFilteredDeliveryList(Model.PREDICATE_SHOW_ALL_DELIVERIES);

        Delivery deliveryToDelete = model.getDeliveryUsingFilteredList(targetIndex.getOneBased());

        if (deliveryToDelete != null) {
            model.deleteDelivery(deliveryToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_DELIVERY_SUCCESS,
                    Messages.format(deliveryToDelete)), true);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryDeleteCommand)) {
            return false;
        }

        DeliveryDeleteCommand otherDeleteCommand = (DeliveryDeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
