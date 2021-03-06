package com.gather.jsfcommons.sort;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 14-04-14
 * Time: 13:03
 */
public class ColumnSorter {
    private static final Logger LOG = Logger.getLogger(ColumnSorter.class);

    private List<ISort> sortList;

    public ColumnSorter() {
    }

    public ColumnSorter(List<ISort> sortList) {
        this.sortList = sortList;
    }

    public List<ISort> getSortList() {
        if (sortList == null) {
            sortList = new ArrayList();
        }

        return sortList;
    }

    public void setSortList(List<ISort> sortList) {
        this.sortList = sortList;
    }

    public int sort(Object o1,
                    Object o2) {
        LOG.debug("DATOS A PROCESAR: " + o1 + "  " + o2);

        try {
            for (ISort sorter : sortList) {
                if (sorter.check(o1,
                                 o2)) {
                    return sorter.sort(o1,
                                       o2);
                }
            }
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }

        return 0;
    }
}
