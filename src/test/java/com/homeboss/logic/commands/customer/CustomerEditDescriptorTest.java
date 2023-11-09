package com.homeboss.logic.commands.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.customer.CustomerEditCommand.CustomerEditDescriptor;
import com.homeboss.testutil.CustomerEditDescriptorBuilder;

public class CustomerEditDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true

        CustomerEditDescriptor descriptorWithSameValues = new CustomerEditDescriptor(CommandTestUtil.DESC_AMY);

        assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        CustomerEditDescriptor editedAmy = new CustomerEditDescriptorBuilder(CommandTestUtil.DESC_AMY).withName(
                CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new CustomerEditDescriptorBuilder(CommandTestUtil.DESC_AMY).withPhone(
                CommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new CustomerEditDescriptorBuilder(CommandTestUtil.DESC_AMY).withEmail(
                CommandTestUtil.VALID_EMAIL_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new CustomerEditDescriptorBuilder(CommandTestUtil.DESC_AMY).withAddress(
                CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        CustomerEditDescriptor customerEditDescriptor = new CustomerEditDescriptor();
        String expected = CustomerEditDescriptor.class.getCanonicalName() + "{name="
                + customerEditDescriptor.getName().orElse(null) + ", phone="
                + customerEditDescriptor.getPhone().orElse(null) + ", email="
                + customerEditDescriptor.getEmail().orElse(null) + ", address="
                + customerEditDescriptor.getAddress().orElse(null)
                + "}";
        assertEquals(expected, customerEditDescriptor.toString());
    }
}
