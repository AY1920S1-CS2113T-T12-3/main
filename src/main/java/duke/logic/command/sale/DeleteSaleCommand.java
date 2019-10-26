package duke.logic.command.sale;

import duke.commons.core.index.Index;
import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.model.Model;
import duke.model.sale.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * A command to delete sales from Sale List.
 */
public class DeleteSaleCommand extends SaleCommand {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_COMMIT = "Delete sale";
    private static final String MESSAGE_DELETE_SUCCESS = "%s sale(s) removed.";
    private static final String MESSAGE_INDEX_OUT_OF_BOUND = "Index [%d] is out of bound.";
    private final Set<Index> indices;

    /**
     * Creates a {@code DeleteProductCommand}.
     *
     * @param indices of the sales to delete
     */
    public DeleteSaleCommand(Set<Index> indices) {
        requireNonNull(indices);

        this.indices = indices;
    }


    public CommandResult execute(Model model) throws CommandException {
        List<Sale> toDelete = new ArrayList<>();
        for (Index index : indices) {
            if (index.getZeroBased() >= model.getFilteredSaleList().size()) {
                throw new CommandException(String.format(MESSAGE_INDEX_OUT_OF_BOUND, index.getOneBased()));
            }
            toDelete.add(model.getFilteredSaleList().get(index.getZeroBased()));
        }
        for (Sale sale : toDelete) {
            model.deleteSale(sale);
        }

        model.commit(MESSAGE_COMMIT);

        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, indices.size()),
                CommandResult.DisplayedPage.SALE);

    }

}