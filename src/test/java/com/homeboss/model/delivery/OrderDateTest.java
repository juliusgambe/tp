package com.homeboss.model.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.testutil.Assert.assertThrows;

import java.time.DateTimeException;

import com.homeboss.testutil.Assert;
import org.junit.jupiter.api.Test;

public class OrderDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new OrderDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new OrderDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> OrderDate.isValidDate(null));

        // invalid date
        assertFalse(OrderDate.isValidDate("")); // empty string
        assertFalse(OrderDate.isValidDate(" ")); // spaces only
        assertFalse(OrderDate.isValidDate("10-12-2023")); // wrong format
        assertFalse(OrderDate.isValidDate("2023=12+32")); // wrong format
        assertFalse(OrderDate.isValidDate("2023-12")); // missing day

        // valid date
        assertTrue(OrderDate.isValidDate("2023-12-12")); // correct date
        assertTrue(OrderDate.isValidDate("2021-12-12")); // correct date
    }

    @Test
    public void isNotPastDate() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> OrderDate.isPastDate(null));

        // past date
        assertTrue(OrderDate.isPastDate("2020-12-12")); // past date

        // future date
        assertFalse(OrderDate.isPastDate("2023-12-12")); // future date
    }

    @Test
    public void isValidOrderDate() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> OrderDate.isValidOrderDate(null));

        // invalid date
        assertFalse(OrderDate.isValidOrderDate("")); // empty string
        assertFalse(OrderDate.isValidOrderDate(" ")); // spaces only
        assertFalse(OrderDate.isValidOrderDate("10-12-2023")); // wrong format
        assertFalse(OrderDate.isValidOrderDate("2023=12+32")); // wrong format
        assertFalse(OrderDate.isValidOrderDate("2023-12")); // missing day
        Assert.assertThrows(DateTimeException.class, () -> OrderDate.isValidOrderDate("2023-13-12")); // wrong month
        Assert.assertThrows(DateTimeException.class, () -> OrderDate.isValidOrderDate("2023-12-32")); // wrong day
        assertFalse(OrderDate.isValidOrderDate("2023-12-12")); // correct date but past date

        // valid date
        assertTrue(OrderDate.isValidOrderDate("2023-08-13")); // correct date
    }
}
