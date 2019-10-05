package duke.model;

import duke.model.recipe.Recipe;
import duke.model.recipe.RecipeList;
import javafx.collections.ObservableList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BakingHome implements ReadOnlyBakingHome {

    private final RecipeList recipes;

    public BakingHome() {
        recipes = new RecipeList();
    }

    public BakingHome(ReadOnlyBakingHome toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the order list with {@code orders}.
     */
    public void setOrders(List<Order> orders) {
        //this.orders.setOrders(orders);
    }

    /**
     * Replaces the contents of the order list with {@code recipes}
     */
    public void setRecipes(List<Recipe> recipes) {
  //      this.recipes.setRecipes(recipes);
    }
    /**
     * Resets tje existing data
     */

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBakingHome newData) {
        requireNonNull(newData);

        setOrders(newData.getOrderList());
    }

    /**
     * Returns an unmodifiable view of the order list.
     */
    @Override
    public ObservableList<Order> getOrderList() {
        return null;
    }

    @Override
    public ObservableList<Recipe> getRecipeList() {
        return null;
    }
}