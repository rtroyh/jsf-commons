package com.gather.jsfcommons.util;

import com.gather.gathercommons.util.Validator;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 14-04-14
 * Time: 13:03
 */
public class ColumnSorter {
    private static final Logger LOG = Logger.getLogger(ColumnSorter.class);

    public int sort(Object o1,
                    Object o2) {
        try {
            if (Validator.validateString(o1) && Validator.validateString(o2)) {
                String valor1 = o1.toString();
                String valor2 = o2.toString();

                if (equals(valor1,
                           valor2)) {
                    return 0;
                }

                valor1 = o1.toString().replaceAll("\\.",
                                                  "");
                valor1 = valor1.replaceAll(",",
                                           "");
                valor1 = valor1.replaceAll("-",
                                           "");

                valor2 = o2.toString().replaceAll("\\.",
                                                  "");
                valor2 = valor2.replaceAll(",",
                                           "");
                valor2 = valor2.replaceAll("-",
                                           "");

                if (!Validator.validateNumber(valor1) || !Validator.validateNumber(valor2)) {
                    LOG.debug("STRING: " + valor1 + " - " + valor2);
                    String[] strings = {o1.toString(), o2.toString()};
                    Arrays.sort(strings);

                    LOG.debug("ORDEN: " + strings[0] + "," + strings[1]);

                    if (strings[0].equals(o1.toString())) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    LOG.debug("NUMERO: " + valor1 + " - " + valor2);
                    valor1 = o1.toString().replaceAll("\\.",
                                                      "");
                    valor1 = valor1.replaceAll(",",
                                               ".");

                    valor2 = o2.toString().replaceAll("\\.",
                                                      "");
                    valor2 = valor2.replaceAll(",",
                                               ".");

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
            }
        } catch (NumberFormatException e) {
            LOG.error(e.getMessage());
        }

        return 0;
    }

    private boolean equals(String valor1,
                           String valor2) {
        if (valor1.equals(valor2)) {
            return true;
        }

        return false;
    }
}
