package com.gather.jsfcommons.util.sort;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 16-04-14
 * Time: 12:34
 */
public class RUTSort implements ISort {
    private static final Logger LOG = Logger.getLogger(RUTSort.class);

    @Override
    public int sort(Object o1,
                    Object o2) {
        return 0;
    }

    @Override
    public boolean check(Object o1,
                         Object o2) {
        return false;
    }
}
