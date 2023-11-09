package com.homeboss.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.delivery.DeliveryEditCommand;
import com.homeboss.testutil.DeliveryEditDescriptorBuilder;

public class DeliveryEditDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        DeliveryEditCommand.DeliveryEditDescriptor descriptorWithSameValues =
                new DeliveryEditCommand.DeliveryEditDescriptor(
                        CommandTestUtil.DESC_EDIT_MILK);
        assertTrue(CommandTestUtil.DESC_EDIT_MILK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_EDIT_CHIPS.equals(CommandTestUtil.DESC_EDIT_CHIPS));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_EDIT_CHIPS.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_EDIT_CHIPS.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_EDIT_CHIPS.equals(CommandTestUtil.DESC_EDIT_MILK));

        // different name -> returns false
        DeliveryEditCommand.DeliveryEditDescriptor editedChips =
                new DeliveryEditDescriptorBuilder(CommandTestUtil.DESC_EDIT_CHIPS).build();
        assertFalse(CommandTestUtil.DESC_EDIT_MILK.equals(editedChips));

        // different customer id -> returns false
        editedChips = new DeliveryEditDescriptorBuilder(CommandTestUtil.DESC_EDIT_CHIPS).withCustomerId(
                CommandTestUtil.VALID_CUSTOMER_ID_2).build();
        assertFalse(CommandTestUtil.DESC_EDIT_MILK.equals(editedChips));

        // different delivery date -> returns false
        editedChips = new DeliveryEditDescriptorBuilder(CommandTestUtil.DESC_EDIT_CHIPS)
                .withDeliveryDate(CommandTestUtil.VALID_DELIVERY_DATE_2).build();
        assertFalse(CommandTestUtil.DESC_EDIT_MILK.equals(editedChips));

    }

    @Test
    public void toStringMethod() {
        DeliveryEditCommand.DeliveryEditDescriptor deliveryEditDescriptor = new DeliveryEditDescriptorBuilder(
                CommandTestUtil.DESC_EDIT_MILK).build();
        String expected = DeliveryEditCommand.DeliveryEditDescriptor.class.getCanonicalName() + "{Delivery Name="
                + deliveryEditDescriptor.getDeliveryName().orElse(null) + ", Customer Id="
                + deliveryEditDescriptor.getCustomerId().orElse(null) + ", Delivery Date="
                + deliveryEditDescriptor.getDeliveryDate().orElse(null) + ", Status="
                + deliveryEditDescriptor.getStatus().orElse(null) + ", Note="
                + deliveryEditDescriptor.getNote().orElse(null) + "}";
        assertEquals(expected, deliveryEditDescriptor.toString());
    }
}

