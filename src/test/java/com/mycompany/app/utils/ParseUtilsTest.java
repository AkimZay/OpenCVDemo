package com.mycompany.app.utils;

import org.junit.Assert;
import org.junit.Test;

public class ParseUtilsTest {

    @Test
    public void getIntegerValue() {
        int intValue = ParseUtils.getIntegerValue("1");
        Assert.assertEquals(1, intValue);
    }

    @Test
    public void getIntegerValueNotInt() {
        int intValue = ParseUtils.getIntegerValue("x");
        Assert.assertEquals(0, intValue);
    }
}
