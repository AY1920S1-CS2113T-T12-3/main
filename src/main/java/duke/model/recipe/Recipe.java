package duke.model.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import duke.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public static enum State {
        ACTIVE,
        ARCHIVED
    }


    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();
    private int difficultyLevel;
    private int time;
    private State state;

    public Recipe(@JsonProperty("name") String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
        this.difficultyLevel = 0;
    }

    public Recipe() {

    }

    public Recipe(@JsonProperty("name") String name,
                  @JsonProperty("ingredients") List<Ingredient> ingredients,
                  @ JsonProperty("steps") List<Step> steps,
                  @JsonProperty("difficultyLevel") int difficultyLevel) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.difficultyLevel = difficultyLevel;
    }

    public Recipe init() {
        name = "Cheese Cake";
        List<Step> stepList = new ArrayList<>();
        Step step1 = new Step("");
        step1.init();
        stepList.add(step1);
        this.steps = stepList;
        ingredients.add(new Ingredient("cream"));
        ingredients.add(new Ingredient("cheese"));
        difficultyLevel = 5;
        time = 40;
        return this;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public int getTime() {
//        int time = 0;
//        for (Step step : steps) {
//            time += step.getTime();
//        }
//        return time;
//    }

//    public int getDifficultyLevel() {
//        return difficultyLevel;
//    }

//    public double getCost() {
//        return cost;
//    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

//    public void addIngredient(Ingredient ingredient) {
//        this.ingredients.add(ingredient);
//    }
//
//    public void addStep(Step step) {
//        steps.add(step);
//    }
}
