package duke.command;

import duke.commons.DukeException;
import duke.model.Sale;
import duke.parser.decrypted.CommandParser;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.UiManager;

import java.util.List;
import java.util.Map;

public class AddSale implements Undoable {
    private Map<String, List<String>> params;
    private Sale sale;

    public AddSale(Map<String, List<String>> params) throws DukeException {
        this.params = params;
    }

    public void execute(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {
        sale = new Sale();
        CommandParser.modifySale(params, sale);
        addSale(sale, bakingList);
        storage.serialize(bakingList);
        ui.refreshSaleList(bakingList.getSaleList(), bakingList.getSaleList());
    }

    @Override
    public void undo(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {
        bakingList.getSaleList().remove(sale);
        storage.serialize(bakingList);
        ui.refreshSaleList(bakingList.getSaleList(), bakingList.getSaleList());
    }

    @Override
    public void redo(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {
        execute(bakingList, storage, ui);
    }

    private void addSale(Sale sale, BakingList bakingList) {
        bakingList.getSaleList().add(0, sale);
    }

}