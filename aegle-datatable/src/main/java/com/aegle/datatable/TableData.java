package com.aegle.datatable;

import java.util.Collection;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 * @since 1.0
 */
public class TableData<T> {
    private Collection<T> rows;
    private Long total;

    public TableData(Collection<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

