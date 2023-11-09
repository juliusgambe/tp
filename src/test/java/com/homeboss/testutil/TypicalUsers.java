package com.homeboss.testutil;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.user.User;

/**
 * A utility class containing a list of {@code User} objects to be used in tests.
 */
public class TypicalUsers {

    public static final User ANDY = new UserBuilder().withUsername("andy").withPassword("abcd1234")
            .withSecretQuestion("What is your favourite food?").withAnswer("Pizza").build();
    public static final User CARL = new UserBuilder().withUsername("car1").withPassword("1234ABcd")
            .withSecretQuestion("What is your favourite module?").withAnswer("CS2103T").build();

    // Manually added
    public static final User AARON = new UserBuilder().withUsername(CommandTestUtil.VALID_USERNAME_AARON)
            .withPassword(CommandTestUtil.VALID_HASHED_PASSWORD_AARON)
            .withSecretQuestion(CommandTestUtil.VALID_SECRET_QUESTION_AARON)
            .withAnswer(CommandTestUtil.VALID_ANSWER_AARON).build();

    public static final User FOODBEAR = new UserBuilder().withUsername(CommandTestUtil.VALID_USERNAME_FOODBEAR)
            .withPassword(CommandTestUtil.VALID_HASHED_PASSWORD_FOODBEAR)
            .withSecretQuestion(CommandTestUtil.VALID_SECRET_QUESTION_FOODBEAR)
            .withAnswer(CommandTestUtil.VALID_ANSWER_FOODBEAR).build();

    private TypicalUsers() {
    } // prevents instantiation
}
