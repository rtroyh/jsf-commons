package com.gather.jsfcommons.util.sort;

import com.gather.gathercommons.util.Validator;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 16-04-14
 * Time: 12:20
 */
public class StringSort implements ISort {
    private static final Logger LOG = Logger.getLogger(StringSort.class);

    @Override
    public int sort(Object o1,
                    Object o2) {
        LOG.debug("STRING: " + o1 + " - " + o2);
        String[] strings = {o1.toString(), o2.toString()};
        Arrays.sort(strings);

        LOG.debug("ORDEN: " + strings[0] + "," + strings[1]);

        if (strings[0].equals(o1.toString())) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean check(Object o1,
                         Object o2) {
        return Validator.validateString(o1) && Validator.validateString(o2);
    }
}
