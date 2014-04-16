package com.gather.jsfcommons.util.sort;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 16-04-14
 * Time: 12:35
 */
public class NullSort implements ISort {
    private static final Logger LOG = Logger.getLogger(NullSort.class);

    @Override
    public int sort(Object o1,
                    Object o2) {
        LOG.debug("NULL: " + o1 + " - " + o2);

        if (o1 == null && o2 == null) {
            return 0;
        }

        if (o1 != null) {
            return 1;
        }

        return -1;
    }

    @Override
    public boolean check(Object o1,
                         Object o2) {
        return o1 == null || o2 == null;
    }
}
