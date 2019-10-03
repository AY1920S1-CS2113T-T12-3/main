package duke.entities.recipe;

import duke.commons.DukeException;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RecipeTest {

    @Test
    private void createRecipe_withName_success() {

            Recipe recipe = new Recipe("Cake");

            assertEquals("Cake", recipe.getName());
    }
}