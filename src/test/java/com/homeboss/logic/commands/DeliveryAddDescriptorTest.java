package com.homeboss.logic.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.homeboss.testutil.DeliveryAddDescriptorBuilder;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.delivery.DeliveryAddCommand.DeliveryAddDescriptor;

public class DeliveryAddDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        DeliveryAddDescriptor descriptorWithSameValues = new DeliveryAddDescriptor(CommandTestUtil.DESC_MILK);
        assertTrue(CommandTestUtil.DESC_MILK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_MILK.equals(CommandTestUtil.DESC_MILK));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_MILK.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_MILK.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_MILK.equals(CommandTestUtil.DESC_RICE));

        // different name -> returns false
        DeliveryAddDescriptor editedMilk =
                new DeliveryAddDescriptorBuilder(CommandTestUtil.DESC_MILK).withDeliveryName(
                    CommandTestUtil.VALID_NAME_JAMES_MILK).build();
        assertFalse(CommandTestUtil.DESC_MILK.equals(editedMilk));

        // different customer id -> returns false
        editedMilk = new DeliveryAddDescriptorBuilder(CommandTestUtil.DESC_MILK).withCustomerId(
            CommandTestUtil.VALID_CUSTOMER_ID_2).build();
        assertFalse(CommandTestUtil.DESC_MILK.equals(editedMilk));

        // different expected delivery date -> returns false
        editedMilk = new DeliveryAddDescriptorBuilder(CommandTestUtil.DESC_MILK).withDeliveryDate(
            CommandTestUtil.VALID_DELIVERY_DATE_2).build();
        assertFalse(CommandTestUtil.DESC_MILK.equals(editedMilk));

    }

    @Test
    public void toStringMethod() {
        DeliveryAddDescriptor deliveryAddDescriptor = new DeliveryAddDescriptorBuilder(CommandTestUtil.DESC_MILK).build();
        String expected = DeliveryAddDescriptor.class.getCanonicalName() + "{name="
                + deliveryAddDescriptor.getDeliveryName().orElse(null) + ", customer="
                + deliveryAddDescriptor.getCustomerId().orElse(null) + ", deliveredAt="
                + deliveryAddDescriptor.getDate().orElse(null) + "}";
        assertEquals(expected, deliveryAddDescriptor.toString());
    }
}
