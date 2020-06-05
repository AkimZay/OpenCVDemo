package com.mycompany.app.config;

import com.mycompany.app.constant.Constant;
import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

    @Test
    public void getProp() {
        System.clearProperty("config.properties");
        System.setProperty("config.properties", "src/test/resources/config.properties");
        String prop= Config.getProp(Constant.PATH_TO_IMAGES);
        Assert.assertEquals("src/test/resources/img/",prop);
    }
}