package duke.logic;

import duke.command.Command;
import duke.commons.DukeException;
import duke.parser.Parser;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {

    private static final Storage STORAGE = new Storage("baking.json");
    private static BakingList bakingList = new BakingList();
    private Ui ui;
    private CommandManager commandManager;

    public Duke(Ui ui) {
        this.ui = ui;
        try {
            bakingList = STORAGE.deserialize();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.disableInput();
        }
        ui.refreshOrderList(bakingList.getOrderList(), bakingList.getOrderList());
        commandManager = new CommandManager(bakingList, STORAGE, ui);
    }

    public void executeInput(String input) {

        try {
            Command command = Parser.getCommand(input);
            commandManager.execute(command);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

}
