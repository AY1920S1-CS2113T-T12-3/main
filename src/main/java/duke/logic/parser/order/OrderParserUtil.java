package duke.logic.parser.order;

import duke.commons.core.Message;
import duke.logic.command.order.OrderDescriptor;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.TimeParser;
import duke.logic.parser.exceptions.ParseException;
import duke.model.commons.Item;
import duke.model.commons.Quantity;
import duke.model.order.Order;
import duke.model.order.Remark;
import duke.model.order.TotalPrice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static duke.logic.parser.commons.CliSyntax.PREFIX_CUSTOMER_CONTACT;
import static duke.logic.parser.commons.CliSyntax.PREFIX_CUSTOMER_NAME;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_DEADLINE;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_ITEM;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_REMARKS;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_STATUS;
import static duke.logic.parser.commons.CliSyntax.PREFIX_ORDER_TOTAL;

/**
 * A utility class for order parser.
 */
class OrderParserUtil {

    /**
     * Returns an {@code OrderDescriptor} from the given {@code ArgumentMultimap}.
     */
    static OrderDescriptor createDescriptor(ArgumentMultimap map) {
        OrderDescriptor descriptor = new OrderDescriptor();

        if (map.getValue(PREFIX_CUSTOMER_NAME).isPresent()) {
            String value = map.getValue(PREFIX_CUSTOMER_NAME).get();
            descriptor.setCustomerName(value);
        }
        if (map.getValue(PREFIX_CUSTOMER_CONTACT).isPresent()) {
            String value = map.getValue(PREFIX_CUSTOMER_CONTACT).get();
            descriptor.setCustomerContact(value);
        }
        if (map.getValue(PREFIX_ORDER_DEADLINE).isPresent()) {
            descriptor.setDeliveryDate(TimeParser.convertStringToDate(
                map.getValue(PREFIX_ORDER_DEADLINE).get()));
        }
        if (map.getValue(PREFIX_ORDER_REMARKS).isPresent()) {
            String value = map.getValue(PREFIX_ORDER_REMARKS).get();
            descriptor.setRemarks(new Remark(value));
        }
        if (map.getValue(PREFIX_ORDER_ITEM).isPresent()) {
            descriptor.setItems(parseItems(map.getAllValues(PREFIX_ORDER_ITEM)));
        }
        if (map.getValue(PREFIX_ORDER_STATUS).isPresent()) {
            descriptor.setStatus(parseStatus(map.getValue(PREFIX_ORDER_STATUS).get()));
        }
        if (map.getValue(PREFIX_ORDER_TOTAL).isPresent()) {
            descriptor.setTotal(parseTotal(map.getValue(PREFIX_ORDER_TOTAL).get()));
        }
        return descriptor;
    }

    private static Set<Item<String>> parseItems(List<String> itemArg) throws ParseException {
        Set<Item<String>> items = new HashSet<>();
        for (String itemString : itemArg) {
            String[] itemAndQty = itemString.split(",");
            if (itemAndQty.length < 2) {
                throw new ParseException(Message.MESSAGE_ITEM_MISSING_NAME_OR_QUANTITY);
            }
            if (itemAndQty[0].strip().equals("") || itemAndQty[1].strip().equals("")) {
                throw new ParseException(Message.MESSAGE_ITEM_MISSING_NAME_OR_QUANTITY);
            }

            try {
                double amount = Double.parseDouble(itemAndQty[1].strip());

                Item<String> item = new Item<>(itemAndQty[0].strip(),
                    new Quantity(amount));
                items.add(item);
            } catch (NumberFormatException e) {
                throw new ParseException(Message.MESSAGE_INVALID_QUANTITY);
            }
        }
        return items;
    }


    private static Order.Status parseStatus(String statusString) throws ParseException {
        try {
            return Order.Status.valueOf(statusString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(Message.MESSAGE_INVALID_STATUS);
        }
    }

    private static TotalPrice parseTotal(String totalString) throws ParseException {
        try {
            return new TotalPrice(Double.parseDouble(totalString));
        } catch (NumberFormatException e) {
            throw new ParseException(Message.MESSAGE_INVALID_NUMBER_FORMAT);
        }
    }

}
