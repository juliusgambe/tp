package com.homeboss.logic.commands.customer;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.AddressBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.testutil.TypicalDeliveries;
import com.homeboss.testutil.TypicalIndexes;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CustomerListCommand.
 */
public class CustomerListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(
            TypicalPersons.getTypicalAddressBook(), TypicalDeliveries.getTypicalDeliveryBook(), new UserPrefs(), true);
        expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
            new UserPrefs(), model.getUserLoginStatus());
    }

    @Test
    public void execute_notLoggedIn_throwsCommandException() {
        Model emptyModel =
            new ModelManager(new AddressBook(), TypicalDeliveries.getTypicalDeliveryBook(), new UserPrefs(), false);
        CommandTestUtil.assertCommandFailure(
            new CustomerListCommand(),
            emptyModel,
            Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }
    @Test
    public void execute_customerListIsEmpty_showsEmpty() {
        Model emptyModel =
            new ModelManager(new AddressBook(), TypicalDeliveries.getTypicalDeliveryBook(), new UserPrefs(), true);
        CommandTestUtil.assertCommandListSuccess(
            new CustomerListCommand(),
            emptyModel,
            CustomerListCommand.MESSAGE_EMPTY, emptyModel);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandListSuccess(
            new CustomerListCommand(),
            model,
            CustomerListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        CommandTestUtil.assertCommandListSuccess(
            new CustomerListCommand(),
            model,
            CustomerListCommand.MESSAGE_SUCCESS, expectedModel);

    }
}
