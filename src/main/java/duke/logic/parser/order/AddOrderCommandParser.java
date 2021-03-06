package duke.logic.parser.order;

import duke.commons.core.Message;
import duke.logic.command.order.AddOrderCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.exceptions.ParseException;

import static duke.logic.parser.commons.CliSyntax.PREFIX_CUSTOMER_CONTACT;
import static duke.logic.parser.commons.CliSyntax.PREFIX_CUSTOMER_NAME;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_DEADLINE;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_ITEM;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_REMARKS;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_STATUS;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_TOTAL;
import static duke.logic.parser.order.OrderParserUtil.createDescriptor;

/**
 * A parser that parses {@code AddOrderCommand}.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {
    @Override
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(
            args,
            PREFIX_CUSTOMER_NAME,
            PREFIX_CUSTOMER_CONTACT,
            PREFIX_ORDER_ITEM,
            PREFIX_ORDER_DEADLINE,
            PREFIX_ORDER_STATUS,
            PREFIX_ORDER_REMARKS,
            PREFIX_ORDER_TOTAL
        );

        if (!map.getPreamble().isBlank()) {
            throw new ParseException(Message.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        
        return new AddOrderCommand(createDescriptor(map));
    }
}