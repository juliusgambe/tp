package com.homeboss.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static com.homeboss.testutil.Assert.assertThrows;
import static com.homeboss.testutil.TypicalDeliveries.GAMBES_RICE;
import static com.homeboss.testutil.TypicalDeliveries.JY_CAKE;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.homeboss.commons.exceptions.DataLoadingException;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.ReadOnlyBook;
import com.homeboss.testutil.Assert;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.homeboss.model.delivery.Delivery;

public class JsonDeliveryBookStorageTest {
    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonDeliveryBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void getBookFilePath_returnsFilePath() {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        JsonDeliveryBookStorage jsonDeliveryBookStorage = new JsonDeliveryBookStorage(filePath);
        assertEquals(jsonDeliveryBookStorage.getBookFilePath(), filePath);
    }

    @Test
    public void getBookParentPath_returnsParentPath() {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        JsonDeliveryBookStorage jsonDeliveryBookStorage = new JsonDeliveryBookStorage(filePath);
        assertEquals(jsonDeliveryBookStorage.getBookParentPath(), filePath.getParent());
    }

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readDeliveryBook(null));
    }

    private java.util.Optional<ReadOnlyBook<Delivery>> readDeliveryBook(String filePath) throws Exception {
        JsonDeliveryBookStorage jsonDeliveryBookStorage = new JsonDeliveryBookStorage(Paths.get(filePath));
        jsonDeliveryBookStorage.setReferencingBook(TypicalPersons.getTypicalAddressBook());
        return jsonDeliveryBookStorage.readBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDeliveryBook("NonExistentFile.json").isPresent());
    }
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataLoadingException.class, () -> readDeliveryBook("notJsonFormatDeliveryBook.json"));
    }

    @Test
    public void readAddressBook_invalidDeliveryAddressBook_throwDataLoadingException() {
        Assert.assertThrows(DataLoadingException.class, () -> readDeliveryBook("invalidDeliveryBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidDeliveryAddressBook_throwDataLoadingException() {
        Assert.assertThrows(DataLoadingException.class, () -> readDeliveryBook("invalidAndValidDeliveryBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        DeliveryBook original = getTypicalDeliveryBook();
        JsonDeliveryBookStorage jsonDeliveryBookStorage = new JsonDeliveryBookStorage(filePath);
        jsonDeliveryBookStorage.setReferencingBook(TypicalPersons.getTypicalAddressBook());

        // Save in new file and read back
        jsonDeliveryBookStorage.saveBook(original, filePath);
        ReadOnlyBook<Delivery> readBack = jsonDeliveryBookStorage.readBook(filePath).get();
        assertEquals(original, new DeliveryBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addDelivery(JY_CAKE);
        original.removeDelivery(GAMBES_RICE);
        jsonDeliveryBookStorage.saveBook(original, filePath);
        readBack = jsonDeliveryBookStorage.readBook(filePath).get();
        assertEquals(original, new DeliveryBook(readBack));

        // Save and read without specifying file path
        original.addDelivery(GAMBES_RICE);
        jsonDeliveryBookStorage.saveBook(original); // file path not specified
        readBack = jsonDeliveryBookStorage.readBook().get(); // file path not specified
        assertEquals(original, new DeliveryBook(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveDeliveryBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveDeliveryBook(ReadOnlyBook<Delivery> deliveryBook, String filePath) {
        try {
            new JsonDeliveryBookStorage(Paths.get(filePath))
                .saveBook(deliveryBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveDeliveryBook(new DeliveryBook(), null));
    }

}
