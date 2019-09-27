package duke.ui;

import com.jfoenix.controls.JFXButton;
import duke.entities.Order;
import duke.entities.Sale;
import duke.logic.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.List;

public class MainWindow extends AnchorPane {

    private Duke duke;
    private Ui ui;

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

    private OrderPage orderPage;
    private RecipePage recipePage;
    private SalePage salePage;

    public void initialize() {
        Ui ui = new Ui(this);
        duke = new Duke(ui);
        popUp.setVisible(false);
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
        System.out.println("!!!");
        showRecipePage();
    }

    @FXML
    private void handleShowOrder() {
        showOrderPage();
    }

    @FXML
    private void handleShowInventory() {

    }

    @FXML
    private void handleShowSales() {

    }

    void initializePages() {
        orderPage = new OrderPage();
        AnchorPane.setLeftAnchor(orderPage, 0.0);
        AnchorPane.setRightAnchor(orderPage, 0.0);
        AnchorPane.setTopAnchor(orderPage, 0.0);
        AnchorPane.setBottomAnchor(orderPage, 4.0);

        recipePage = new RecipePage();
        AnchorPane.setLeftAnchor(recipePage, 0.0);
        AnchorPane.setRightAnchor(recipePage, 0.0);
        AnchorPane.setTopAnchor(recipePage, 0.0);
        AnchorPane.setBottomAnchor(recipePage, 4.0);

        salePage = new SalePage();
        AnchorPane.setLeftAnchor(salePage, 0.0);
        AnchorPane.setRightAnchor(salePage, 0.0);
        AnchorPane.setTopAnchor(salePage, 0.0);
        AnchorPane.setBottomAnchor(salePage, 4.0);
    }

    void showMessage(String message) {
        popUpLabel.setText(message);
        popUpLabel.setTextFill(Color.valueOf("#000000"));
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

    void showSalePage() {
        pagePane.getChildren().clear();
        pagePane.getChildren().add(salePage);

        recipeButton.setButtonType(JFXButton.ButtonType.FLAT);
        orderButton.setButtonType(JFXButton.ButtonType.RAISED);
        inventoryButton.setButtonType(JFXButton.ButtonType.FLAT);
        salesButton.setButtonType(JFXButton.ButtonType.FLAT);

        currentPage.setText("Sales");
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

    void disableInput() {
        userInput.setDisable(true);
    }
}
