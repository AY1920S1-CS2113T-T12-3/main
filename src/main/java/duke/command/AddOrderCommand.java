package duke.command;

import duke.commons.DukeException;
import duke.entities.Order;
import duke.parser.CommandParser;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.*;

/**
 * A command to add an <code>Order</code> object to an <code>OrderList</code> object.
 */
public class AddOrderCommand extends UndoableCommand {
    private static final Set<String> acceptedParameters = new HashSet<>(Arrays.asList(
            "line", "primary", "secondary", "cmd", "item", "name", "contact", "rmk", "by", "status"
    ));
    private final Map<String, List<String>> params;
    private Order order;

    /**
     * Class constructor.
     *
     * @param params The parameters specifying details of the order.
     */
    public AddOrderCommand(Map<String, List<String>> params) throws DukeException {
        this.params = params;
        checkParameters();
    }

    @Override
    public void execute(BakingList bakingList, Storage storage, Ui ui) throws DukeException {
        order = new Order();
        CommandParser.modifyOrdrer(params, order);
        addOrder(order, bakingList);
        storage.serialize(bakingList);
        ui.refreshOrderList(bakingList.getOrderList(), bakingList.getOrderList());
        ui.showMessage("Order added");
    }

    @Override
    public void undo(BakingList bakingList, Storage storage, Ui ui) throws DukeException {
        bakingList.getOrderList().remove(order);
        storage.serialize(bakingList);
        ui.refreshOrderList(bakingList.getOrderList(), bakingList.getOrderList());
        ui.showMessage("Undo: Add order");
    }

    @Override
    public void redo(BakingList bakingList, Storage storage, Ui ui) throws DukeException {
        execute(bakingList, storage, ui);
        ui.showMessage("Redo: Add order");
    }

    private void addOrder(Order order, BakingList bakingList) {
        bakingList.getOrderList().add(0, order);
    }

    private void checkParameters() throws DukeException {
        for (String key : params.keySet()) {
            if (!acceptedParameters.contains(key)) {
                throw new DukeException("Invalid parameter: " + key);
            }
        }
    }
}
