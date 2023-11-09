package com.homeboss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.Assert;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.homeboss.model.person.exceptions.DuplicatePersonException;
import com.homeboss.testutil.CustomerBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = TypicalPersons.getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields

        Customer editedAlice = new CustomerBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();

        List<Customer> newCustomers = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newCustomers);

        Assert.assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);
        assertTrue(addressBook.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);

        Customer editedAlice = new CustomerBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();

        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasCustomerWithSamePhone_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.hasCustomerWithSamePhone(null));
    }

    @Test
    public void hasCustomerWithSamePhone_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCustomerWithSamePhone(TypicalPersons.ALICE));
    }

    @Test
    public void hasCustomerWithSamePhone_personInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);
        assertTrue(addressBook.hasCustomerWithSamePhone(TypicalPersons.ALICE));
    }

    @Test
    public void hasCustomerWithSamePhone_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);
        Customer editedAlice = new CustomerBuilder(TypicalPersons.ALICE).withName(CommandTestUtil.VALID_NAME_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertTrue(addressBook.hasCustomerWithSamePhone(editedAlice));
    }

    @Test
    public void hasCustomerWithSamePhone_personWithSamePhoneFieldInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);
        Customer editedAlice = new CustomerBuilder(TypicalPersons.ALICE).withCustomerId(101).withName(
                CommandTestUtil.VALID_NAME_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertTrue(addressBook.hasCustomerWithSamePhone(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> addressBook.getList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyBook<Customer> {
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();

        AddressBookStub(Collection<Customer> customers) {
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Customer> getList() {
            return customers;
        }

        @Override
        public Optional<Customer> getById(int id) {
            return Optional.empty();
        }
    }

}
