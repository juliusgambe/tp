package com.homeboss.model;

import java.nio.file.Path;

import com.homeboss.commons.core.GuiSettings;
import com.homeboss.model.user.User;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getDeliveryBookFilePath();

    Path getAuthenticationFilePath();

    User getStoredUser();

}
