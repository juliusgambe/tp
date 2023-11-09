package com.homeboss.logic.commands.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.testutil.TypicalDeliveries.GABRIELS_MILK;
import static com.homeboss.testutil.TypicalDeliveries.GAMBES_RICE;
import static com.homeboss.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import com.homeboss.logic.commands.CommandTestUtil;
import com.homeboss.testutil.TypicalPersons;
import org.junit.jupiter.api.Test;

import com.homeboss.logic.Messages;
import com.homeboss.logic.commands.ClearCommand;
import com.homeboss.model.AddressBook;
import com.homeboss.model.DeliveryBook;
import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;
import com.homeboss.model.UserPrefs;
import com.homeboss.model.delivery.Delivery;
import com.homeboss.model.delivery.Note;
import com.homeboss.testutil.DeliveryBuilder;
public class DeliveryCreateNoteCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), getTypicalDeliveryBook(), new UserPrefs(), true);

    @Test
    public void execute_replaceNote_success() {
        Note note = new Note("This is a test note");
        Delivery expectedDelivery = new DeliveryBuilder(GABRIELS_MILK).withNote(note.note).build();
        DeliveryCreateNoteCommand deliveryCreateNoteCommand =
            new DeliveryCreateNoteCommand(GABRIELS_MILK.getDeliveryId(), note);

        String expectedMessage = String.format(DeliveryCreateNoteCommand.MESSAGE_NOTE_SUCCESS,
            Messages.format(expectedDelivery));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(),
            true);
        expectedModel.setDelivery(model.getDeliveryBook().getById(GABRIELS_MILK.getDeliveryId()).get(),
            expectedDelivery);

        CommandTestUtil.assertCommandSuccess(deliveryCreateNoteCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_newNote_success() {
        Note note = new Note("This is a new note");
        Delivery expectedDelivery = new DeliveryBuilder(GAMBES_RICE).withNote(note.note).build();
        DeliveryCreateNoteCommand deliveryCreateNoteCommand =
            new DeliveryCreateNoteCommand(GAMBES_RICE.getDeliveryId(), note);

        String expectedMessage = String.format(DeliveryCreateNoteCommand.MESSAGE_NOTE_SUCCESS,
            Messages.format(expectedDelivery));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
            new DeliveryBook(model.getDeliveryBook()),
            new UserPrefs(),
            true);
        expectedModel.setDelivery(model.getDeliveryBook().getById(GAMBES_RICE.getDeliveryId()).get(),
            expectedDelivery);

        CommandTestUtil.assertCommandSuccess(deliveryCreateNoteCommand, model, expectedMessage, expectedModel, true);
    }

    @Test
    public void execute_invalidTargetId_throwsCommandException() {
        Note note = new Note("This is a note");
        DeliveryCreateNoteCommand deliveryCreateNoteCommand = new DeliveryCreateNoteCommand(-1, note);
        CommandTestUtil.assertCommandFailure(deliveryCreateNoteCommand, model, Messages.MESSAGE_INVALID_DELIVERY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_newNoteLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        Note note = new Note("This is a new note");
        DeliveryCreateNoteCommand deliveryCreateNoteCommand =
                new DeliveryCreateNoteCommand(GAMBES_RICE.getDeliveryId(), note);

        CommandTestUtil.assertCommandFailure(deliveryCreateNoteCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void execute_invalidLoggedOut_throwsCommandException() {
        model.setLogoutSuccess();
        Note note = new Note("This is a note");
        DeliveryCreateNoteCommand deliveryCreateNoteCommand = new DeliveryCreateNoteCommand(-1, note);
        CommandTestUtil.assertCommandFailure(deliveryCreateNoteCommand, model, Messages.MESSAGE_USER_NOT_AUTHENTICATED);
    }

    @Test
    public void equals() {
        Note note = new Note("This is a note");
        final DeliveryCreateNoteCommand standardCommand =
            new DeliveryCreateNoteCommand(GABRIELS_MILK.getDeliveryId(), note);

        // same values -> returns true
        DeliveryCreateNoteCommand commandWithSameValue =
            new DeliveryCreateNoteCommand(GABRIELS_MILK.getDeliveryId(), note);
        assertTrue(standardCommand.equals(commandWithSameValue));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeliveryCreateNoteCommand(GAMBES_RICE.getDeliveryId(),
            note)));

        // different note -> returns false
        assertFalse(standardCommand.equals(new DeliveryCreateNoteCommand(GABRIELS_MILK.getDeliveryId(),
            new Note("Different Note"))));
    }

    @Test
    public void toStringMethod() {
        Note note = new Note("This is a note");
        String expected = DeliveryCreateNoteCommand.class.getCanonicalName()
            + "{targetId=" + GABRIELS_MILK.getDeliveryId() + ", note="
            + note + "}";
        DeliveryCreateNoteCommand deliveryStatusCommand =
            new DeliveryCreateNoteCommand(GABRIELS_MILK.getDeliveryId(), note);
        assertEquals(expected, deliveryStatusCommand.toString());
    }
}
