package com.gather.jsfcommons.component.datatable;

import javax.faces.event.ActionEvent;
import java.util.*;

@SuppressWarnings("unchecked")
public abstract class Row implements List<Object> {

    private List<Object> data;

    private ISelectedRowHolder selectedRowHolder;

    public Row(Row row) {
        super();
        this.data = new ArrayList<Object>(row.getData());
        this.selectedRowHolder = row.getSelectedRowHolder();
    }

    public Row(List<Object> data,
               ISelectedRowHolder selectedRowHolder) {
        super();
        this.data = data;
        this.selectedRowHolder = selectedRowHolder;
    }

    public ISelectedRowHolder getSelectedRowHolder() {
        return selectedRowHolder;
    }

    public void setSelectedRowHolder(ISelectedRowHolder selectedRowHolder) {
        this.selectedRowHolder = selectedRowHolder;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    @Override
    public boolean add(Object e) {
        return this.getData().add(e);
    }

    @Override
    public void add(int index,
                    Object element) {
        this.getData().add(index,
                           element);
    }

    @Override
    public boolean addAll(Collection<? extends Object> c) {
        return this.getData().addAll(c);
    }

    @Override
    public boolean addAll(int index,
                          Collection<? extends Object> c) {
        return this.getData().addAll(index,
                                     c);
    }

    @Override
    public void clear() {
        this.getData().clear();
    }

    @Override
    public boolean contains(Object o) {
        return this.getData().contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.getData().containsAll(c);
    }

    @Override
    public Object get(int index) {
        return this.getData().get(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.getData().indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return this.getData().isEmpty();
    }

    @Override
    public Iterator<Object> iterator() {
        return this.getData().iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.getData().lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return this.getData().listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return this.getData().listIterator(index);
    }

    @Override
    public boolean remove(Object o) {
        return this.getData().remove(o);
    }

    @Override
    public Object remove(int index) {
        return this.getData().remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.getData().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.getData().retainAll(c);
    }

    @Override
    public Object set(int index,
                      Object element) {
        return this.getData().set(index,
                                  element);
    }

    @Override
    public int size() {
        return this.getData().size();
    }

    @Override
    public List<Object> subList(int fromIndex,
                                int toIndex) {
        return this.getData().subList(fromIndex,
                                      toIndex);
    }

    @Override
    public Object[] toArray() {
        return this.getData().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.getData().toArray(a);
    }

    public abstract void select(final ActionEvent event);

}
