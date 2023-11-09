package com.homeboss.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import com.homeboss.commons.util.ToStringBuilder;
import com.homeboss.model.person.Customer;
import com.homeboss.model.person.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyBook<Customer> {

    private final UniquePersonList persons;

    public AddressBook() {
        persons = new UniquePersonList();
    }

    /**
     * Creates an AddressBook using the Data in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyBook<Customer> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Customer> customers) {
        this.persons.setPersons(customers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBook<Customer> newData) {
        requireNonNull(newData);

        setPersons(newData.getList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Customer customer) {
        requireNonNull(customer);
        return persons.contains(customer);
    }

    /**
     * Returns true if a customer with the same {@code Phone} as {@code customer} exists in the address book.
     */
    public boolean hasCustomerWithSamePhone(Customer customer) {
        requireNonNull(customer);
        return persons.containsPhone(customer);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Customer p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        persons.setPerson(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Customer key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("persons", persons)
            .toString();
    }

    @Override
    public ObservableList<Customer> getList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Returns the item with the specified id if it exists
     *
     * @param id the id of the item
     * @return the item specified by the id
     */
    @Override
    public Optional<Customer> getById(int id) {
        return persons.getById(id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
