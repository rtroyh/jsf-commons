package com.gather.jsfcommons.util.sort;

import org.apache.log4j.Logger;

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

    public ColumnSorter(List<ISort> sortList) {
        this.sortList = sortList;
    }

    public int sort(Object o1,
                    Object o2) {
        try {
            for (ISort sorter : sortList) {
                if (sorter.check(o1,
                                 o2)) {
                    return sorter.sort(o1,
                                       o2);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return 0;
    }

    private boolean equals(Object valor1,
                           Object valor2) {
        return valor1.equals(valor2);
    }
}
