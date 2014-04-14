package com.gather.jsfcommons.util;

import com.gather.gathercommons.util.Validator;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 14-04-14
 * Time: 13:03
 */
public class ColumnSorter {
    private static final Logger LOG = Logger.getLogger(ColumnSorter.class);
    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private Pattern pattern;
    private Matcher matcher;

    private boolean isNumber(Object o) {
        String valor = o.toString();
        valor = valor.replaceAll("\\.",
                                 "");
        valor = valor.replaceAll(",",
                                 "");
        valor = valor.replaceAll("-",
                                 "");

        return Validator.validateNumber(valor);
    }

    private boolean isDate(Object o) {
        pattern = Pattern.compile(DATE_PATTERN);
        String valor = o.toString().trim();
        matcher = pattern.matcher(valor);

        if (matcher.matches()) {
            matcher.reset();

            if (matcher.find()) {
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11") || month.equals("04") || month.equals("06") || month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        return !(day.equals("30") || day.equals("31"));
                    } else {
                        return !(day.equals("29") || day.equals("30") || day.equals("31"));
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int sort(Object o1,
                    Object o2) {
        try {
            boolean areString = areString(o1,
                                          o2);
            if (areString) {
                String valor1 = o1.toString();
                String valor2 = o2.toString();

                if (equals(valor1,
                           valor2)) {
                    return 0;
                }

                if (isNumber(o1) && isNumber(o2)) {
                    LOG.debug("NUMBER: " + valor1 + " - " + valor2);
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
                } else if (isDate(o1) && isDate(o2)) {
                    LOG.debug("DATE: " + valor1 + " - " + valor2);
                } else {
                    LOG.debug("STRING: " + valor1 + " - " + valor2);
                    String[] strings = {o1.toString(), o2.toString()};
                    Arrays.sort(strings);

                    LOG.debug("ORDEN: " + strings[0] + "," + strings[1]);

                    if (strings[0].equals(o1.toString())) {
                        return -1;
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

    private boolean areString(Object o1,
                              Object o2) {
        return Validator.validateString(o1) && Validator.validateString(o2);
    }

    private boolean equals(Object valor1,
                           Object valor2) {
        if (valor1.equals(valor2)) {
            return true;
        }

        return false;
    }
}
