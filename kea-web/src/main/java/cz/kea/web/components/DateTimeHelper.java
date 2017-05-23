package cz.kea.web.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class DateTimeHelper {

    private Map<Locale, DateTimeFormatter> dateFormatters = new HashMap<>();
    private Map<Locale, DateTimeFormatter> dateTimeFormatters = new HashMap<>();

    public String dateFormatter(LocalDate localDate, Locale locale) {
        Validate.notNull(locale);

        if (localDate == null) {
            return null;
        } else {
            return getDateFormatter(locale).format(localDate);
        }
    }

    public String dateTimeFormatter(LocalDateTime localDateTime, Locale locale) {
        Validate.notNull(locale);

        if (localDateTime == null) {
            return null;
        } else {
            return getDateTimeFormatter(locale).format(localDateTime);
        }
    }

    private DateTimeFormatter getDateFormatter(Locale locale) {
        synchronized (dateFormatters) {
            if (dateFormatters.containsKey(locale)) {
                return dateFormatters.get(locale);
            } else {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
                dateFormatters.put(locale, dateTimeFormatter);
                return dateTimeFormatter;
            }
        }
    }

    private DateTimeFormatter getDateTimeFormatter(Locale locale) {
        synchronized (dateTimeFormatters) {
            if (dateTimeFormatters.containsKey(locale)) {
                return dateTimeFormatters.get(locale);
            } else {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
                dateTimeFormatters.put(locale, dateTimeFormatter);
                return dateTimeFormatter;
            }
        }
    }
}
