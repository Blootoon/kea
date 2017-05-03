package cz.kea.web.components;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Wraps the functionality of Spring MessageSource providing slightly friendlier API.
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class KeaMessageSource {

    @Autowired
    private MessageSource messageSource;

    public String get(String key, Locale locale) {
        return messageSource.getMessage(key, new Object[]{}, locale);
    }

    public String get(String key, Locale locale, Object... parameters) {
        return messageSource.getMessage(key, parameters, locale);
    }

    public String get(Enum enumerated, Locale locale) {
        return messageSource.getMessage(enumerated.getClass().getCanonicalName() + "." + enumerated.name(), new Object[]{}, locale);
    }
}