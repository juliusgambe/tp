package com.homeboss.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.testutil.TypicalUsers.AARON;
import static com.homeboss.testutil.TypicalUsers.FOODBEAR;

import org.junit.jupiter.api.Test;

import com.homeboss.testutil.UserBuilder;

public class UserTest {

    @Test
    public void isSameUser() {
        // same object -> returns true
        assertTrue(AARON.hasSameUsername(AARON));

        // null -> returns false
        assertFalse(AARON.hasSameUsername(null));

        // different user -> returns false
        assertFalse(AARON.hasSameUsername(FOODBEAR));

        // same name, different password -> returns true
        User editedAaron = new UserBuilder(AARON).withPassword("aDifferentPassword").build();
        assertTrue(AARON.hasSameUsername(editedAaron));

        // different name, same password -> returns false
        editedAaron = new UserBuilder(AARON).withUsername("aDifferentUsername").build();
        assertFalse(AARON.hasSameUsername(editedAaron));

        // same name, same password -> returns true
        User newAaron = new UserBuilder(AARON).build();
        assertTrue(AARON.hasSameUsername(newAaron));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(AARON.equals(AARON));

        // null -> returns false
        assertFalse(AARON.equals(null));

        // different user -> returns false
        assertFalse(AARON.equals(FOODBEAR));

        // same name, different password -> returns false
        User editedAaron = new UserBuilder(AARON).withPassword("aDifferentPassword").build();
        assertFalse(AARON.equals(editedAaron));

        // different name, same password -> returns false
        editedAaron = new UserBuilder(AARON).withUsername("aDifferentUsername").build();
        assertFalse(AARON.equals(editedAaron));

        // same name, same password -> returns true
        User newAaron = new UserBuilder(AARON).build();
        assertTrue(AARON.equals(newAaron));
    }
}
