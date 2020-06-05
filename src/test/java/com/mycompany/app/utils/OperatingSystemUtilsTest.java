package com.mycompany.app.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OperatingSystemUtilsTest {

    @Before
    public void setUp() {
        System.setProperty("config.properties", "src/test/resources/config.properties");
    }

    @After
    public void tearDown() {
        System.clearProperty("config.properties");
    }

    @Test
    public void loadConfig() throws Exception {
        OperatingSystemUtils.loadConfig();
    }
}
