package com.homeboss.logic.commands.delivery;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.Assert.assertThrows;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.homeboss.commons.core.index.Index;
import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.ClearCommand;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.exceptions.CommandException;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.person.Customer;
import com.homeboss.testutil.CustomerBuilder;
import com.homeboss.testutil.DeliveryBuilder;
import com.homeboss.testutil.DeliveryEditDescriptorBuilder;
import com.homeboss.testutil.TypicalIndexes;
import com.homeboss.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class DeliveryEditCommandTest {
    private Model modelLoggedIn = new ModelManager(
            TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(),
            true);

    private Model modelLoggedOut = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(),
            new UserPrefs(), false);

    @Test
    public void constructor_nullDeliveryEditDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryEditCommand(null, null));
    }

    @Test
    public void execute_allFieldsSpecified_success() {

        Customer editedCustomer = modelLoggedIn.getFilteredPersonList().get(0);
        Delivery editedDelivery =
                new DeliveryBuilder().withCustomer(editedCustomer).withOrderDate("2021-12-12")
                        .withNote("TestFF").withName("Vanilla Cake")
                        .withDeliveryDate("2027-12-12").withStatus(CommandTestUtil.VALID_STATUS_SHIPPED).build();

        DeliveryEditCommand.DeliveryEditDescriptor descriptor = new DeliveryEditDescriptorBuilder(
                editedDelivery).build();
        DeliveryEditCommand editCommand = new DeliveryEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(DeliveryEditCommand.MESSAGE_EDIT_DELIVERY_SUCCESS,
                Messages.format(editedDelivery));

        Model expectedModel = new ModelManager(new AddressBook(modelLoggedIn.getAddressBook()),
                new DeliveryBook(modelLoggedIn.getDeliveryBook()),
                new UserPrefs(), modelLoggedIn.getUserLoginStatus());

        expectedModel.setDelivery(modelLoggedIn.getFilteredDeliveryList().get(0), editedDelivery);

        assertCommandSuccess(editCommand, modelLoggedIn, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidDelivery_throwsCommandException() {
        CustomerBuilder personBuilder = new CustomerBuilder();
        Customer validCustomer = personBuilder.withCustomerId(CommandTestUtil.VALID_CUSTOMER_ID_1).build();

        Delivery editedDelivery =
                new DeliveryBuilder().withCustomer(validCustomer).withOrderDate("2021-12-12")
                        .withNote("TestFF").withName("Vanilla Cake")
                        .withDeliveryDate("2027-12-12").withStatus(CommandTestUtil.VALID_STATUS_SHIPPED).build();

        DeliveryEditCommand.DeliveryEditDescriptor descriptor = new DeliveryEditDescriptorBuilder(
                editedDelivery).build();
        DeliveryEditCommand editCommand = new DeliveryEditCommand(TypicalIndexes.INDEX_TOO_LARGE, descriptor);


        Model expectedModel = new ModelManager(new AddressBook(modelLoggedIn.getAddressBook()),
                new DeliveryBook(modelLoggedIn.getDeliveryBook()),
                new UserPrefs(), modelLoggedIn.getUserLoginStatus());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX, () ->
                editCommand.execute(expectedModel));
    }

    @Test
    public void execute_invalidDeliveryDate_throwsCommandException() { //not done
        CustomerBuilder personBuilder = new CustomerBuilder();
        Customer validCustomer = personBuilder.withCustomerId(CommandTestUtil.VALID_CUSTOMER_ID_1).build();

        Delivery editedDelivery =
                new DeliveryBuilder().withCustomer(validCustomer).withOrderDate("2021-12-12")
                        .withNote("TestFF").withName("Vanilla Cake")
                        .withDeliveryDate(CommandTestUtil.INVALID_DELIVERY_DATE)
                        .withStatus(CommandTestUtil.VALID_STATUS_SHIPPED).build();

        DeliveryEditCommand.DeliveryEditDescriptor descriptor = new DeliveryEditDescriptorBuilder(
                editedDelivery).build();
        DeliveryEditCommand editCommand = new DeliveryEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);


        Model expectedModel = new ModelManager(new AddressBook(modelLoggedIn.getAddressBook()),
                new DeliveryBook(modelLoggedIn.getDeliveryBook()),
                new UserPrefs(), modelLoggedIn.getUserLoginStatus());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DELIVERY_DATE, () ->
                editCommand.execute(expectedModel));
    }

    //need to check if this is ok
    @Test
    public void execute_invalidCustomerId_throwsCommandException() { //not done
        CustomerBuilder personBuilder = new CustomerBuilder();
        Customer invalidCustomer = personBuilder.withCustomerId(CommandTestUtil.TOO_LARGE_CUSTOMER_ID).build();

        Delivery editedDelivery =
                new DeliveryBuilder().withCustomer(invalidCustomer).withOrderDate("2021-12-12")
                        .withNote("TestFF").withName("Vanilla Cake")
                        .withDeliveryDate(CommandTestUtil.INVALID_DELIVERY_DATE)
                        .withStatus(CommandTestUtil.VALID_STATUS_SHIPPED).build();

        DeliveryEditCommand.DeliveryEditDescriptor descriptor = new DeliveryEditDescriptorBuilder(
                editedDelivery).build();
        DeliveryEditCommand editCommand = new DeliveryEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);


        Model expectedModel = new ModelManager(new AddressBook(modelLoggedIn.getAddressBook()),
                new DeliveryBook(modelLoggedIn.getDeliveryBook()),
                new UserPrefs(), modelLoggedIn.getUserLoginStatus());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, () ->
                editCommand.execute(expectedModel));
    }

    @Test
    public void execute_deliveryAcceptedByModelLoggedOut_addFailure() {
        CustomerBuilder personBuilder = new CustomerBuilder();
        Customer validCustomer = personBuilder.build();


        Delivery validDelivery = new DeliveryBuilder().withCustomer(validCustomer).build();

        DeliveryEditCommand deliveryEditCommand = new DeliveryEditCommand(TypicalIndexes.INDEX_FIRST_PERSON, new
                DeliveryEditDescriptorBuilder(validDelivery).build());
        assertThrows(CommandException.class, Messages.MESSAGE_USER_NOT_AUTHENTICATED, () ->
                deliveryEditCommand.execute(modelLoggedOut));

    }

    @Test
    public void equals() {
        final DeliveryEditCommand standardCommand = new DeliveryEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_EDIT_CHIPS);

        // same values -> returns true
        DeliveryEditCommand.DeliveryEditDescriptor copyDescriptor = new DeliveryEditCommand.DeliveryEditDescriptor(
                CommandTestUtil.DESC_EDIT_CHIPS);
        DeliveryEditCommand commandWithSameValues = new DeliveryEditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeliveryEditCommand(
                TypicalIndexes.INDEX_SECOND_PERSON, CommandTestUtil.DESC_EDIT_CHIPS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new DeliveryEditCommand(
                TypicalIndexes.INDEX_FIRST_PERSON, CommandTestUtil.DESC_EDIT_MILK)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        DeliveryEditCommand.DeliveryEditDescriptor deliveryEditDescriptor =
                new DeliveryEditCommand.DeliveryEditDescriptor();
        DeliveryEditCommand editCommand = new DeliveryEditCommand(index, deliveryEditDescriptor);
        String expected = DeliveryEditCommand.class.getCanonicalName() + "{id=" + index + ", deliveryEditDescriptor="
                + deliveryEditDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}

