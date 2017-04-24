package cz.kea.api.model;

import java.io.Serializable;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Identifiable<ID extends Serializable> extends Serializable {

    ID getId();
}
