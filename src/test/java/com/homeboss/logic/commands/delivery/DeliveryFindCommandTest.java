package com.homeboss.logic.commands.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.GABRIELS_MILK;
import static com.homeboss.testutil.TypicalDeliveries.GAMBES_RICE;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.util.Arrays;
import java.util.Collections;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.model.delivery.DeliveryNameContainsKeywordsPredicate;

public class DeliveryFindCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
        new UserPrefs(), true);
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
        new UserPrefs(), true);

    @Test
    public void equals() {
        DeliveryNameContainsKeywordsPredicate firstPredicate =
            new DeliveryNameContainsKeywordsPredicate(Collections.singletonList("first"));
        DeliveryNameContainsKeywordsPredicate secondPredicate =
            new DeliveryNameContainsKeywordsPredicate(Collections.singletonList("second"));

        DeliveryFindCommand deliveryFindFirstCommand = new DeliveryFindCommand(firstPredicate);
        DeliveryFindCommand deliveryFindSecondCommand = new DeliveryFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(deliveryFindFirstCommand.equals(deliveryFindFirstCommand));

        // same values -> returns true
        DeliveryFindCommand findFirstCommandCopy = new DeliveryFindCommand(firstPredicate);
        assertTrue(deliveryFindFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(deliveryFindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deliveryFindFirstCommand.equals(null));

        // different query -> returns false
        assertFalse(deliveryFindFirstCommand.equals(deliveryFindSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDeliveryFound() {
        String expectedMessage = String.format(Messages.MESSAGE_DELIVERY_LISTED_OVERVIEW, 0);
        DeliveryNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Collections.emptyList(), model.getFilteredDeliveryList());
    }

    @Test
    public void execute_multipleKeywords_multipleDeliveriesFound() {
        String expectedMessage = String.format(Messages.MESSAGE_DELIVERY_LISTED_OVERVIEW, 2);
        DeliveryNameContainsKeywordsPredicate predicate = preparePredicate("Gabriel Gambe");
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GABRIELS_MILK, GAMBES_RICE), model.getFilteredDeliveryList());
    }

    @Test
    public void execute_zeroKeywordsLoggedOut_failure() {
        model.setLogoutSuccess();
        expectedModel.setLogoutSuccess();
        DeliveryNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);
        CommandTestUtil.assertCommandFailure(command, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_multipleKeywordsLoggedOut_failure() {
        model.setLogoutSuccess();
        expectedModel.setLogoutSuccess();
        DeliveryNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);
        CommandTestUtil.assertCommandFailure(command, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    /**
     * Parses {@code userInput} into a {@code DeliveryNameContainsKeywordsPredicate}.
     */
    private DeliveryNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DeliveryNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
