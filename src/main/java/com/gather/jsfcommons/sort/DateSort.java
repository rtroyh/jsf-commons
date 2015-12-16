package com.gather.jsfcommons.sort;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 16-04-14
 * Time: 12:10
 */
public class DateSort implements ISort {
    private static final Logger LOG = Logger.getLogger(DateSort.class);
    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private Pattern pattern;
    private Matcher matcher;

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

    @Override
    public int sort(Object o1,
                    Object o2) {
        LOG.debug("DATE: " + o1 + " - " + o2);

        if (o1 == null && o2 != null) {
            return -1;
        } else if (o1 == null) {
            return 0;
        } else if (o2 == null) {
            return 1;
        }

        Date date1 = this.getObjectAsDate(o1);
        Date date2 = this.getObjectAsDate(o2);

        if (date1.before(date2)) {
            return -1;
        } else if (date1.equals(date2)) {
            return 0;
        } else if (date1.after(date2)) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean check(Object o1,
                         Object o2) {
        return isDate(o1) && isDate(o2);
    }

    private Date getObjectAsDate(Object o) {
        Calendar c = Calendar.getInstance();

        pattern = Pattern.compile(DATE_PATTERN);
        String valor = o.toString().trim();
        matcher = pattern.matcher(valor);

        if (matcher.matches()) {
            matcher.reset();

            if (matcher.find()) {
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                int year = Integer.parseInt(matcher.group(3));

                c.set(Calendar.DAY_OF_MONTH,
                      day);
                c.set(Calendar.MONTH,
                      month - 1);
                c.set(Calendar.YEAR,
                      year);

                return c.getTime();
            }
        }

        return null;
    }
}
