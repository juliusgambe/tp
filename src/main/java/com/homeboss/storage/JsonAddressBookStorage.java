package com.homeboss.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.homeboss.commons.core.LogsCenter;
import com.homeboss.commons.exceptions.DataLoadingException;
import com.homeboss.commons.exceptions.IllegalValueException;
import com.homeboss.commons.util.FileUtil;
import com.homeboss.commons.util.JsonUtil;
import com.homeboss.model.person.Customer;
import com.homeboss.model.ReadOnlyBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements BookStorage<Customer> {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookFilePath() {
        return filePath;
    }

    public Path getBookParentPath() {
        return filePath.getParent();
    }

    @Override
    public Optional<ReadOnlyBook<Customer>> readBook() throws DataLoadingException {
        return readBook(filePath);
    }

    /**
     * Similar to {@link #readBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyBook<Customer>> readBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveBook(ReadOnlyBook<Customer> addressBook) throws IOException {
        saveBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveBook(ReadOnlyBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBook(ReadOnlyBook<Customer> addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

}
