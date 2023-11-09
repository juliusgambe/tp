package com.homeboss.model.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.testutil.Assert.assertThrows;
import static com.homeboss.testutil.TypicalDeliveries.GABRIELS_MILK;
import static com.homeboss.testutil.TypicalDeliveries.GAMBES_RICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.delivery.exceptions.DeliveryNotFoundException;
import com.homeboss.model.delivery.exceptions.DuplicateDeliveryException;
import com.homeboss.testutil.Assert;
import org.junit.jupiter.api.Test;

import com.homeboss.testutil.DeliveryBuilder;

public class UniqueDeliveryListTest {

    private final UniqueDeliveryList uniqueDeliveryList = new UniqueDeliveryList();

    @Test
    public void contains_nullDelivery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.contains(null));
    }

    @Test
    public void contains_deliveryNotInList_returnsFalse() {
        assertFalse(uniqueDeliveryList.contains(GABRIELS_MILK));
    }

    @Test
    public void contains_deliveryInList_returnsTrue() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        assertTrue(uniqueDeliveryList.contains(GABRIELS_MILK));
    }

    @Test
    public void contains_deliveryWithSameIdDifferentFields_returnsTrue() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        Delivery delivery = new DeliveryBuilder(GAMBES_RICE).withId(1).build();
        assertTrue(uniqueDeliveryList.contains(delivery));
    }

    @Test
    public void add_nullDelivery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.add(null));
    }

    @Test
    public void add_duplicateDelivery_throwsDuplicateDeliveryException() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        Assert.assertThrows(DuplicateDeliveryException.class, () -> uniqueDeliveryList.add(GABRIELS_MILK));
    }

    @Test
    public void add_deliveryWithSameIdDifferentFields_throwsDuplicateDeliveryException() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        Delivery delivery = new DeliveryBuilder().withId(1).build();
        Assert.assertThrows(DuplicateDeliveryException.class, () -> uniqueDeliveryList.add(delivery));
    }

    @Test
    public void setDelivery_nullTargetDelivery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.setDelivery(null, GABRIELS_MILK));
    }

    @Test
    public void setDelivery_nullEditedDelivery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.setDelivery(GABRIELS_MILK, null));
    }

    @Test
    public void setDelivery_targetDeliveryNotInList_throwsDeliveryNotFoundException() {
        Assert.assertThrows(DeliveryNotFoundException.class, () -> uniqueDeliveryList.setDelivery(GABRIELS_MILK,
            GABRIELS_MILK));
    }

    @Test
    public void setDelivery_editedDeliveryIsSameDelivery_success() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        uniqueDeliveryList.setDelivery(GABRIELS_MILK, GABRIELS_MILK);
        UniqueDeliveryList expectedUniqueDeliveryList = new UniqueDeliveryList();
        expectedUniqueDeliveryList.add(GABRIELS_MILK);
        assertEquals(expectedUniqueDeliveryList, uniqueDeliveryList);
    }

    @Test
    public void setDelivery_editedDeliveryHasSameIdentity_success() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        Delivery editedDelivery = new DeliveryBuilder(GABRIELS_MILK).withName(CommandTestUtil.VALID_NAME_JAMES_MILK).build();
        uniqueDeliveryList.setDelivery(GABRIELS_MILK, editedDelivery);
        UniqueDeliveryList expectedUniqueDeliveryList = new UniqueDeliveryList();
        expectedUniqueDeliveryList.add(editedDelivery);
        assertEquals(expectedUniqueDeliveryList, uniqueDeliveryList);
    }

    @Test
    public void setDelivery_editedDeliveryHasDifferentIdentity_success() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        uniqueDeliveryList.setDelivery(GABRIELS_MILK, GAMBES_RICE);
        UniqueDeliveryList expectedUniqueDeliveryList = new UniqueDeliveryList();
        expectedUniqueDeliveryList.add(GAMBES_RICE);
        assertEquals(expectedUniqueDeliveryList, uniqueDeliveryList);
    }

    @Test
    public void setDelivery_editedDeliveryHasNonUniqueIdentity_throwsDuplicateDeliveryException() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        uniqueDeliveryList.add(GAMBES_RICE);
        Assert.assertThrows(DuplicateDeliveryException.class, ()
            -> uniqueDeliveryList.setDelivery(GABRIELS_MILK, GAMBES_RICE));
    }

    @Test
    public void remove_nullDelivery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.remove(null));
    }

    @Test
    public void remove_deliveryDoesNotExists_throwsDeliveryNotFoundException() {
        Assert.assertThrows(DeliveryNotFoundException.class, () -> uniqueDeliveryList.remove(GABRIELS_MILK));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        uniqueDeliveryList.remove(GABRIELS_MILK);
        UniqueDeliveryList expectedUniqueDeliveryList = new UniqueDeliveryList();
        assertEquals(expectedUniqueDeliveryList, uniqueDeliveryList);
    }

    @Test
    public void setDeliveries_nullUniqueDeliveryList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.setDeliveries((UniqueDeliveryList) null));
    }

    @Test
    public void setDeliveries_uniqueDeliveryList_replacesOwnListWithProvidedUniqueDeliveryList() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        UniqueDeliveryList expectedUniqueDeliveryList = new UniqueDeliveryList();
        expectedUniqueDeliveryList.add(GAMBES_RICE);
        uniqueDeliveryList.setDeliveries(expectedUniqueDeliveryList);
        assertEquals(expectedUniqueDeliveryList, uniqueDeliveryList);
    }

    @Test
    public void setDeliveries_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueDeliveryList.setDeliveries((List<Delivery>) null));
    }

    @Test
    public void setDeliveries_list_replacesOwnListWithProvidedList() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        List<Delivery> deliveryList = Collections.singletonList(GAMBES_RICE);
        uniqueDeliveryList.setDeliveries(deliveryList);
        UniqueDeliveryList expectedUniqueDeliveryList = new UniqueDeliveryList();
        expectedUniqueDeliveryList.add(GAMBES_RICE);
        assertEquals(expectedUniqueDeliveryList, uniqueDeliveryList);
    }

    @Test
    public void setDeliveries_listWithDuplicateDeliveries_throwsDuplicatePersonException() {
        List<Delivery> deliveryListWithDuplicateDeliveries = Arrays.asList(GABRIELS_MILK, GABRIELS_MILK);
        Assert.assertThrows(DuplicateDeliveryException.class, ()
            -> uniqueDeliveryList.setDeliveries(deliveryListWithDuplicateDeliveries));
    }

    @Test
    public void getById_validId_givesPresentDeliveryOptional() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        Optional<Delivery> d = uniqueDeliveryList.getById(GABRIELS_MILK.getDeliveryId());
        assertTrue(d.isPresent());
        assertEquals(d.get(), GABRIELS_MILK);
    }

    @Test
    public void getById_negativeId_givesEmptyDeliveryOptional() {
        Optional<Delivery> d = uniqueDeliveryList.getById(-1);
        assertTrue(d.isEmpty());
    }

    @Test
    public void getById_invalidId_givesEmptyDeliveryOptional() {
        uniqueDeliveryList.add(GABRIELS_MILK);
        Optional<Delivery> d = uniqueDeliveryList.getById(GABRIELS_MILK.getDeliveryId() + 1);
        assertTrue(d.isEmpty());
    }

    @Test
    public void asUnmodifiableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueDeliveryList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueDeliveryList.asUnmodifiableObservableList().toString(), uniqueDeliveryList.toString());
    }
}
