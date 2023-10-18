package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.person.Customer;
import seedu.address.model.user.User;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private boolean isLoggedIn = false;

    private final AddressBook addressBook;
    private final DeliveryBook deliveryBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Delivery> filteredDeliveries;

    private SortedList<Delivery> sortedDeliveries;

    /**
     * Initializes a ModelManager with the given addressBook, deliveryBook and userPrefs.
     */
    public ModelManager(ReadOnlyBook<Customer> addressBook,
                        ReadOnlyBook<Delivery> deliveryBook,
                        ReadOnlyUserPrefs userPrefs, boolean isLoggedIn) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
            + ", delivery book" + deliveryBook
            + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.deliveryBook = new DeliveryBook(deliveryBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getList());
        filteredDeliveries = new FilteredList<>(this.deliveryBook.getList());
        sortedDeliveries = new SortedList<>(filteredDeliveries);
        this.isLoggedIn = isLoggedIn;

    }

    public ModelManager() {
        this(new AddressBook(), new DeliveryBook(), new UserPrefs(), false);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getDeliveryBookFilePath() {
        return userPrefs.getDeliveryBookFilePath();
    }

    @Override
    public void setDeliveryBookFilePath(Path deliveryBookFilePath) {
        requireNonNull(deliveryBookFilePath);
        userPrefs.setDeliveryBookFilePath(deliveryBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyBook<Customer> addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyBook<Customer> getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasPerson(customer);
    }

    @Override
    public void deletePerson(Customer target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Customer customer) {
        addressBook.addPerson(customer);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setPerson(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setPerson(target, editedCustomer);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredPersonList() {
        // only shows the customer list if the user is logged in
        if (!isLoggedIn) {
            filteredCustomers.setPredicate(PREDICATE_SHOW_NO_CUSTOMERS);
        }
        return filteredCustomers;
    }

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     */
    @Override
    public void updateFilteredPersonList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        // only shows the customer list if the user is logged in
        if (isLoggedIn) {
            filteredCustomers.setPredicate(predicate);
        } else {
            filteredCustomers.setPredicate(PREDICATE_SHOW_NO_CUSTOMERS);
        }


    }

    //=========== User Related Methods =======================================================================

    /**
     * Returns true if the {@code user} is currently logged in.
     */
    @Override
    public boolean getUserLoginStatus() {
        return isLoggedIn;
    }

    /**
     * Returns true if the stored username and password matches the given {@code user}.
     */
    @Override
    public boolean userMatches(User user) {
        requireNonNull(user);
        return userPrefs.userMatches(user);
    }

    /**
     * Sets the login flag to true.
     */
    @Override
    public void setLoginSuccess() {
        isLoggedIn = true;
    }

    /**
     * Sets the logout flag to true.
     */
    @Override
    public void setLogoutSuccess() {
        isLoggedIn = false;
    }

    /**
     * Returns the stored user.
     */
    @Override
    public User getStoredUser() {

        return userPrefs.getStoredUser();
    }

    /**
     * Returns the stored user for testing.
     * @param isTestNoStoredUser
     * @return
     */
    @Override
    public User getStoredUser(boolean isTestNoStoredUser) {
        return userPrefs.getStoredUser(isTestNoStoredUser);
    }

    /**
     * Registers the given {@code user}.
     */
    @Override
    public void registerUser(User user) {
        userPrefs.registerUser(user);
        // set login flag to true
        isLoggedIn = true;
    }

    //=========== DeliveryBook ================================================================================

    /**
     * Replaces delivery book data with the data in {@code deliveryBook}.
     */
    @Override
    public void setDeliveryBook(ReadOnlyBook<Delivery> deliveryBook) {
        this.deliveryBook.resetData(deliveryBook);
    }

    /**
     * Returns the DeliveryBook
     */
    @Override
    public ReadOnlyBook<Delivery> getDeliveryBook() {
        return deliveryBook;
    }

    /**
     * Returns true if a delivery with the same identity as {@code delivery} exists in the delivery book.
     */
    @Override
    public boolean hasDelivery(Delivery delivery) {
        requireNonNull(delivery);
        return deliveryBook.hasDelivery(delivery);
    }

    /**
     * Deletes the given delivery.
     */
    @Override
    public void deleteDelivery(Delivery target) {
        deliveryBook.removeDelivery(target);
    }

    /**
     * Adds the given delivery.
     */
    @Override
    public void addDelivery(Delivery delivery) {
        deliveryBook.addDelivery(delivery);
        updateFilteredDeliveryList(PREDICATE_SHOW_ALL_DELIVERIES);
    }

    /**
     * Replaces the given delivery {@code target} with {@code editedDelivery}.
     */
    @Override
    public void setDelivery(Delivery target, Delivery editedDelivery) {
        requireAllNonNull(target, editedDelivery);

        deliveryBook.setDelivery(target, editedDelivery);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Delivery> getFilteredDeliveryList() {
        // only shows the delivery list if the user is logged in
        if (!isLoggedIn) {
            filteredDeliveries.setPredicate(PREDICATE_SHOW_NO_DELIVERIES);
        }
        return filteredDeliveries;
    }

    /**
     * Updates the filter of the filtered delivery list to filter by the given {@code predicate}.
     */
    @Override
    public ObservableList<Delivery> getSortedDeliveryList() {
        return sortedDeliveries;
    }

    @Override
    public void updateFilteredDeliveryList(Predicate<Delivery> predicate) {
        requireNonNull(predicate);
        // only shows the delivery list if the user is logged in
        if (isLoggedIn) {
            filteredDeliveries.setPredicate(predicate);
        } else {
            filteredDeliveries.setPredicate(PREDICATE_SHOW_NO_DELIVERIES);
        }

        // Update the sorted list
        this.sortedDeliveries = new SortedList<>(filteredDeliveries);
    }

    @Override
    public void sortFilteredDeliveryList(Comparator<Delivery> comparator) {
        requireNonNull(comparator);
        sortedDeliveries.setComparator(comparator);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
            && deliveryBook.equals(otherModelManager.deliveryBook)
            && userPrefs.equals(otherModelManager.userPrefs)
            && filteredCustomers.equals(otherModelManager.filteredCustomers)
            && filteredDeliveries.equals(otherModelManager.filteredDeliveries)
            && isLoggedIn == otherModelManager.isLoggedIn;
    }

}
