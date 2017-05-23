package cz.kea.web.components.helpers;

import cz.kea.api.enums.Sex;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class SexHelper {

    public static final String MALE_SYMBOL = "♂";
    public static final String FEMALE_SYMBOL = "♀";
    public static final String UNKNOWN_SYMBOL = "?";

    public String symbolGenerator(Sex sex) {
        if (sex == null) {
            return UNKNOWN_SYMBOL;
        } else {
            switch (sex) {
                case MALE:
                    return MALE_SYMBOL;
                case FEMALE:
                    return FEMALE_SYMBOL;
                default:
                    return UNKNOWN_SYMBOL;
            }
        }
    }
}
