package com.homeboss.logic.commands;

import com.homeboss.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    // public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    // Give a more informative help message that explains how to use basic commands
    public static final String HELP_MESSAGE = "Ensure you have registered an account / have logged"
            + " in before using the following basic commands, except for the ones indicated with a `.\n\n"
            + "USER\n"
            + "These are commands for managing your user account in HomeBoss.\n"
            + "* `register - Registers a new user account to use HomeBoss.\n"
            + "* `login - Logs in to your user account.\n"
            + "* update - Updates your user account details.\n"
            + "* `recover account - Recovers your user account.\n"
            + "* `logout - Logs out of your user account.\n"
            + "* `delete account - Deletes your user account.\n\n"

            + "CUSTOMER\n"
            + "These are commands for managing your customers in HomeBoss.\n"
            + "* customer add - Adds a customer to the address book.\n"
            + "* customer find - Finds customers whose names contain any of the given keywords.\n"
            + "* customer list - Lists all customers in the address book.\n"
            + "* customer view - Shows the details of the specified customer.\n"
            + "* customer edit - Updates the details of an existing customer in the address book.\n"
            + "* customer delete - Deletes the specified customer from the address book.\n\n"

            + "DELIVERY\n"
            + "These are commands for managing your deliveries in HomeBoss.\n"
            + "* delivery add - Adds a delivery to the delivery book.\n"
            + "* delivery find - Finds deliveries whose names contain any of the given keywords.\n"
            + "* delivery list - Lists all deliveries in the delivery book.\n"
            + "* delivery view - Shows the details of the specified delivery.\n"
            + "* delivery edit - Updates the details of an existing delivery in the delivery book.\n"
            + "* delivery status - Changes the status of a specified delivery.\n"
            + "* delivery note - Creates a note for a specified delivery.\n"
            + "* delivery delete - Deletes the specified delivery from the delivery book.\n\n"

            + "MISCELLANEOUS\n"
            + "These are general commands in HomeBoss.\n"
            + "* `exit - Exits the program.\n"
            + "* `help - Shows a list of commands and their usage.\n"
            + "* clear - Clears both customer and delivery database. *Warning:* This action is irreversible.\n\n"

            + "Refer to the URL in the pop-up window for"
            + " our full user guide.\n\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HELP_MESSAGE, true, false, false);
    }
}
