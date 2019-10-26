package duke.logic.parser.sale;

import duke.logic.command.sale.DeleteSaleCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.commons.ParserUtil;
import duke.logic.parser.exceptions.ParseException;

public class DeleteSaleCommandParser implements Parser<DeleteSaleCommand> {
    private static final String MESSAGE_EMPTY_INDICES = "Indices cannot be empty.";

    @Override
    public DeleteSaleCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args);

        if (map.getPreamble().isBlank()) {
            throw new ParseException(MESSAGE_EMPTY_INDICES);
        }


        return new DeleteSaleCommand(ParserUtil.getIndices(map.getPreamble()));
    }

}