package cz.kea.api.utils;

import cz.kea.api.enums.FilterOperation;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Filter {

    String getPath();

    FilterOperation getFilterOperation();

    Class getClazz();

}
