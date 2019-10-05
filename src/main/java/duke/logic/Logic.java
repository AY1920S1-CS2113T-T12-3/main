package duke.logic;

import duke.command.Command;
import duke.commons.core.GuiSettings;
import duke.model.Order;
import duke.model.recipe.Recipe;
import javafx.collections.ObservableList;

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

    /** Returns unmodifiable version of RecipeList; */
    ObservableList<Recipe> getRecipeList();

    ObservableList<Order> getOrderList();
    GuiSettings getGuiSettings();
}
