package cz.kea.api.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public enum State {

    DEAD,
    ADULT(DEAD),
    WEANED(ADULT, DEAD),
    HATCHED(WEANED, ADULT, DEAD),
    DEAD_EMBRYO,
    BROKEN,
    UNFERTILIZED,
    EGG(UNFERTILIZED, BROKEN, DEAD_EMBRYO, HATCHED);

    private List<State> allowedTransitions;

    State(State... allowedTransitions) {
        this.allowedTransitions = Arrays.asList(allowedTransitions);
    }

    public List<State> getAllowedTransitions() {
        return allowedTransitions;
    }

    public boolean isTransitionAllowed(State newState) {
        return allowedTransitions.contains(newState);
    }
}
