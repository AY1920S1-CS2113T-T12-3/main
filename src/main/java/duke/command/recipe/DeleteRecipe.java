package duke.command.recipe;

import duke.command.Command;
import duke.command.Undoable;
import duke.commons.DukeException;
import duke.model.recipe.Recipe;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.UiManager;

import java.util.List;
import java.util.Map;

public class DeleteRecipe extends Command implements Undoable {

    private Recipe recipe;
    private int index;
    private Map<String, List<String>> params;

    public DeleteRecipe(Map<String, List<String>> params) throws DukeException {
        this.params = params;

    }


    /**
     * Execute the command.
     *
     * @param bakingList A BakingList.
     * @param storage    A Storage object which specifies the location of the data.
     * @param ui         A Ui object capable of controlling GUI.
     * @throws DukeException If the execution fails.
     */
    @Override
    public void execute(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {
        //this.recipe = getRecipe(bakingList.getRecipeList());
        //bakingList.getRecipeList().remove(recipe);
        storage.serialize(bakingList);
        //ui.refreshRecipeListPage(bakingList.getRecipeList());
    }

    private Recipe getRecipe(List<Recipe> recipes) {
        if (params.containsKey("secondary")) {
            String indexParams = params.get("secondary").get(0);
            return recipes.get(Integer.parseInt(indexParams));
        }
        System.out.println("DeleteRecipeCommand function getRecipe return null");
        return null;
    }

    @Override
    public void undo(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {

    }

    @Override
    public void redo(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {

    }
}
