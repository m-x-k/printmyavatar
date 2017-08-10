package org.printmyavatar.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class HomeControllerTest {

    @Test
    public void testHelloWorld() throws Exception {
        HomeController homeController = new HomeController();
        assertEquals("index", homeController.home());
    }
}