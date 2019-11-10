package duke.logic.parser.commons;

import duke.commons.core.Message;
import duke.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix> value <prefix> value ...}<br>
 * e.g. {@code some preamble text -name jiajun -contact 12345678 -i} where prefixes are {@code -name -contact -i}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code -i} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated.
 */
public class ArgumentTokenizer {

    private static final Pattern PREAMBLE_ARGS_FORMAT = Pattern.compile("^([^-]+)?\\s*(.*)");
    private static final Pattern PREFIX_VALUE_FORMAT = Pattern.compile("(-\\w*)\\s*([^-]+)?");

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with
     * @return ArgumentMultimap object that maps prefixes to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Prefix... prefixes) {
        String preamble = extractPreambleAndArgs(argsString).get(0).strip();
        String args = extractPreambleAndArgs(argsString).get(1).strip();
        ArgumentMultimap map = extractArgs(prefixes, argsString);
        map.put(new Prefix(""), preamble);
        return map;
    }

    private static List<String> extractPreambleAndArgs(String argString) {
        final Matcher matcher = PREAMBLE_ARGS_FORMAT.matcher(argString.strip());
        if (!matcher.matches()) {
            throw new ParseException(Message.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        List<String> res = new ArrayList<>();
        if (matcher.group(1) == null) {
            res.add("");
        } else {
            res.add(matcher.group(1));
        }
        if (matcher.group(2) == null) {
            res.add("");
        } else {
            res.add(matcher.group(2));
        }
        return res;
    }

    private static ArgumentMultimap extractArgs(Prefix[] prefixes, String args) {
        List<Prefix> prefixList = Arrays.asList(prefixes);
        ArgumentMultimap map = new ArgumentMultimap();
        final Matcher matcher = PREFIX_VALUE_FORMAT.matcher(args);
        while (matcher.find()) {
            String prefixString = matcher.group(1).strip();
            Prefix prefix = new Prefix(prefixString);
            String value;
            if (matcher.group(2) != null) {
                value = matcher.group(2).strip();
            } else {
                value = "";
            }
            if (prefixList.contains(prefix)) {
                map.put(prefix, value);
            } else {
                throw new ParseException(String.format(Message.MESSAGE_INVALID_PREFIX, prefixString));
            }
        }
        return map;
    }
}