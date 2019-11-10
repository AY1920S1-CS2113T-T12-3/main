package duke.logic.command.product;

import duke.commons.core.LogsCenter;
import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.model.Model;

import java.util.logging.Logger;

import static duke.logic.message.ProductMessageUtils.MESSAGE_SUCCESS_SHOW_PRODUCT;

/**
 * A command to switch to Product Page.
 */
public class SwitchProductPageCommand extends ProductCommand {

    private static final Logger logger = LogsCenter.getLogger(SwitchProductPageCommand.class);
    public static final String COMMAND_WORD = "";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.getFilteredProductList();
        logger.fine(MESSAGE_SUCCESS_SHOW_PRODUCT);
        return new CommandResult(MESSAGE_SUCCESS_SHOW_PRODUCT, CommandResult.DisplayedPage.PRODUCT);
    }
}
