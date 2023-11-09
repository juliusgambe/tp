package com.homeboss.logic.parser;

import static com.homeboss.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.homeboss.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_ANSWER;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_DATE;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_PASSWORD_CONFIRM;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_SECRET_QUESTION;
import static com.homeboss.logic.parser.CliSyntax.PREFIX_USER;
import static com.homeboss.testutil.Assert.assertThrows;
import static com.homeboss.testutil.TypicalDeliveries.GABRIELS_MILK;
import static com.homeboss.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.homeboss.logic.commands.ClearCommand;
import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.logic.commands.ExitCommand;
import com.homeboss.logic.commands.HelpCommand;
import com.homeboss.logic.commands.customer.CustomerAddCommand;
import com.homeboss.logic.commands.customer.CustomerDeleteCommand;
import com.homeboss.logic.commands.customer.CustomerEditCommand;
import com.homeboss.logic.commands.customer.CustomerEditCommand.CustomerEditDescriptor;
import com.homeboss.logic.commands.customer.CustomerFindCommand;
import com.homeboss.logic.commands.customer.CustomerListCommand;
import com.homeboss.logic.commands.customer.CustomerViewCommand;
import com.homeboss.logic.commands.delivery.DeliveryAddCommand;
import com.homeboss.logic.commands.delivery.DeliveryAddCommand.DeliveryAddDescriptor;
import com.homeboss.logic.commands.delivery.DeliveryCreateNoteCommand;
import com.homeboss.logic.commands.delivery.DeliveryDeleteCommand;
import com.homeboss.logic.commands.delivery.DeliveryEditCommand;
import com.homeboss.logic.commands.delivery.DeliveryEditCommand.DeliveryEditDescriptor;
import com.homeboss.logic.commands.delivery.DeliveryFindCommand;
import com.homeboss.logic.commands.delivery.DeliveryStatusCommand;
import com.homeboss.logic.commands.delivery.DeliveryViewCommand;
import com.homeboss.logic.commands.user.UserDeleteCommand;
import com.homeboss.logic.commands.user.UserLoginCommand;
import com.homeboss.logic.commands.user.UserLogoutCommand;
import com.homeboss.logic.commands.user.UserRecoverAccountCommand;
import com.homeboss.logic.commands.user.UserRegisterCommand;
import com.homeboss.logic.commands.user.UserUpdateCommand;
import com.homeboss.logic.commands.user.UserUpdateCommand.UserUpdateDescriptor;
import com.homeboss.logic.parser.exceptions.ParseException;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.DeliveryNameContainsKeywordsPredicate;
import com.homeboss.model.delivery.DeliveryStatus;
import com.homeboss.model.delivery.Note;
import com.homeboss.model.person.Customer;
import com.homeboss.model.person.NameContainsKeywordsPredicate;
import com.homeboss.model.user.User;
import com.homeboss.testutil.CustomerBuilder;
import com.homeboss.testutil.CustomerEditDescriptorBuilder;
import com.homeboss.testutil.CustomerUtil;
import com.homeboss.testutil.DeliveryAddDescriptorBuilder;
import com.homeboss.testutil.DeliveryBuilder;
import com.homeboss.testutil.DeliveryEditDescriptorBuilder;
import com.homeboss.testutil.DeliveryUtil;
import com.homeboss.testutil.UpdateUserDescriptorBuilder;
import com.homeboss.testutil.UserBuilder;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_customerAdd() throws Exception {
        Customer customer = new CustomerBuilder().withCustomerId(Customer.getCustomerCount()).build();
        CustomerAddCommand command = (CustomerAddCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new CustomerAddCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deliveryCreateNote() throws Exception {
        DeliveryCreateNoteCommand command = (DeliveryCreateNoteCommand) parser.parseCommand(
                DeliveryCreateNoteCommand.COMMAND_WORD + " 1 --note This is a note");
        assertEquals(new DeliveryCreateNoteCommand(1, new Note("This is a note")), command);
    }

    @Test
    public void parseCommand_customerDelete() throws Exception {
        CustomerDeleteCommand command = (CustomerDeleteCommand) parser.parseCommand(
                CustomerDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new CustomerDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_customerView() throws Exception {
        CustomerViewCommand command = (CustomerViewCommand) parser.parseCommand(
                CustomerViewCommand.COMMAND_WORD + " " + CommandTestUtil.VALID_VIEW_CUSTOMER_ID_1);
        assertEquals(new CustomerViewCommand(Integer.parseInt(CommandTestUtil.VALID_VIEW_CUSTOMER_ID_1)), command);
    }


    @Test
    public void parseCommand_deliveryStatus() throws Exception {
        DeliveryStatusCommand command = (DeliveryStatusCommand) parser.parseCommand(
                DeliveryStatusCommand.COMMAND_WORD + " "
                        + GABRIELS_MILK.getDeliveryId() + " " + DeliveryStatus.COMPLETED);
        assertEquals(new DeliveryStatusCommand(GABRIELS_MILK.getDeliveryId(), DeliveryStatus.COMPLETED), command);
    }

    @Test
    public void parseCommand_deliveryView() throws Exception {
        DeliveryViewCommand command = (DeliveryViewCommand) parser.parseCommand(
                DeliveryViewCommand.COMMAND_WORD + " " + GABRIELS_MILK.getDeliveryId());
        assertEquals(new DeliveryViewCommand(GABRIELS_MILK.getDeliveryId()), command);
    }

    @Test
    public void parseCommand_customerEdit() throws Exception {

        Customer customer = new CustomerBuilder().build();
        CustomerEditDescriptor descriptor = new CustomerEditDescriptorBuilder(customer).build();
        CustomerEditCommand command = (CustomerEditCommand) parser
                .parseCommand(CustomerEditCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " "
                        + CustomerUtil.getEditPersonDescriptorDetails(descriptor));

        assertEquals(new CustomerEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_deleteDelivery() throws Exception {
        DeliveryDeleteCommand command = (DeliveryDeleteCommand) parser.parseCommand(
                DeliveryDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeliveryDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_addDelivery() throws Exception {
        DeliveryAddCommand command = (DeliveryAddCommand) parser.parseCommand(
                DeliveryAddCommand.COMMAND_WORD + " "
                        + "Gabriels "
                        + PREFIX_CUSTOMER_ID + CommandTestUtil.VALID_VIEW_CUSTOMER_ID_1 + " "
                        + PREFIX_DATE + CommandTestUtil.VALID_DELIVERY_DATE_3);

        DeliveryBuilder deliveryBuilder = new DeliveryBuilder();
        Delivery validDelivery = deliveryBuilder.build();
        DeliveryAddDescriptorBuilder deliveryAddDescriptorBuilder = new DeliveryAddDescriptorBuilder(validDelivery);
        DeliveryAddDescriptor validDeliveryAddDescriptor = deliveryAddDescriptorBuilder.build();

        assertEquals(new DeliveryAddCommand(validDeliveryAddDescriptor), command);
    }

    @Test
    public void parseCommand_editDelivery() throws Exception {
        Delivery delivery = new DeliveryBuilder().build();
        DeliveryEditDescriptor descriptor = new DeliveryEditDescriptorBuilder(delivery).build();
        DeliveryEditCommand command = (DeliveryEditCommand) parser.parseCommand(DeliveryEditCommand
                .COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + DeliveryUtil.getEditDeliveryDescriptorDetails(descriptor));
        assertEquals(new DeliveryEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    public void parseCommand_deliveryFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        DeliveryFindCommand command = (DeliveryFindCommand) parser.parseCommand(
                DeliveryFindCommand.COMMAND_WORD
                        + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new DeliveryFindCommand(new DeliveryNameContainsKeywordsPredicate(keywords)), command);

    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        CustomerFindCommand command = (CustomerFindCommand) parser.parseCommand(
                CustomerFindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new CustomerFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_customerList() throws Exception {
        assertTrue(parser.parseCommand(CustomerListCommand.COMMAND_WORD) instanceof CustomerListCommand);
        assertTrue(parser.parseCommand(CustomerListCommand.COMMAND_WORD + " 3")
                instanceof CustomerListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_invalidPrefix_throwsParseException() {
        // Cannot wrap lines due to Separator Wrap not allowing lambda on newline
        assertThrows(
                ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                        .parseCommand("invalidPrefix list"));
    }

    @Test
    // test user register command
    public void parseCommand_userRegister() throws Exception {
        assertTrue(parser.parseCommand(UserRegisterCommand.COMMAND_WORD + " "
                + PREFIX_USER + " username "
                + PREFIX_PASSWORD + " password "
                + PREFIX_PASSWORD_CONFIRM + " password "
                + PREFIX_SECRET_QUESTION + " secret question "
                + PREFIX_ANSWER + " answer") instanceof UserRegisterCommand);
    }

    @Test
    public void parseCommand_userLogin() throws Exception {
        assertTrue(parser.parseCommand(UserLoginCommand.COMMAND_WORD + " "
                + PREFIX_USER + " username "
                + PREFIX_PASSWORD + " password ") instanceof UserLoginCommand);
    }

    @Test
    public void parseCommand_userLogout() throws Exception {
        assertTrue(parser.parseCommand(UserLogoutCommand.COMMAND_WORD) instanceof UserLogoutCommand);
        assertTrue(parser.parseCommand(UserLogoutCommand.COMMAND_WORD + " abc") instanceof UserLogoutCommand);
    }

    @Test
    public void parseCommand_userDelete() throws Exception {
        assertTrue(parser.parseCommand(UserDeleteCommand.COMMAND_WORD) instanceof UserDeleteCommand);
        assertTrue(parser.parseCommand(UserDeleteCommand.COMMAND_WORD + " abc") instanceof UserDeleteCommand);
    }

    @Test
    public void parseCommand_userRecoverAccount() throws Exception {
        assertTrue(parser.parseCommand(UserRecoverAccountCommand.COMMAND_WORD) instanceof UserRecoverAccountCommand);
        assertTrue(parser.parseCommand(UserRecoverAccountCommand.COMMAND_WORD + " abc")
                instanceof UserRecoverAccountCommand);
        assertTrue(parser.parseCommand(UserRecoverAccountCommand.COMMAND_WORD + " "
                + PREFIX_ANSWER + " answer "
                + PREFIX_PASSWORD + " password "
                + PREFIX_PASSWORD_CONFIRM + " password") instanceof UserRecoverAccountCommand);
    }

    @Test
    public void parseCommand_userUpdate() throws Exception {
        User user = new UserBuilder().build();
        UserUpdateDescriptor descriptor = new UpdateUserDescriptorBuilder(user).build();
        UserUpdateCommand command = (UserUpdateCommand) parser.parseCommand(UserUpdateCommand.COMMAND_WORD + " "
                + PREFIX_USER + " " + UserBuilder.DEFAULT_USERNAME + " "
                + PREFIX_PASSWORD + " " + UserBuilder.DEFAULT_PASSWORD + " "
                + PREFIX_PASSWORD_CONFIRM + " " + UserBuilder.DEFAULT_PASSWORD + " "
                + PREFIX_SECRET_QUESTION + " " + UserBuilder.SECRET_QUESTION + " "
                + PREFIX_ANSWER + " " + UserBuilder.ANSWER);
        assertEquals(new UserUpdateCommand(descriptor), command);
    }
}
