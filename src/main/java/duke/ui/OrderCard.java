package duke.ui;

import duke.logic.parser.commons.TimeParser;
import duke.model.commons.Item;
import duke.model.order.Order;
import duke.model.product.Product;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.Date;

/**
 * Controller for OrderCard. An OrderCard displays an order, including its creation time, customer, items,
 * delivery date, index, and status.
 */
public class OrderCard extends UiPart<AnchorPane> {
    private static final String FXML = "OrderCard.fxml";

    @FXML
    private AnchorPane innerPane;
    @FXML
    private FlowPane itemFlow;
    @FXML
    private Label id;
    @FXML
    private Label creationDate;
    @FXML
    private Label index;
    @FXML
    private Label deadline;
    @FXML
    private Label name;
    @FXML
    private Label contact;
    @FXML
    private Label remarks;
    @FXML
    private Label status;
    @FXML
    private Label total;
    @FXML
    private Label inventoryStatus;


    /**
     * Creates a card displaying the {@code order}.
     *
     * @param order          to display
     * @param displayedIndex the index of the order to show on the card
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);

        id.setText(Long.toString(order.getId()));
        creationDate.setText(order.getCreationDate().toString());
        index.setText(displayedIndex + ".");
        deadline.setText(TimeParser.convertDateToString(order.getDeliveryDate()));
        name.setText(order.getCustomer().name);
        contact.setText(order.getCustomer().contact);
        remarks.setText(order.getRemarks());
        total.setText(Double.toString(order.getTotal()));

        status.setText(order.getStatus().toString().toLowerCase());
        status.getStyleClass().clear();
        status.getStyleClass().addAll("status-" + order.getStatus().toString().toLowerCase());

        for (Item<Product> item : order.getItems()) {
            itemFlow.getChildren().add(
                    new OrderItemBox(item.getItem().getProductName(), item.getQuantity().getNumber())
            );
        }

        //Setup listener to update inventory status
        updateInventoryStatus(order.isIsIngredientEnough(), order.getStatus());
        order.isIngredientEnoughProperty().addListener(((observable, oldValue, newValue) -> {
            updateInventoryStatus(newValue, order.getStatus());
        }));

        //Clock to update deadline
        initializeClock(order);

    }

    private void updateInventoryStatus(boolean isIngredientEnough, Order.Status status) {
        if (!isIngredientEnough && status.equals(Order.Status.ACTIVE)) {
            inventoryStatus.setVisible(true);
        } else {
            inventoryStatus.setVisible(false);
        }
    }

    private void updateDeadline(Order.Status status, Date deliveryDate) {
        deadline.setText(TimeParser.convertDateToString(deliveryDate));
        if (Order.Status.ACTIVE.equals(status) && deliveryDate.before(Calendar.getInstance().getTime())) {
            deadline.getStyleClass().clear();
            deadline.getStyleClass().add("deadline-overdue");
        } else {
            deadline.getStyleClass().clear();
            deadline.getStyleClass().add("deadline-normal");
        }
    }

    private void initializeClock(Order order) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            deadline.setText(TimeParser.convertDateToString(order.getDeliveryDate()));
            if (Order.Status.ACTIVE.equals(order.getStatus())
                && order.getDeliveryDate().before(Calendar.getInstance().getTime())) {
                deadline.getStyleClass().clear();
                deadline.getStyleClass().add("deadline-overdue");
            } else {
                deadline.getStyleClass().clear();
                deadline.getStyleClass().add("deadline-normal");
            }
        }), new KeyFrame(javafx.util.Duration.seconds(2)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
