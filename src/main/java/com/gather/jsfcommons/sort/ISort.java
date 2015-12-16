package com.gather.jsfcommons.sort;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 16-04-14
 * Time: 12:07
 */
public interface ISort {
    int sort(Object o1,
             Object o2) throws
                        Exception;

    boolean check(Object o1,
                  Object o2);
}
