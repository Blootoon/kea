package cz.kea.web.components;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class EnumHelper {

    @Autowired
    private KeaMessageSource messageSource;

    public String nameGenerator(Enum enumerated, Locale locale) {
        if (enumerated == null) {
            return null;
        } else {
            return messageSource.get(enumerated, locale);
        }
    }
}
