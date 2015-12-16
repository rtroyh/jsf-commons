package com.gather.jsfcommons.util;

import com.gather.jsfcommons.sort.RUTSort;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsf-commons
 * User: rodrigotroy
 * Date: 16-12-15
 * Time: 13:34
 */
public class RUTSortTest {
    private RUTSort rutSort;

    @BeforeClass
    public void initData() {
        rutSort = new RUTSort();
    }

    @DataProvider
    public Object[][] EsIgualProvider() {
        return new Object[][]{
                new Object[]{null, null},
                new Object[]{"13665383-0", "13665383-0"},
                new Object[]{"15330294-4", "15330294-4"}};
    }

    @DataProvider
    public Object[][] EsMenorProvider() {
        return new Object[][]{
                new Object[]{null, "13665383-0"},
                new Object[]{"13665383-0", "15330294-4"}};
    }

    @DataProvider
    public Object[][] EsMayorProvider() {
        return new Object[][]{
                new Object[]{"15330294-0", "13665383-K"},
                new Object[]{"15330294-0", null}};
    }

    @Test(dataProvider = "EsMayorProvider")
    public void ValidEsMayorProviderTest(Object o1,
                                         Object o2) {
        int valid = rutSort.sort(o1,
                                 o2);

        Assert.assertEquals(1,
                            valid);
    }

    @Test(dataProvider = "EsMenorProvider")
    public void ValidEsMenorProviderTest(Object o1,
                                         Object o2) {
        int valid = rutSort.sort(o1,
                                 o2);

        Assert.assertEquals(-1,
                            valid);
    }

    @Test(dataProvider = "EsIgualProvider")
    public void ValidEsIgualProviderest(Object o1,
                                        Object o2) {
        int valid = rutSort.sort(o1,
                                 o2);

        Assert.assertEquals(0,
                            valid);
    }
}
