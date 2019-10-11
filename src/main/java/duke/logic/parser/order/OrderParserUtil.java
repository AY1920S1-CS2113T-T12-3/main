package duke.logic.parser.order;

import duke.commons.core.Message;
import duke.logic.command.order.OrderDescriptor;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.TimeParser;
import duke.logic.parser.exceptions.ParseException;
import duke.model.commons.Item;
import duke.model.order.Order;
import duke.model.order.Quantity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static duke.logic.parser.commons.CliSyntax.*;

class OrderParserUtil {

    static Set<Item<String>> parseItems(List<String> itemArg) throws ParseException {
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
                Item<String> item = new Item<>(itemAndQty[0].strip(),
                        new Quantity(Integer.parseInt(itemAndQty[1].strip())));
                items.add(item);
            } catch (NumberFormatException e) {
                throw new ParseException(Message.MESSAGE_INVALID_NUMBER_FORMAT);
            }
        }
        return items;
    }

    static Order.Status parseStatus(String statusString) throws ParseException {
        try {
            return Order.Status.valueOf(statusString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(Message.MESSAGE_INVALID_STATUS);
        }
    }

    static OrderDescriptor createDescriptor(ArgumentMultimap map) {
        OrderDescriptor descriptor = new OrderDescriptor();

        if (map.getValue(PREFIX_CUSTOMER_NAME).isPresent()) {
            descriptor.setCustomerName(map.getValue(PREFIX_CUSTOMER_NAME).get());
        }
        if (map.getValue(PREFIX_CUSTOMER_CONTACT).isPresent()) {
            descriptor.setCustomerContact(map.getValue(PREFIX_CUSTOMER_CONTACT).get());
        }
        if (map.getValue(PREFIX_ORDER_DEADLINE).isPresent()) {
            descriptor.setDeliveryDate(TimeParser.convertStringToDate(
                    map.getValue(PREFIX_ORDER_DEADLINE).get()));
        }
        if (map.getValue(PREFIX_ORDER_REMARKS).isPresent()) {
            descriptor.setRemarks(map.getValue(PREFIX_ORDER_REMARKS).get());
        }
        if (map.getValue(PREFIX_ORDER_ITEM).isPresent()) {
            descriptor.setItems(OrderParserUtil.parseItems(map.getAllValues(PREFIX_ORDER_ITEM)));
        }
        if (map.getValue(PREFIX_ORDER_STATUS).isPresent()) {
            descriptor.setStatus(OrderParserUtil.parseStatus(map.getValue(PREFIX_ORDER_STATUS).get()));
        }
        return descriptor;
    }
}
