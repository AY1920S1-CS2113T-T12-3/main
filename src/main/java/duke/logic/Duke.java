package duke.logic;

import duke.command.Command;
import duke.commons.DukeException;
import duke.parser.BakingHomeParser;
import duke.parser.exceptions.ParseException;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.UiManager;

public class Duke {

    private static final Storage STORAGE = new Storage("baking.json");
    private static BakingList bakingList = new BakingList();
    private UiManager ui;
    private CommandManager commandManager;

    public Duke(UiManager ui) {
        this.ui = ui;
        try {
            bakingList = STORAGE.deserialize();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.disableInput();
        }

        commandManager = new CommandManager(bakingList, STORAGE, ui);
    }

    public void executeInput(String input) {
        try {
            Command command = new BakingHomeParser().parseCommand(input);
            commandManager.execute(command);
        } catch (DukeException | ParseException e) {
            ui.showError(e.getMessage());
        }
    }
}
