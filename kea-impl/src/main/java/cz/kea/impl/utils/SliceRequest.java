package cz.kea.impl.utils;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class SliceRequest implements Pageable, Serializable {

    private final int offset;
    private final int limit;
    private final Sort sort;

    public SliceRequest(int offset, int limit) {
        this(offset, limit, null);
    }


    public SliceRequest(int offset, int limit, Sort sort) {
        Validate.isTrue(offset >= 0, "Offset must be greater or equal than zero.");
        Validate.isTrue(limit > 0, "Limit must be greater than zero.");

        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public Pageable previous() {
        return new SliceRequest(getOffset() - getPageSize(), getPageSize(), getSort());
    }

    @Override
    public Pageable next() {
        return new SliceRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new SliceRequest(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return getOffset() > getPageSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        SliceRequest that = (SliceRequest) o;

        return new EqualsBuilder().append(offset, that.offset).append(limit, that.limit).append(sort, that.sort).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(offset).append(limit).append(sort).toHashCode();
    }

    @Override
    public String toString() {
        return "SliceRequest{" +
            "offset=" + offset +
            ", limit=" + limit +
            ", sort=" + sort +
            '}';
    }
}
