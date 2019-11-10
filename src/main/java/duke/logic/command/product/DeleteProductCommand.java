package duke.logic.command.product;

import duke.commons.core.index.Index;
import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.logic.message.ProductMessageUtils;
import duke.model.Model;
import duke.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static duke.logic.message.ProductMessageUtils.MESSAGE_DELETE_SUCCESS;
import static duke.logic.message.ProductMessageUtils.MESSAGE_INDEX_OUT_OF_BOUND;
import static java.util.Objects.requireNonNull;

/**
 * A command to delete products from Product List in {@code Model}.
 */
public class DeleteProductCommand extends ProductCommand {
    public static final String COMMAND_WORD = "remove";

    public static final String AUTO_COMPLETE_INDICATOR = ProductCommand.COMMAND_WORD + " " + COMMAND_WORD;

    private final Set<Index> indices;

    /** Creates a DeleteProductCommand.
     * @param indices of the products to delete
     */
    public DeleteProductCommand(Set<Index> indices) {
        requireNonNull(indices);
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Product> toDelete = new ArrayList<>();
        for (Index index : indices) {
            if (index.getZeroBased() >= model.getFilteredProductList().size()) {
                throw new CommandException(MESSAGE_INDEX_OUT_OF_BOUND);
            }
            toDelete.add(model.getFilteredProductList().get(index.getZeroBased()));
        }
        for (Product product : toDelete) {
            model.deleteProduct(product);
        }
        model.commit(ProductMessageUtils.MESSAGE_COMMIT_DELETE_PRODUCT);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, indices.size()),
                CommandResult.DisplayedPage.PRODUCT);
    }
}
