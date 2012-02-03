package com.gather.jsfcommons.component.datatable;

public interface ISelectedRowHolder<E extends Row> {

    public void setSelectedRow(E selectedRow);

    public E getSelectedRow();
}
