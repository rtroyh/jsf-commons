package com.gather.jsfcommons.util;

import com.gather.jsfcommons.sort.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<ISort> iSortList = new ArrayList<ISort>();
        iSortList.add(new NullSort());
        iSortList.add(new RUTSort());
        iSortList.add(new DateSort());
        iSortList.add(new NumberSort());
        iSortList.add(new StringSort());

        columnSorter = new ColumnSorter(iSortList);
    }

    @DataProvider
    public Object[][] EsIgualProvider() {
        return new Object[][]{
                {null, null},
                {"1", "1"},
                {"a", "a"},
                {"", ""},
                {"01/01/2010", "01/01/2010"}};
    }

    @DataProvider
    public Object[][] EsMenorProvider() {
        return new Object[][]{
                new Object[]{null, "2"},
                new Object[]{"1", "2"},
                new Object[]{"aaaaaaa", "bb"},
                new Object[]{"", "bb"},
                new Object[]{"01/01/2010", "01/02/2010"}};
    }

    @DataProvider
    public Object[][] EsMayorProvider() {
        return new Object[][]{
                new Object[]{"2", null},
                new Object[]{"12", "2"},
                new Object[]{"zaaaaaaa", ""},
                new Object[]{"zaaaaaaa", "bb"},
                new Object[]{"05/01/2020", "1/2/2010"}};
    }

    @Test(dataProvider = "EsMayorProvider")
    public void ValidEsMayorProviderTest(Object o1,
                                         Object o2) {
        int valid = columnSorter.sort(o1,
                                      o2);

        Assert.assertEquals(1,
                            valid);
    }

    @Test(dataProvider = "EsMenorProvider")
    public void ValidEsMenorProviderTest(Object o1,
                                         Object o2) {
        int valid = columnSorter.sort(o1,
                                      o2);

        Assert.assertEquals(-1,
                            valid);
    }

    @Test(dataProvider = "EsIgualProvider")
    public void ValidEsIgualProviderTest(Object o1,
                                         Object o2) {
        int valid = columnSorter.sort(o1,
                                      o2);

        Assert.assertEquals(0,
                            valid);
    }
}
