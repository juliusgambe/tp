package com.homeboss.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.model.person.Customer;
import com.homeboss.model.AddressBook;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Customer ALICE = new CustomerBuilder().withCustomerId(1)
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").build();
    public static final Customer BENSON = new CustomerBuilder().withCustomerId(2)
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").build();
    public static final Customer CARL = new CustomerBuilder().withCustomerId(3)
            .withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Customer DANIEL = new CustomerBuilder().withCustomerId(4)
            .withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").build();
    public static final Customer ELLE = new CustomerBuilder().withCustomerId(5)
            .withName("Elle Meyer").withPhone("94822246")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Customer FIONA = new CustomerBuilder().withCustomerId(6)
            .withName("Fiona Kunz").withPhone("94824276")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Customer GEORGE = new CustomerBuilder().withCustomerId(7)
            .withName("George Best").withPhone("94824426")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withCustomerId(8).withName("Hoon Meier")
        .withPhone("84824246").withEmail("stefan@example.com").withAddress("little india").build();
    public static final Customer IDA = new CustomerBuilder().withCustomerId(9).withName("Ida Mueller")
        .withPhone("84821316").withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withCustomerId(10).withName(CommandTestUtil.VALID_NAME_AMY)
        .withPhone(CommandTestUtil.VALID_PHONE_AMY).withEmail(CommandTestUtil.VALID_EMAIL_AMY).withAddress(
            CommandTestUtil.VALID_ADDRESS_AMY).build();
    public static final Customer BOB = new CustomerBuilder().withCustomerId(11).withName(CommandTestUtil.VALID_NAME_BOB)
        .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_BOB).withAddress(
            CommandTestUtil.VALID_ADDRESS_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Customer customer : getTypicalPersons()) {
            ab.addPerson(customer);
        }
        return ab;
    }

    public static List<Customer> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
