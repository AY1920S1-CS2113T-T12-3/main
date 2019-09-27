package duke.ui;

import duke.entities.recipe.Ingredient;
import duke.entities.recipe.Recipe;
import duke.entities.recipe.Step;
import duke.storage.recipe.RecipeList;
import duke.ui.recipe.IngredientBox;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RecipePage extends AnchorPane {


    @FXML
    private AnchorPane recipeShowPane;
    @FXML
    private AnchorPane recipeListPane;
    @FXML
    private Label recipeName;
    @FXML
    private HBox difficultyLevel;
    @FXML
    private HBox ingredients;
    @FXML
    private VBox steps;
    @FXML
    private VBox recipeList;
    @FXML
    private Label timeLabel;
    @FXML
    private TableView<Recipe> recipeTable;

    private Recipe recipe = new Recipe("");


    public RecipePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/RecipePage.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return recipe.getName();
    }

    public void showRecipeShowPane() {
        Recipe recipe = new Recipe("");
        recipe.init();
        recipeShowPane.setVisible(true);
        recipeListPane.setVisible(false);
        recipeName.setText(recipe.getName());

        String currentDir = System.getProperty("user.dir");
        try {
            Image image1 = new Image(new FileInputStream(currentDir + "\\src\\main\\resources\\images\\star.png"));
            difficultyLevel.getChildren().clear();
            for (int i = 0; i < recipe.getDiffLevel(); i++) {
                ImageView star = new ImageView();
                star.setImage(image1);
                star.setFitHeight(28);
                star.setPreserveRatio(true);
                difficultyLevel.getChildren().add(star);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ingredients.getChildren().clear();
        for (Ingredient ingredient : recipe.getIngredients()) {
            IngredientBox ingredientBox = new IngredientBox(ingredient);
            ingredient.init();
            ingredients.getChildren().add(ingredientBox);
        }

        steps.getChildren().clear();
        steps.setSpacing(10);
        int index = 1;
        for (Step step: recipe.getSteps()) {
            Label newLabel = new Label();
            newLabel.setText("Step " + index + ". " + step.getDescription());
            newLabel.setWrapText(true);
            newLabel.setFont(new Font("Arial", 20));
            newLabel.setBackground(new Background(new BackgroundFill(Color.web("#96663b20"),null, null)));
            steps.getChildren().add(newLabel);
        }
        timeLabel.setText(recipe.getTime() + " mins");
    }



    void showRecipeListPage(RecipeList rpl) {
        recipeListPane.setVisible(true);
        recipeShowPane.setVisible(false);
        ObservableList<Recipe> recipeObservableList = FXCollections.observableArrayList();
        for (Recipe recipe : rpl) {
            recipeObservableList.add(recipe);
        }

        recipeTable.getColumns().clear();
        if (rpl.size() != 0) {

            TableColumn<Recipe, Void> indexColumn = new TableColumn<>("S/N");
            indexColumn.setMinWidth(50);
            indexColumn.setPrefWidth(70);
            indexColumn.setResizable(true);


            //Solution below adapted from: https://stackoverflow.com/questions/31212400/adding-index-of-records-in-a-javafx-tableview-column
            ///////////////////////////////////////////////////////index column
            {indexColumn.setCellFactory(col -> {

                // just a default table cell:
                TableCell<Recipe, Void> cell = new TableCell<>();

                cell.textProperty().bind(Bindings.createStringBinding(() -> {
                    if (cell.isEmpty()) {
                        return null ;
                    } else {
                        return Integer.toString(cell.getIndex() + 1);
                    }
                }, cell.emptyProperty(), cell.indexProperty()));

                return cell ;
            });}
            ////////////////////////////////index column created

            TableColumn<Recipe, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setMinWidth(150);
            nameColumn.setPrefWidth(500);
            nameColumn.setResizable(true);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<Recipe, Integer> timeColumn = new TableColumn<>("Time");
            timeColumn.setMinWidth(50);
            timeColumn.setPrefWidth(100);
            timeColumn.setResizable(true);
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

            TableColumn<Recipe, Double> costColumn = new TableColumn<>("Cost");
            costColumn.setMinWidth(50);
            costColumn.setPrefWidth(70);
            costColumn.setResizable(true);
            costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

            TableColumn<Recipe, Integer> difficultyLevelColumn = new TableColumn<>("Difficulty Level");
            difficultyLevelColumn.setMinWidth(70);
            difficultyLevelColumn.setPrefWidth(300);
            difficultyLevelColumn.setResizable(true);
            difficultyLevelColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyLevel"));

            recipeTable.setItems(recipeObservableList);
            recipeTable.getColumns().addAll(indexColumn, nameColumn, timeColumn, costColumn,
                    difficultyLevelColumn);

        }
    }


}
