package com.homeboss.logic.commands.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.testutil.UpdateUserDescriptorBuilder;

public class UserUpdateDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UserUpdateCommand.UserUpdateDescriptor descriptorWithSameValues = new UserUpdateCommand.UserUpdateDescriptor(
                CommandTestUtil.DESC_AARON);
        assertTrue(CommandTestUtil.DESC_AARON.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_AARON.equals(CommandTestUtil.DESC_AARON));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_AARON.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_AARON.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_AARON.equals(CommandTestUtil.DESC_FOODBEAR));

        // different username -> returns false
        UserUpdateCommand.UserUpdateDescriptor editedAaron = new UpdateUserDescriptorBuilder(CommandTestUtil.DESC_AARON)
                .withUsername(CommandTestUtil.VALID_USERNAME_FOODBEAR).build();
        assertFalse(CommandTestUtil.DESC_AARON.equals(editedAaron));

        // different password -> returns false
        editedAaron = new UpdateUserDescriptorBuilder(CommandTestUtil.DESC_AARON).withPassword(
                CommandTestUtil.VALID_PASSWORD_FOODBEAR).build();
        assertFalse(CommandTestUtil.DESC_AARON.equals(editedAaron));

        // different secret question -> returns false
        editedAaron = new UpdateUserDescriptorBuilder(CommandTestUtil.DESC_AARON)
                .withSecretQuestion(CommandTestUtil.VALID_SECRET_QUESTION_FOODBEAR).build();
        assertFalse(CommandTestUtil.DESC_AARON.equals(editedAaron));

        // different answer -> returns false
        editedAaron = new UpdateUserDescriptorBuilder(CommandTestUtil.DESC_AARON).withAnswer(
                CommandTestUtil.VALID_ANSWER_FOODBEAR).build();
        assertFalse(CommandTestUtil.DESC_AARON.equals(editedAaron));
    }

    @Test
    public void toStringMethod() {
        UserUpdateCommand.UserUpdateDescriptor userUpdateDescriptor = new UserUpdateCommand.UserUpdateDescriptor();
        String expected = UserUpdateCommand.UserUpdateDescriptor.class.getCanonicalName() + "{username="
                + userUpdateDescriptor.getUsername().orElse(null) + ", secretQuestion="
                + userUpdateDescriptor.getSecretQuestion().orElse(null) + ", answer="
                + userUpdateDescriptor.getAnswer().orElse(null) + "}";
        assertEquals(expected, userUpdateDescriptor.toString());
    }
}
