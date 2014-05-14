package com.gather.jsfcommons.util;

import com.gather.jsfcommons.util.sort.NumberSort;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 14-05-14
 * Time: 16:57
 */
public class NumberSortTest {
    private NumberSort numberSort;

    @BeforeClass
    public void initData() {
        numberSort = new NumberSort();
    }

    @DataProvider
    public Object[][] esNumeroDataProvider() {
        return new Object[][]{
                new Object[]{"1.011,8888", true},
                new Object[]{4438, true},
                new Object[]{4000, true},
                new Object[]{"444.3456", true},
                new Object[]{"01", true}};
    }

    @Test(dataProvider = "esNumeroDataProvider")
    public void validEsNumeroTest(Object o1,
                                  boolean result) {
        boolean valid = numberSort.check(o1,
                                         o1);

        Assert.assertEquals(result,
                            valid);
    }

}
