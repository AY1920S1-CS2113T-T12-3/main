package duke.logic.command.inventory;

import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.logic.message.InventoryMessageUtils;
import duke.logic.parser.commons.CliSyntax;
import duke.logic.parser.commons.Prefix;
import duke.model.Model;
import duke.model.commons.Item;
import duke.model.inventory.Ingredient;

import static java.util.Objects.requireNonNull;

public class AddInventoryCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "add";

    public static final String AUTO_COMPLETE_INDICATOR = InventoryCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final Prefix[] AUTO_COMPLETE_PARAMETERS = {
        CliSyntax.PREFIX_INVENTORY_NAME,
        CliSyntax.PREFIX_INVENTORY_QUANTITY,
        CliSyntax.PREFIX_INVENTORY_REMARKS
    };

    private final Item<Ingredient> toAdd;

    public AddInventoryCommand(Item<Ingredient> toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInventory(toAdd)) {
            throw new CommandException(String.format(InventoryMessageUtils.MESSAGE_DUPLICATE_INVENTORY,
                    toAdd.getItem().getName()));
        }

        model.addInventory(toAdd);
        model.commit(InventoryMessageUtils.MESSAGE_COMMIT_ADD_INVENTORY);

        return new CommandResult(String.format(InventoryMessageUtils.MESSAGE_SUCCESS_ADD_INVENTORY,
                toAdd.getItem().getName()),
                CommandResult.DisplayedPage.INVENTORY);
    }
}
