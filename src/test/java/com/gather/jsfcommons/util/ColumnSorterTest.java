package com.gather.jsfcommons.util;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    public Object[][] EsMenorProvider() {
        return new Object[][]{
                new Object[]{"1", "2"},
                new Object[]{"aaaaaaa", "bb"},
                new Object[]{"01/01/2020", "01/02/2010"}};
    }

    @DataProvider
    public Object[][] EsMayorProvider() {
        return new Object[][]{
                new Object[]{"12", "2"},
                new Object[]{"zaaaaaaa", "bb"},
                new Object[]{"05/01/2020", "1/2/2010"}};
    }

    @Test(dataProvider = "EsMayorProvider")
    public void ValidEsMayorProviderTest(Object o1,
                                         Object o2) {
        int valid = columnSorter.sort(o1,
                                      o2);
        System.out.println(valid);

        Assert.assertEquals(1,
                            valid);
    }

    @Test(dataProvider = "EsMenorProvider")
    public void ValidEsMenorProviderTest(Object o1,
                                         Object o2) {
        int valid = columnSorter.sort(o1,
                                      o2);
        System.out.println(valid);

        Assert.assertEquals(-1,
                            valid);
    }
}
