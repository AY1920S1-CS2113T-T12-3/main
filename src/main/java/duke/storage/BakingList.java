package duke.storage;

import duke.command.ExecuteShortcut;
import duke.model.Order;
import duke.model.Sale;
import duke.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BakingList {

    private List<Order> orderList = new ArrayList<>();
    private List<Sale> saleList = new ArrayList<>();
    private Map<String, ExecuteShortcut> shortcuts = new HashMap<>();

    private List<Recipe> recipeList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public Map<String, ExecuteShortcut> getShortcuts() {
        return shortcuts;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void setShortcuts(Map<String, ExecuteShortcut> shortcuts) {
        this.shortcuts = shortcuts;
    }
}
