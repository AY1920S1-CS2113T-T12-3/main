package duke.logic.parser.shopping;

import duke.commons.core.Message;
import duke.commons.core.index.Index;
import duke.logic.command.shopping.BuyShoppingCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.commons.ParserUtil;
import duke.logic.parser.exceptions.ParseException;

import java.util.Set;

public class BuyShoppingCommandParser implements Parser<BuyShoppingCommand> {

    @Override
    public BuyShoppingCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args);

        Set<Index> indices;

        try {
            indices = ParserUtil.getIndices(map.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(Message.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new BuyShoppingCommand(indices);
    }
}
