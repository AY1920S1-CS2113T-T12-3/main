package duke.parser;

import duke.command.AddOrderCommand;
import duke.command.Command;
import duke.command.RedoCommand;
import duke.command.UndoCommand;
import duke.commons.DukeException;
import duke.commons.Message;
import duke.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input into commands.
 */
public class Parser {

    private static final String COMMAND_ORDER = "order";
    private static final String COMMAND_ORDER_ADD = "add";
    /**
     * Parses user input into a command.
     *
     * @param line the user input.
     * @return the command from user input.
     * @throws DukeException if it is not valid command or command parameters are invalid.
     */
    public static Command getCommand(String line) throws DukeException {
        Map<String, List<String>> params = parseCommandAndParams(line);
        String commandWord = params.get("cmd").get(0);

        switch (commandWord) {
            case COMMAND_ORDER:
                return parseOrder(line);
        }
        throw new DukeException(Message.MESSAGE_UNKNOWN_COMMAND);
    }

    private static Map<String, List<String>> parseCommandAndParams(String line) throws DukeException {
        Map<String, List<String>> params = new HashMap<>();

        //Regex to get the command word and the primary command.
        Pattern commandWordPattern = Pattern.compile("^(\\w+)(\\s+[^-]+)?");
        Matcher commandWordMatcher = commandWordPattern.matcher(line);
        if (!commandWordMatcher.find()) {
            throw new DukeException("Please enter a command");
        }

        params.put("cmd", new ArrayList<String>() {{
            add(commandWordMatcher.group(1).strip());
        }});

        if (commandWordMatcher.group(2) != null) {
            params.put("primary", new ArrayList<String>() {{
                add(commandWordMatcher.group(2).strip());
            }});
        }

        //Regex to get each parameter block. e.g. "-at some place" is one command block.
        Pattern paramsPattern = Pattern.compile("(-\\w+ [^-]+|-\\w+)");
        Matcher paramsMatcher = paramsPattern.matcher(line);

        while (paramsMatcher.find()) {
            String s = paramsMatcher.group().strip();
            if (s.isEmpty() || s.isBlank()) continue;

            //Regex to get parameter and value. e.g. in "-at some place", "at" is the parameter and "some place" is the value.
            Pattern attrAndValuePattern = Pattern.compile("-(\\w+) ([^-]+)|-(\\w+)");
            Matcher attrAndValueMatcher = attrAndValuePattern.matcher(s);

            if (!attrAndValueMatcher.find()) {
                throw new DukeException("Please enter valid parameters");
            }

            if (attrAndValueMatcher.group(2) == null) {
                if (!params.containsKey(attrAndValueMatcher.group(3))) {
                    params.put(attrAndValueMatcher.group(3), new ArrayList<>() {{
                        add("");
                    }});
                } else {
                    params.get(attrAndValueMatcher.group(3)).add("");
                }
            } else {
                if (!params.containsKey(attrAndValueMatcher.group(1))) {
                    params.put(attrAndValueMatcher.group(1).strip(), new ArrayList<>() {{
                        add(attrAndValueMatcher.group(2));
                    }});
                } else {
                    params.get(attrAndValueMatcher.group(1).strip()).add(attrAndValueMatcher.group(2));
                }
            }
        }

        return params;
    }

    private static Command parseOrder(String line) throws DukeException {
        Map<String, List<String>> params = parseCommandAndParams(line);
        if (params.get("primary").get(0).equals(COMMAND_ORDER_ADD)) {
            return parseOrderAdd(line);
        }
        return null;
    }

    private static Command parseOrderAdd(String line) throws DukeException {
        Map<String, List<String>> params = parseCommandAndParams(line);

        if (!params.containsKey("name") || !params.containsKey("contact") || !params.containsKey("by") || !params.containsKey("item")) {
            throw new DukeException("Must have name, contact, deadline & item.");
        }

        Order order = new Order(params.get("name").get(0),
                params.get("contact").get(0),
                TimeParser.convertStringToDate(params.get("by").get(0)));

        for (String item : params.get("item")) {
            String[] itemAndQty = item.split(",");
            if (itemAndQty.length < 2) {
                throw new DukeException("Must have item name and quantity");
            }
            if (itemAndQty[0].strip().equals("") || itemAndQty[1].strip().equals("")) {
                throw new DukeException("Item name and quantity should not be empty");
            }
            try {
                order.addItem(itemAndQty[0].strip(), Integer.parseInt(itemAndQty[1].strip()));
            } catch (NumberFormatException e) {
                throw new DukeException("Quantity should be an integer.");
            }
        }

        return new AddOrderCommand(order);
        //order add -name jj -contact 12345678 -by 01/10/2019 18:00 -item bread, 1
    }
    private static Command parseUndo(String line) throws DukeException {
        return new UndoCommand();
    }

    private static Command parseRedo(String line) throws DukeException {
        return new RedoCommand();
    }
}

