package com.homeboss.logic.commands.delivery;

import static com.homeboss.commons.util.CollectionUtil.isAnyNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import com.homeboss.commons.core.index.Index;
import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandResult;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.logic.parser.CliSyntax;
import com.homeboss.model.Model;
import com.homeboss.model.ReadOnlyBook;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.DeliveryDate;
import com.homeboss.model.delivery.DeliveryName;
import com.homeboss.model.delivery.DeliveryStatus;
import com.homeboss.model.delivery.Note;
import com.homeboss.model.delivery.OrderDate;
import com.homeboss.model.person.Customer;

/**
 * Edits the details of an existing Delivery in the address book.
 */
public class DeliveryEditCommand extends DeliveryCommand {

    public static final String COMMAND_WORD = DeliveryCommand.COMMAND_WORD + " " + "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the delivery identified "
        + "by the DELIVERY_ID used in the displayed delivery list. "
        + "Existing values will be overwritten by the input values.\n\n"
        + "Parameters: DELIVERY_ID (must be a positive integer)\n\n"
        + "At least one field must be specified."
        + "[" + CliSyntax.PREFIX_NAME + " DELIVERY_NAME] "
        + "[" + CliSyntax.PREFIX_CUSTOMER_ID + " CUSTOMER_ID] "
        + "[" + CliSyntax.PREFIX_DATE + " DELIVERY_DATE] "
        + "[" + CliSyntax.PREFIX_STATUS + " STATUS] "
        + "[" + CliSyntax.PREFIX_NOTE + " NOTE]...\n\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + CliSyntax.PREFIX_NAME + " 10 Chocolate Cakes "
        + CliSyntax.PREFIX_DATE + " 2025-12-12";

    public static final String MESSAGE_EDIT_DELIVERY_SUCCESS = "Edited Delivery:\n\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field must be provided!";
    private final Index targetIndex;
    private final DeliveryEditDescriptor deliveryEditDescriptor;

    /**
     * @param targetIndex            of the delivery in the delivery list to edit
     * @param deliveryEditDescriptor details to edit the delivery with
     */
    public DeliveryEditCommand(Index targetIndex, DeliveryEditDescriptor deliveryEditDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(deliveryEditDescriptor);
        this.targetIndex = targetIndex;
        this.deliveryEditDescriptor = new DeliveryEditDescriptor(deliveryEditDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // User cannot perform this operation before logging in
        if (!model.getUserLoginStatus()) {
            throw new CommandException(Messages.MESSAGE_USER_NOT_AUTHENTICATED);
        }

        boolean found = false;
        Delivery editedDelivery = null;

        model.updateFilteredDeliveryList(Model.PREDICATE_SHOW_ALL_DELIVERIES);

        Delivery deliveryToEdit = model.getDeliveryUsingFilteredList(targetIndex.getOneBased());

        if (deliveryToEdit != null) {
            found = true;
            editedDelivery = createEditedDelivery(model, deliveryToEdit, deliveryEditDescriptor);
        }
        boolean isNull = deliveryToEdit == null || editedDelivery == null || !found;

        if (isNull) {
            throw new CommandException(Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX);
        } else {
            model.setDelivery(deliveryToEdit, editedDelivery);
            model.updateFilteredDeliveryList(Model.PREDICATE_SHOW_ALL_DELIVERIES);
            return new CommandResult(String.format(MESSAGE_EDIT_DELIVERY_SUCCESS,
                Messages.format(editedDelivery)), true);
        }
    }

    /**
     * Creates and returns a {@code Delivery} with the details of edited with {@code editDeliveryDescriptor}.
     *
     * @param model                  {@code Model} which the delivery is edited in.
     * @param deliveryToEdit         {@code Delivery} which the command edits.
     * @param deliveryEditDescriptor {@code editDeliveryDescriptor} details to edit the delivery with.
     */
    private static Delivery createEditedDelivery(Model model, Delivery deliveryToEdit, DeliveryEditDescriptor
        deliveryEditDescriptor) throws CommandException {

        assert deliveryToEdit != null;

        DeliveryName updatedDeliveryName =
            deliveryEditDescriptor.getDeliveryName().orElse(deliveryToEdit.getName());

        int customerId = deliveryEditDescriptor.getCustomerId().orElse(deliveryToEdit.getCustomerId());
        Customer updatedCustomer = null;

        OrderDate orderDate = deliveryToEdit.getOrderDate();

        DeliveryDate updatedDeliveryDate =
            deliveryEditDescriptor.getDeliveryDate().orElse(deliveryToEdit.getDeliveryDate());

        DeliveryStatus updatedDeliveryStatus =
            deliveryEditDescriptor.getStatus().orElse(deliveryToEdit.getStatus());

        Note updatedNote = deliveryEditDescriptor.getNote().orElse(deliveryToEdit.getNote());

        ReadOnlyBook<Customer> customerReadOnlyBook = model.getAddressBook();

        if (checkValidCustomer(model, customerId)) {
            updatedCustomer = customerReadOnlyBook.getById(customerId).get();
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
        if (!DeliveryDate.isFutureDate(updatedDeliveryDate.toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_DELIVERY_DATE);
        }
        return new Delivery(deliveryToEdit.getDeliveryId(), updatedDeliveryName, updatedCustomer, orderDate,
            updatedDeliveryDate,
            updatedDeliveryStatus,
            updatedNote);
    }

    private static boolean checkValidCustomer(Model model, int customerId) {
        return model.getCustomerUsingFilteredList(customerId) != null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryEditCommand)) {
            return false;
        }

        DeliveryEditCommand otherEditCommand = (DeliveryEditCommand) other;
        return targetIndex.equals(otherEditCommand.targetIndex)
            && deliveryEditDescriptor.equals(otherEditCommand.deliveryEditDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("id", targetIndex)
            .add("deliveryEditDescriptor", deliveryEditDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the delivery with. Each non-empty field value will replace the
     * corresponding field value of the delivery.
     */
    public static class DeliveryEditDescriptor {
        private int deliveryId;
        private DeliveryName deliveryName;
        private Integer customerId = null;
        private OrderDate orderDate;
        private DeliveryDate deliveryDate;
        private DeliveryStatus status;
        private Note note;

        public DeliveryEditDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy is used internally.
         */
        public DeliveryEditDescriptor(DeliveryEditDescriptor toCopy) {
            setDeliveryId(toCopy.deliveryId);
            setDeliveryName(toCopy.deliveryName);
            setCustomerId(toCopy.customerId);
            setOrderDate(toCopy.orderDate);
            setDeliveryDate(toCopy.deliveryDate);
            setStatus(toCopy.status);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return isAnyNonNull(deliveryName, customerId, deliveryDate, status, note);
        }

        public Optional<Integer> getDeliveryId() {
            return Optional.ofNullable(deliveryId);
        }

        public void setDeliveryId(int deliveryId) {
            this.deliveryId = deliveryId;
        }

        public void setDeliveryName(DeliveryName deliveryName) {
            this.deliveryName = deliveryName;
        }

        public Optional<DeliveryName> getDeliveryName() {
            return Optional.ofNullable(deliveryName);
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Optional<Integer> getCustomerId() {
            return Optional.ofNullable(customerId);
        }

        public void setOrderDate(OrderDate orderDate) {
            this.orderDate = orderDate;
        }

        public Optional<OrderDate> getOrderDate() {
            return Optional.ofNullable(orderDate);
        }

        public void setDeliveryDate(DeliveryDate deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public Optional<DeliveryDate> getDeliveryDate() {
            return Optional.ofNullable(deliveryDate);
        }

        public void setStatus(DeliveryStatus status) {
            this.status = status;
        }

        public Optional<DeliveryStatus> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeliveryEditDescriptor)) {
                return false;
            }

            DeliveryEditDescriptor otherEditDeliveryDescriptor = (DeliveryEditDescriptor) other;
            return Objects.equals(deliveryName, otherEditDeliveryDescriptor.deliveryName)
                && Objects.equals(customerId, otherEditDeliveryDescriptor.customerId)
                && Objects.equals(deliveryDate, otherEditDeliveryDescriptor.deliveryDate)
                && Objects.equals(status, otherEditDeliveryDescriptor.status)
                && Objects.equals(note, otherEditDeliveryDescriptor.note);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("Delivery Name", deliveryName)
                .add("Customer Id", customerId)
                .add("Delivery Date", deliveryDate)
                .add("Status", status)
                .add("Note", note)
                .toString();
        }
    }
}



