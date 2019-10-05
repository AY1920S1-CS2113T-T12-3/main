package duke.ui;

import com.jfoenix.controls.JFXButton;
import duke.command.Command;
import duke.commons.DukeException;
import duke.commons.LogsCenter;
import duke.commons.core.GuiSettings;
import duke.model.Order;
import duke.model.Sale;
import duke.model.recipe.Recipe;
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

    //4 Sections
    private OrderPage orderPage;
    private RecipePage recipePage;
    private InventoryPage inventoryPage;
    private SalePage salePage;


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

        popUp.setVisible(false);
        // Configure Main Page
        setWindowDefaultSize(logic.getGuiSettings());
    }


    void showDefaultSectionPage() {
        orderPage = new OrderPage(logic.getOrderList());
        pagePane.getChildren().add(recipePage.getRoot());
    }

    void show() {
        primaryStage.show();
    }

    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Adjusts the MainPage elements when showing different section
     * @param section
     */
    private void showMainElements(UiPart<Region> section) {
        pagePane.getChildren().clear();
        pagePane.getChildren().add(section);

        recipeButton.setButtonType(JFXButton.ButtonType.FLAT);
        orderButton.setButtonType(JFXButton.ButtonType.FLAT);
        inventoryButton.setButtonType(JFXButton.ButtonType.FLAT);
        salesButton.setButtonType(JFXButton.ButtonType.FLAT);
        if (section instanceof RecipePage) {
            recipeButton.setButtonType(JFXButton.ButtonType.RAISED);
            currentPage.setText("Recipe");
        } else {
            //
        }
    }

    private void setPageAnchor(UiPart<Region> section) {
        AnchorPane.setLeftAnchor(section, 0.0);
        AnchorPane.setRightAnchor(section, 0.0);
        AnchorPane.setTopAnchor(section, 0.0);
        AnchorPane.setBottomAnchor(section, 4.0);
    }

    /**
     * Configure MainWindow when different section pages are shown
     * @param section ROIS page shown
     */
    public void configMainWindow(UiPart<Region> section) {
        showMainElements(section);
        setPageAnchor(section);
    }

    // Maybe for error handling
    public RecipePage getRecipePage() {
        return recipePage;
    }


    @FXML
    private void handleUserInput() {
        popUp.setVisible(false);
        String input = userInput.getText();
        executeCommand(input);
        userInput.clear();
    }

    //equivalent to executeCommand() method in AB3
    //is the Part where UI controls Logic
    //In AB3 CommandBox is a UiPart<Region>, but in our case the place to input is only a textInput,
    //Will revisit when Command component is done
    private void executeCommand(String input) {
        try {
            Command command = logic.execute(input);
            logger.info("Result: " + command.getFeedbackToUser());
            handleOk();
        } catch (Exception e) {
            logger.info("Invalid command!");
            handleError();
            throw e;
        }
    }

    @FXML void handleError() {

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


    //Might need to implement Command to change page section
    //i.e. user enters "order", UI shows recipe page
    // for this part Ui might need to control Logic
    void showOrderPage() {
        //configMainWindow(orderPage);
    }

    void showRecipePage() {
        configMainWindow(recipePage);
    }

    void showInventoryPage() {
        //configMainWindow(InventoryPage);
    }

    void showSalePage() {
        //configMainWindow(SalePage);
    }

    void disableInput() {
        userInput.setDisable(true);
    }
}
