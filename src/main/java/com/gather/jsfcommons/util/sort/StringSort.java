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
        String s = o1.toString();
        String s1 = o2.toString();

        if (s.equals(s1)) {
            return 0;
        }

        String[] strings = {s, s1};
        Arrays.sort(strings);

        LOG.debug("ORDEN: " + strings[0] + "," + strings[1]);

        if (strings[0].equals(s)) {
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
