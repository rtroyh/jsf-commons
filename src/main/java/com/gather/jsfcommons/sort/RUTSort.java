package com.gather.jsfcommons.sort;

import com.gather.gathercommons.util.Validator;
import org.apache.commons.lang3.StringUtils;
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
        LOG.debug("RUT: " + o1 + " - " + o2);

        String valor1 = o1.toString();
        String valor2 = o2.toString();

        valor1 = valor1.replaceAll("\\.",
                                   "");
        valor1 = valor1.replaceAll(",",
                                   ".");
        valor1 = valor1.replaceAll("-",
                                   "");

        valor2 = valor2.replaceAll("\\.",
                                   "");
        valor2 = valor2.replaceAll(",",
                                   ".");
        valor2 = valor2.replaceAll("-",
                                   "");

        Double d1 = Double.valueOf(valor1);
        Double d2 = Double.valueOf(valor2);

        if (d1 < d2) {
            return -1;
        } else if (d1.equals(d2)) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean check(Object o1,
                         Object o2) {
        return isRUT(o1) && isRUT(o2);
    }

    private boolean isRUT(Object o) {
        String valor = o.toString();
        boolean haveOneSlash = StringUtils.countMatches(valor,
                                                        "-") == 1;

        if (haveOneSlash) {
            String numberPart = valor.substring(0,
                                                valor.indexOf('-'));

            numberPart = numberPart.replaceAll("\\.",
                                               "");
            numberPart = numberPart.replaceAll(",",
                                               ".");

            return Validator.validateNumber(numberPart);
        } else {
            return false;
        }
    }
}
