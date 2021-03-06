package com.gather.jsfcommons.sort;

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

        if (o1 == null && o2 != null) {
            return -1;
        } else if (o1 == null) {
            return 0;
        } else if (o2 == null) {
            return 1;
        }

        String s1 = o1.toString();
        String s2 = o2.toString();

        if (s1.equals(s2)) {
            return 0;
        }

        String[] strings = {s1, s2};
        Arrays.sort(strings);

        LOG.debug("ORDEN: " + strings[0] + "," + strings[1]);

        if (strings[0].equals(s1)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean check(Object o1,
                         Object o2) {
        return (Validator.validateString(o1) && Validator.validateString(o2)) || (o1.toString().length() == 0 && Validator.validateString(o2)) || (Validator.validateString(o1) && o2.toString().length() == 0) || (o1.toString().length() == 0 && o2.toString().length() == 0);
    }
}
