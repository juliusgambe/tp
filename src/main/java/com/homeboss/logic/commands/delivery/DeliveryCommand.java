package com.homeboss.logic.commands.delivery;

import com.homeboss.logic.commands.Command;

/**
 * Represents a delivery command with hidden internal logic and the ability to be executed.
 */
public abstract class DeliveryCommand extends Command {
    /**
     * Command Prefix for all Delivery Commands
     */
    public static final String COMMAND_WORD = "delivery";
}
