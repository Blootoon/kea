package cz.kea.api.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class Page<T> implements Serializable {

    private long totalRecords;
    private List<T> items;
    private boolean previous = false;
    private boolean next = false;

    public Page() {
    }

    public Page(long totalRecords, List<T> items, boolean previous, boolean next) {
        this.totalRecords = totalRecords;
        this.items = items;
        this.previous = previous;
        this.next = next;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean hasPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean hasNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
}
