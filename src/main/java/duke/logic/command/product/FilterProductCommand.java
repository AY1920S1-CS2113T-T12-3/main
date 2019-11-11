package duke.logic.command.product;

import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.logic.message.ProductMessageUtils;
import duke.logic.parser.commons.CliSyntax;
import duke.logic.parser.commons.Prefix;
import duke.model.Model;

/**
 * A command to filter products from Product List.
 */
public class FilterProductCommand extends ProductCommand {

    /**
     * Constructs a FilterProductCommand with the given {@code Scope}
     *
     * @param scope scope of the products that needs to be shown
     */
    public FilterProductCommand(Scope scope) {
        this.scope = scope;
    }

    public static final String COMMAND_WORD = "filter";

    private Scope scope;
    public static final String AUTO_COMPLETE_INDICATOR = ProductCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final Prefix[] AUTO_COMPLETE_PARAMETERS = {
        CliSyntax.PREFIX_PRODUCT_SCOPE
    };

    /**
     * The Scope of the products that needs to be shown.
     */
    public enum Scope {
        ALL,
        ACTIVE,
        ARCHIVE
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        switch (scope) {
        case ALL:
            model.updateFilteredProductList(Model.PREDICATE_SHOW_ALL_PRODUCTS);
            break;
        case ACTIVE:
            model.updateFilteredProductList(Model.PREDICATE_SHOW_ACTIVE_PRODUCTS);
            break;
        case ARCHIVE:
            model.updateFilteredProductList(Model.PREDICATE_SHOW_ARCHIVE_PRODUCTS);
            break;
        default:
            break;
        }
        return new CommandResult(String.format(ProductMessageUtils.MESSAGE_LIST_SCOPE, scope),
            CommandResult.DisplayedPage.PRODUCT);
    }
}
