package cz.kea.api.services;

import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface BirdService extends BaseService<Bird, Long> {

    Bird changeState(Bird bird, State newState);
}
