package duke.logic.parser.inventory;

import duke.logic.command.inventory.AddInventoryCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.exceptions.ParseException;
import duke.model.commons.Item;
import duke.model.commons.Quantity;
import duke.model.inventory.Ingredient;
import org.ocpsoft.prettytime.shade.org.apache.commons.lang.StringUtils;

import static duke.logic.parser.commons.CliSyntax.PREFIX_INVENTORY_NAME;
import static duke.logic.parser.commons.CliSyntax.PREFIX_INVENTORY_QUANTITY;
import static duke.logic.parser.commons.CliSyntax.PREFIX_INVENTORY_REMARKS;

/**
 * A parser that parses {@code AddInventoryCommand}.
 */
public class AddInventoryCommandParser implements Parser<AddInventoryCommand> {

    private static final String DEFAULT_QUANTITY = "0.0";
    private static final String EMPTY_STRING = "";

    @Override
    public AddInventoryCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_INVENTORY_NAME,
                PREFIX_INVENTORY_QUANTITY,
                PREFIX_INVENTORY_REMARKS
        );

        if (map.getValue(PREFIX_INVENTORY_QUANTITY).isPresent()) {
            InventoryParserUtil.checkValidQuantity(map.getValue(PREFIX_INVENTORY_QUANTITY).get());
        }

        Ingredient ingredient = new Ingredient(
                StringUtils.capitalize(map.getValue(PREFIX_INVENTORY_NAME).orElse(EMPTY_STRING).toLowerCase()),
                map.getValue(PREFIX_INVENTORY_REMARKS).orElse(EMPTY_STRING)
        );

        Quantity quantity = new Quantity(
                Double.parseDouble(map.getValue(PREFIX_INVENTORY_QUANTITY).orElse(DEFAULT_QUANTITY))
        );

        Item<Ingredient> inventory = new Item<Ingredient>(ingredient, quantity);

        return new AddInventoryCommand(inventory);
    }
}
