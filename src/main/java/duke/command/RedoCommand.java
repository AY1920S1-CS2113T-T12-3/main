package duke.command;

import duke.commons.DukeException;
import duke.storage.BakingList;
import duke.storage.Storage;
import duke.ui.UiManager;

/**
 * A command that restores any actions that have been previously undone using undo.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    @Override
    public void execute(BakingList bakingList, Storage storage, UiManager ui) throws DukeException {

    }

}
