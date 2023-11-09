package com.homeboss.logic.commands.customer;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.CustomerBuilder;
import com.homeboss.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class CustomerAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(),
                true);
    }

    @Test
    public void execute_newPerson_success() {
        Customer validCustomer = new CustomerBuilder().withCustomerId(50).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), model.getUserLoginStatus());
        expectedModel.addPerson(validCustomer);

        assertCommandSuccess(new CustomerAddCommand(validCustomer), model,
                String.format(CustomerAddCommand.MESSAGE_SUCCESS, Messages.format(validCustomer)),
                expectedModel, true);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer customerInList = model.getAddressBook().getList().get(0);
        CommandTestUtil.assertCommandFailure(new CustomerAddCommand(customerInList), model,
                CustomerAddCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_duplicatePersonWithSamePhone_throwsCommandException() {
        // same phone, different name and customerId
        Customer customerInList = model.getAddressBook().getList().get(0);
        Customer customerWithSamePhone = new CustomerBuilder(customerInList).withName("Different Name")
                .withCustomerId(51).build();
        CommandTestUtil.assertCommandFailure(new CustomerAddCommand(customerWithSamePhone), model,
                CustomerAddCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_newPersonLoggedOut_failure() {
        model.setLogoutSuccess();
        Customer validCustomer = new CustomerBuilder().withCustomerId(50).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getDeliveryBook(),
                new UserPrefs(), model.getUserLoginStatus());
        expectedModel.addPerson(validCustomer);

        CommandTestUtil.assertCommandFailure(new CustomerAddCommand(validCustomer), model,
                Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_duplicatePersonLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        Customer customerInList = model.getAddressBook().getList().get(0);
        CommandTestUtil.assertCommandFailure(new CustomerAddCommand(customerInList), model,
                Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

}
