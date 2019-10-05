package duke.model.recipe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class RecipeList implements Iterable<Recipe> {

    private final ObservableList<Recipe> recipeObservableList = FXCollections.observableArrayList();
    private final ObservableList<Recipe> recipeUnmodifiableList =
            FXCollections.unmodifiableObservableList(recipeObservableList);

    public void add(Recipe toAdd) {
        requireNonNull(toAdd);
        recipeObservableList.add(toAdd);
    }


    public void setRecipes(RecipeList replacement) {
     //   requireNonNull(replacement);
     //   internalList.setAll(replacement.internalList);
    }

    @Override
    public Iterator<Recipe> iterator() {
        return recipeObservableList.iterator();
    }
}
