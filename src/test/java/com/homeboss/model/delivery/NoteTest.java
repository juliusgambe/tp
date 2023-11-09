package com.homeboss.model.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.testutil.Assert.assertThrows;

import com.homeboss.testutil.Assert;
import org.junit.jupiter.api.Test;

public class NoteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Note(invalidName));
    }

    @Test
    public void isValid() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Note.isValid(null));

        // invalid note -> returns false
        assertFalse(Note.isValid("")); // empty string

        // valid note -> returns false
        assertTrue(Note.isValid("peter jack")); // alphabets only

        // valid note -> returns false
        assertTrue(Note.isValid("az 09")); // alphanumeric

        // valid note -> returns false
        assertTrue(Note.isValid("AZ 123")); // alphanumeric with capital letters

        // invalid note -> returns false
        assertFalse(Note.isValid("@")); // includes special characters, boundary of ASCII A

        // invalid note -> returns false
        assertFalse(Note.isValid("[")); // includes special characters, boundary of ASCII Z

        // invalid note -> returns false
        assertFalse(Note.isValid("`")); // includes special characters, boundary of ASCII a

        // invalid note -> returns false
        assertFalse(Note.isValid("{")); // includes special characters, boundary of ASCII z

        // invalid note -> returns false
        assertFalse(Note.isValid("/")); // includes special characters, boundary of ASCII 0

        // invalid note -> returns false
        assertFalse(Note.isValid(":")); // includes special characters, boundary of ASCII 9

    }

    @Test
    public void equals() {
        Note note = new Note("Valid Name");

        // same values -> returns true
        assertTrue(note.equals(new Note("Valid Name")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Note("Other Valid Name")));
    }
}
