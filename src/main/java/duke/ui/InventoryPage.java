package duke.ui;

import duke.model.commons.Item;
import duke.model.inventory.Ingredient;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class InventoryPage extends UiPart<AnchorPane> {
    private static final String FXML = "InventoryPage.fxml";

    @FXML
    private TableView inventoryListTable;

    private ObservableList<Item<Ingredient>> inventoryList;

    /**
     * Creates a constructor for InventoryPage and sets the ObservableList of Items to be the 1 in the input
     * Sets up the table view, its columns and data inputted
     * @param inventoryList An observable list containing the ingredients to be displayed in the inventory list
     */
    public InventoryPage(ObservableList<Item<Ingredient>> inventoryList) {
        super(FXML);
        this.inventoryList = inventoryList;
        setupTable();
    }

    void setupTable() {
        inventoryListTable.setItems(inventoryList);
        inventoryListTable.getColumns().clear();
        setIndexColumn();
        setInventoryInfoColumns();
    }

    void setIndexColumn() {
        TableColumn<Item<Ingredient>, Void> indexColumn = new TableColumn<>("S/N");
        indexColumn.setResizable(true);

        //Solution below adapted from: https://stackoverflow.com/questions/31212400/adding-index-of-records-in-a-javafx-tableview-column
        ///////////////////////////////////////////////////////index column
        {
            indexColumn.setCellFactory(col -> {

                // just a default table cell:
                TableCell<Item<Ingredient>, Void> cell = new TableCell<>();

                cell.textProperty().bind(Bindings.createStringBinding(() -> {
                    if (cell.isEmpty()) {
                        return null;
                    } else {
                        return Integer.toString(cell.getIndex() + 1);
                    }
                }, cell.emptyProperty(), cell.indexProperty()));

                return cell;
            });
        }
        ////////////////////////////////index column created

        inventoryListTable.getColumns().add(indexColumn);
        indexColumn.setMinWidth(50);
        indexColumn.setMaxWidth(50);
    }

    //Solution adapted from: http://fxapps.blogspot.com/2012/09/showing-object-properties-in-tableview.html
    void setInventoryInfoColumns() {
        TableColumn<Item<Ingredient>, String> ingredientColumn = new TableColumn<>("Ingredient");
        ingredientColumn.setResizable(true);
        ingredientColumn.setCellValueFactory(itemStringCellDataFeatures ->
                new SimpleStringProperty(itemStringCellDataFeatures.getValue().getItem().getName()));

        TableColumn<Item<Ingredient>, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setResizable(true);
        quantityColumn.setCellValueFactory(itemStringCellDataFeatures ->
                new SimpleStringProperty(String.valueOf(itemStringCellDataFeatures.getValue().getQuantity().getNumber())));

        TableColumn<Item<Ingredient>, String> unitColumn = new TableColumn<>("Remarks");
        unitColumn.setResizable(true);
        unitColumn.setCellValueFactory(itemStringCellDataFeatures ->
                new SimpleStringProperty(itemStringCellDataFeatures.getValue().getItem().getRemarks()));

        inventoryListTable.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);
    }
}



