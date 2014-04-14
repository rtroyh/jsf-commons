package com.gather.jsfcommons.util;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsfcommons
 * User: rodrigotroy
 * Date: 14-04-14
 * Time: 16:14
 */
public class ColumnSorterTest {
    private ColumnSorter columnSorter;

    @BeforeClass
    public void initData() {
        columnSorter = new ColumnSorter();
    }

    @DataProvider
    public Object[][] ValidDateProvider() {
        return new Object[][]{new Object[]{"1/1/2010", "1/1/2010"}, new Object[]{"01/01/2020", "1/1/2010"}};
    }

    @Test(dataProvider = "ValidDateProvider")
    public void ValidDateTest(Object o1,
                              Object o2) {
        int valid = columnSorter.sort(o1,
                                      o2);
        System.out.println(valid);

        Assert.assertEquals(1,
                            valid);
    }
}
