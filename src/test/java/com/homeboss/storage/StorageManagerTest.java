package com.homeboss.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.nio.file.Path;

import com.homeboss.model.person.Customer;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.homeboss.commons.core.GuiSettings;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.ReadOnlyBook;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonDeliveryBookStorage deliveryBookStorage = new JsonDeliveryBookStorage(getTempFilePath("db"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, deliveryBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook original = TypicalPersons.getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyBook<Customer> retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void getAddressBookParentPath() {
        assertNotNull(storageManager.getAddressBookParentPath());
    }

    @Test
    public void deliveryBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDeliveryBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonDeliveryBookStorageTest} class.
         */
        DeliveryBook original = getTypicalDeliveryBook();
        storageManager.setDeliveryBookReference(TypicalPersons.getTypicalAddressBook());
        storageManager.saveDeliveryBook(original);
        ReadOnlyBook<Delivery> retrieved = storageManager.readDeliveryBook().get();
        assertEquals(original, new DeliveryBook(retrieved));
    }

    @Test
    public void getDeliveryBookFilePath() {
        assertNotNull(storageManager.getDeliveryBookFilePath());
    }

}
