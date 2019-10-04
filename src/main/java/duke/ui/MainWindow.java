package duke.ui;

import com.jfoenix.controls.JFXButton;
import duke.commons.LogsCenter;
import duke.entities.Order;
import duke.entities.Sale;
import duke.entities.recipe.Recipe;
import duke.logic.Duke;
import duke.logic.Logic;
import duke.ui.inventory.InventoryPage;
import duke.ui.order.OrderPage;
import duke.ui.recipe.RecipePage;
import duke.ui.sale.SalePage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.logging.Logger;

public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    //4 Usages
    private OrderPage orderPage;
    private RecipePage recipePage;
    private InventoryPage inventoryPage;
    private SalePage salePage;

    //////////////////////////// UI manager
    private Duke duke;
    private UiManager ui;
/////////////////////////////////////////////

    //Popup box
    @FXML
    private HBox popUp;
    @FXML
    private Label popUpLabel;
    @FXML
    JFXButton popUpButton;
    @FXML
    private TextField userInput;

    //Main page
    @FXML
    private Label currentPage;
    @FXML
    private AnchorPane pagePane;

    //Sidebar
    @FXML
    private JFXButton recipeButton;
    @FXML
    private JFXButton orderButton;
    @FXML
    private JFXButton inventoryButton;
    @FXML
    private JFXButton salesButton;


    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage); //fxml is taken care of here;

        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure
        popUp.setVisible(false);
    }

    /////////////////////////////////////
    public void initialize() {
        duke = new Duke(ui);
        popUp.setVisible(false);

        recipePage = new RecipePage(logic.getRecipeList());

    }

    void initializePages() {
        recipePage = new RecipePage(logic.getRecipeList());
        pagePane.getChildren().add(recipePage.getRoot());
        setAnchorForSectionPage(recipePage);

        orderPage = new OrderPage();
        //setAnchorForSectionPage(orderPage);

        inventoryPage = new InventoryPage();
        //setAnchorForSectionPage(inventoryPage);

        salePage = new SalePage();
        //setAnchorForSectionPage(salePage);
    }

    void show() {
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    private void setAnchorForSectionPage(UiPart<Region> sectionPage) {
        AnchorPane.setLeftAnchor(sectionPage, 0.0);
        AnchorPane.setRightAnchor(sectionPage, 0.0);
        AnchorPane.setTopAnchor(sectionPage, 0.0);
        AnchorPane.setBottomAnchor(sectionPage, 4.0);
        ////////////////////////////
        //setPageAnchor(sectionPage);
    }

    @FXML
    private void handleUserInput() {
        popUp.setVisible(false);
        String input = userInput.getText();
        duke.executeInput(input);
        userInput.clear();
    }

    @FXML
    private void handleOk() {
        popUp.setVisible(false);
    }

    @FXML
    private void handleShowRecipe() {
        showRecipePage();
    }

    @FXML
    private void handleShowOrder() {
        showOrderPage();
    }

    @FXML
    private void handleShowInventory() {
        showInventoryPage();
    }

    @FXML
    private void handleShowSale() {
        showSalePage();
    }



    void showMessage(String message) {
        popUpLabel.setText(message);
        popUpLabel.setTextFill(Color.valueOf("#ffffff"));
        popUpButton.getStyleClass().clear();
        popUpButton.getStyleClass().add("message-popup");
        popUp.getStyleClass().clear();
        popUp.getStyleClass().add("message-popup");
        popUp.setVisible(true);
    }

    void showErrorPopUp(String message) {
        popUpLabel.setText(message);
        popUpLabel.setTextFill(Color.valueOf("#ffffff"));
        popUpButton.getStyleClass().clear();
        popUpButton.getStyleClass().add("error-popup");
        popUp.getStyleClass().clear();
        popUp.getStyleClass().add("error-popup");
        popUp.setVisible(true);
    }

    void refreshOrderList(List<Order> orders, List<Order> all) {
        this.orderPage.refreshOrderList(orders, all);
    }

    void refreshRecipeListPage(List<Recipe> recipes) {
        this.recipePage.refreshRecipeListPage(recipes);
    }


    void showOrderPage() {
        pagePane.getChildren().clear();
        pagePane.getChildren().add(orderPage);

        recipeButton.setButtonType(JFXButton.ButtonType.FLAT);
        orderButton.setButtonType(JFXButton.ButtonType.RAISED);
        inventoryButton.setButtonType(JFXButton.ButtonType.FLAT);
        salesButton.setButtonType(JFXButton.ButtonType.FLAT);

        currentPage.setText("Orders");
    }

    void refreshSaleList(List<Sale> sales, List<Sale> all) {
        this.salePage.refreshSaleList(sales, all);
    }

    void showRecipePage() {
        pagePane.getChildren().clear();
        pagePane.getChildren().add(recipePage);

        recipeButton.setButtonType(JFXButton.ButtonType.RAISED);
        orderButton.setButtonType(JFXButton.ButtonType.FLAT);
        inventoryButton.setButtonType(JFXButton.ButtonType.FLAT);
        salesButton.setButtonType(JFXButton.ButtonType.FLAT);

        currentPage.setText("Recipes");
    }


    void showInventoryPage() {
        pagePane.getChildren().clear();
        pagePane.getChildren().add(inventoryPage);

        recipeButton.setButtonType(JFXButton.ButtonType.FLAT);
        orderButton.setButtonType(JFXButton.ButtonType.FLAT);
        inventoryButton.setButtonType(JFXButton.ButtonType.RAISED);
        salesButton.setButtonType(JFXButton.ButtonType.FLAT);

        currentPage.setText("Inventory");
    }

    void showSalePage() {
        pagePane.getChildren().clear();
        pagePane.getChildren().add(salePage);

        recipeButton.setButtonType(JFXButton.ButtonType.FLAT);
        orderButton.setButtonType(JFXButton.ButtonType.FLAT);
        inventoryButton.setButtonType(JFXButton.ButtonType.FLAT);
        salesButton.setButtonType(JFXButton.ButtonType.RAISED);

        currentPage.setText("Sales");
    }

    void disableInput() {
        userInput.setDisable(true);
    }

    private void setPageAnchor(AnchorPane page) {
        AnchorPane.setLeftAnchor(page, 0.0);
        AnchorPane.setRightAnchor(page, 0.0);
        AnchorPane.setTopAnchor(page, 0.0);
        AnchorPane.setBottomAnchor(page, 4.0);
    }
}
