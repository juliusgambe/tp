package com.homeboss.logic.commands;

import static com.homeboss.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.homeboss.logic.commands.HelpCommand.HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import com.homeboss.model.Model;
import com.homeboss.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_helpLoggedIn_success() {
        model.setLoginSuccess();
        expectedModel.setLoginSuccess();
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpLoggedOut_success() {
        model.setLogoutSuccess();
        expectedModel.setLogoutSuccess();
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
