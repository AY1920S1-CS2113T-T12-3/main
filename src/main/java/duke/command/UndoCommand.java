package duke.command;

import duke.commons.DukeException;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.UiManager;

/**
 * A command that reverses the action of an earlier action.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    @Override
    public void execute(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {

    }

}
