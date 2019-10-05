package duke.model.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import duke.model.Ingredient;

public class recipeIngredient {

    private Ingredient ingredient;
    private String description;

    public recipeIngredient(Ingredient ingredient, @JsonProperty String description) {
        this.ingredient = ingredient;
        this.description = description;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }


}
