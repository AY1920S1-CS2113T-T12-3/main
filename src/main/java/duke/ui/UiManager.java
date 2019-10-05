package duke.ui;

import duke.Launcher;
import duke.commons.LogsCenter;
import duke.logic.Logic;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String APPLICATION_ICON = "\\src\\main\\resources\\images\\icon_temp.png";

    private Logic logic;
    private MainWindow mainWindow;

    //////////////////////////////////////////////////////

    public UiManager(Logic logic) {
        super();
        this.logic = logic;
    }

    /**
     * Starts the UI (and the App).
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");

        primaryStage.getIcons().add(getImage(APPLICATION_ICON));

        try {
            mainWindow = new MainWindow(primaryStage, logic);
            mainWindow.show();
            mainWindow.showDefaultSectionPage(); //what's the initial page you want to show
        } catch (Throwable e) {
      //      logger.severe(StringUtil.getDetails(e));
            disableInput();
        }
    }

    private Image getImage(String imagePath) {
        return new Image(Launcher.class.getResourceAsStream(imagePath));
    }
/*

    public void showMessage(String message) {
        mainWindow.showMessage(message);
    }

    public void showError(String message) {
        mainWindow.showErrorPopUp(message);
    }

    public void showOrderPage() {
        mainWindow.showOrderPage();
    }

    public void showRecipePage() {
        mainWindow.showRecipePage();
    }

    public void showSalePage() {
        mainWindow.showSalePage();
    }

    public void initializePages() {
        mainWindow.initializePages();
    }

    public void refreshOrderList(List<Order> orders, List<Order> all) {
        mainWindow.refreshOrderList(orders, all);
    }

    public void refreshRecipeListPage(List<Recipe> recipeList) {
        mainWindow.refreshRecipeListPage(recipeList);
    }

    public void refreshSaleList(List<Sale> sale, List<Sale> all) {
        mainWindow.refreshSaleList(sale, all);
    }
*/

    public void disableInput() {
        mainWindow.disableInput();
    }


}
