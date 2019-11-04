package duke.logic.parser.commons;

import duke.commons.core.Message;
import duke.logic.parser.exceptions.ParseException;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import java.text.SimpleDateFormat;
import java.util.Date;

//@@author liujiajun
/**
 * Parser to parse time.
 */
public class TimeParser {
    private static PrettyTime prettyTime = new PrettyTime();
    private static PrettyTimeParser prettyTimeParser = new PrettyTimeParser();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy HH:mm");

    /**
     * If the difference (in absolute number) between the time given and current dime is within
     * this threshold, TimeParser returns the human-friendly representation of time.
     * Otherwise, TimeParser returns time in format specified in {@code dateFormat}.
     */
    private static final long THRESHOLD_TIME_MILLIS = 1000 * 3600 * 24 * 5;


    /**
     * Converts a Date object to a string representing the date.
     *
     * @param date Date object to convert
     * @return a string representing the date
     */
    public static String convertDateToString(Date date) {
        if (Math.abs(date.getTime() - System.currentTimeMillis()) > THRESHOLD_TIME_MILLIS) {
            return dateFormat.format(date);
        } else {
            return prettyTime.format(date);
        }
    }


    /**
     * Converts a string representing the date to a Date object.
     * TODO: Update docs
     * @param str a string representing the date. Can be either in the format {@code MM/DD/yyyy HH:mm},
     *            or in natural language.
     *            for example, "10/30/1999 18:00" (in specified format),
     *            "tomorrow" (in natural language)
     * @return the date based on the string.
     * @throws ParseException if the string cannot be parsed into a date.
     */
    public static Date convertStringToDate(String str) throws ParseException {
        //List<Date> dates = prettyTimeParser.parse(str);
        //if (dates.isEmpty()) {
        //    throw new ParseException(Message.MESSAGE_INVALID_DATE);
        //}
        //return dates.get(0);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            simpleDateFormat.setLenient(false);
            return simpleDateFormat.parse(str);
        } catch (java.text.ParseException e) {
            throw new ParseException(Message.MESSAGE_INVALID_DATE);
        }
    }
}
