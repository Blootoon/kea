package cz.kea.api.utils;

import java.io.Serializable;

import cz.kea.api.enums.SortDirection;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class Order implements Serializable {

    private String column;
    private SortDirection sortDirection;

    public Order() {
    }

    public Order(String column, SortDirection sortDirection) {
        this.column = column;
        this.sortDirection = sortDirection;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return "Order{" +
            "column='" + column + '\'' +
            ", sortDirection=" + sortDirection +
            '}';
    }
}
