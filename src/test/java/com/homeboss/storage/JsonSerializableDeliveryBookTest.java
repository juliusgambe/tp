package com.homeboss.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.homeboss.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.homeboss.commons.exceptions.IllegalValueException;
import com.homeboss.commons.util.JsonUtil;
import com.homeboss.model.DeliveryBook;
import com.homeboss.testutil.Assert;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.testutil.TypicalDeliveries;

public class JsonSerializableDeliveryBookTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializableDeliveryBookTest");
    private static final Path TYPICAL_DELIVERIES_FILE =
        TEST_DATA_FOLDER.resolve("typicalDeliveriesDeliveryBook.json");
    private static final Path INVALID_DELIVERY_FILE =
        TEST_DATA_FOLDER.resolve("invalidDeliveryDeliveryBook.json");
    private static final Path DUPLICATE_DELIVERY_FILE =
        TEST_DATA_FOLDER.resolve("duplicateDeliveryBook.json");

    @Test
    public void toModelType_typicalDeliveriesFile_success() throws Exception {
        JsonSerializableDeliveryBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_DELIVERIES_FILE,
            JsonSerializableDeliveryBook.class).get();
        DeliveryBook deliveryBookFromFile =
            dataFromFile.toModelType(Optional.of(TypicalPersons.getTypicalAddressBook()));
        DeliveryBook typicalDeliveriesDeliveryBook = TypicalDeliveries.getTypicalDeliveryBook();
        assertEquals(deliveryBookFromFile, typicalDeliveriesDeliveryBook);
    }

    @Test
    public void toModelType_invalidDeliveriesFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDeliveryBook dataFromFile = JsonUtil.readJsonFile(INVALID_DELIVERY_FILE,
            JsonSerializableDeliveryBook.class).get();
        Assert.assertThrows(IllegalValueException.class, ()
            -> dataFromFile.toModelType(Optional.of(TypicalPersons.getTypicalAddressBook())));
    }

    @Test
    public void toModelType_duplicateDeliveries_throwsIllegalValueException() throws Exception {
        JsonSerializableDeliveryBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DELIVERY_FILE,
            JsonSerializableDeliveryBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableDeliveryBook.MESSAGE_DUPLICATE_DELIVERY, ()
            -> dataFromFile.toModelType(Optional.of(TypicalPersons.getTypicalAddressBook())));
    }
}
