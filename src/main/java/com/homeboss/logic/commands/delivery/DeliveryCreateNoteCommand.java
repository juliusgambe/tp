package com.homeboss.logic.commands.delivery;

import static com.homeboss.logic.Messages.MESSAGE_USER_NOT_AUTHENTICATED;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_NOTE;
import static com.homeboss.model.Model.PREDICATE_SHOW_ALL_DELIVERIES;
import static java.util.Objects.requireNonNull;

import java.util.Optional;

import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.Command;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.Model;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.DeliveryDate;
import com.homeboss.model.delivery.DeliveryName;
import com.homeboss.model.delivery.DeliveryStatus;
import com.homeboss.model.delivery.Note;
import com.homeboss.model.delivery.OrderDate;
import com.homeboss.model.person.Customer;

/**
 * Represents a Command to create a note for a delivery
 */
public class DeliveryCreateNoteCommand extends Command {

    public static final String COMMAND_WORD = DeliveryCommand.COMMAND_WORD + " " + "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the delivery identified "
        + "by the DELIVERY_ID of the delivery. Existing note if any will be replaced with the input note.\n\n"
        + "Parameters: DELIVERY_ID (must be a integer representing a valid ID) "
        + PREFIX_NOTE + " Note\n\n"
        + "Example: " + COMMAND_WORD + " 1 --note This is a note";

    public static final String MESSAGE_NOTE_SUCCESS = "Added Note to Delivery:\n\n%1$s";

    private final int targetId;
    private final Note newNote;

    /**
     * Creates a DeliveryCreateNoteCommand to create a note of {@code newNote} for the delivery specified by
     * {@code targetId}.
     *
     * @param targetId the delivery to create a note for.
     * @param newNote  the note to be added.
     */
    public DeliveryCreateNoteCommand(int targetId, Note newNote) {
        requireNonNull(newNote);

        this.targetId = targetId;
        this.newNote = newNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(MESSAGE_USER_NOT_AUTHENTICATED);
        }

        // Find Delivery
        Optional<Delivery> targetDelivery = model.getDelivery(targetId);

        if (targetDelivery.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX);
        }

        // Edit Delivery
        Delivery editedDelivery = createDeliveryWithNewNote(targetDelivery.get(), newNote);

        // Update Delivery
        model.setDelivery(targetDelivery.get(), editedDelivery);
        model.updateFilteredDeliveryList(PREDICATE_SHOW_ALL_DELIVERIES);
        return new CommandResult(String.format(MESSAGE_NOTE_SUCCESS, Messages.format(editedDelivery)), true);
    }

    /**
     * Creates and returns a {@code Delivery} with the details of {@code deliveryToEdit}
     * edited with {@code newStatus}.
     */
    private static Delivery createDeliveryWithNewNote(Delivery deliveryToEdit, Note newNote) {
        assert deliveryToEdit != null;

        int updatedId = deliveryToEdit.getDeliveryId();
        DeliveryName updatedName = deliveryToEdit.getName();
        Customer updatedCustomer = deliveryToEdit.getCustomer();
        OrderDate updatedOrderDate = deliveryToEdit.getOrderDate();
        DeliveryDate updatedDeliveryDate = deliveryToEdit.getDeliveryDate();
        DeliveryStatus updatedStatus = deliveryToEdit.getStatus();
        Note updatedNote = newNote;

        return new Delivery(updatedId, updatedName, updatedCustomer, updatedOrderDate,
            updatedDeliveryDate, updatedStatus, updatedNote);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryCreateNoteCommand)) {
            return false;
        }

        DeliveryCreateNoteCommand otherStatusCommand = (DeliveryCreateNoteCommand) other;
        return targetId == otherStatusCommand.targetId
            && newNote.equals(otherStatusCommand.newNote);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("targetId", targetId)
            .add("note", newNote)
            .toString();
    }
}
