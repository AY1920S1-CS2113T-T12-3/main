package duke.logic;

import duke.command.Command;
import duke.entities.recipe.Recipe;
import javafx.collections.ObservableList;

import java.nio.file.Path;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     */
    Command execute(String commandText);

    ObservableList<Recipe> getRecipeList();
}
    //
    //
    ///**
    // * Returns the user prefs' address book file path.
    // */
    //Path getAddressBookFilePath();
    //
    ///**
    // * Returns the user prefs' GUI settings.
    // */
    //GuiSettings getGuiSettings();
    //
    ///**
    // * Set the user prefs' GUI settings.
    // */
    //void setGuiSettings(GuiSettings guiSettings);