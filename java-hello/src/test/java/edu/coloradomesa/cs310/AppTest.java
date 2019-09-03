/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.coloradomesa.cs310;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author wmacevoy
 */
public class AppTest {

    public AppTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getWho method, of class App.
     */
    @Test
    public void testGetDefaultWho() {
        System.out.println("getWho");
        App instance = new App();
        String expResult = "World";
        String result = instance.getWho();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNonDefaultWho() {
        System.out.println("getWho");
        App instance = new App();
        String value = "not " + instance.getWho();
        instance.setWho(value);
        String expResult = value;
        String result = instance.getWho();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWho method, of class App.
     */
    @Test
    public void testSetWho() {
        System.out.println("setWho");
        String who = "王秀英";
        App app = new App();
        app.setWho(who);
        String expResult = who;
        String result = app.getWho();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGreeting method, of class App.
     */
    @Test
    public void testGetGreeting() {
        System.out.println("getGreeting");
        App instance = new App();
        String expResult = "Hello";
        String result = instance.getGreeting();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGreeting method, of class App.
     */
    @Test
    public void testSetGreeting() {
        System.out.println("setGreeting");
        String greeting = "مرحبا";
        App instance = new App();
        instance.setGreeting(greeting);
        String result = instance.getGreeting();
        String expResult = greeting;
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class App.
     *
     * Since this has side effects; it is hard to test.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");

        String expResult = "Hello World!" + System.lineSeparator();
        PrintStream sysOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(buffer);
        try {
            System.setOut(testOut);
            String[] args = new String[]{};
            App.main(args);
        } finally {
            System.setOut(sysOut);
            testOut.close();
        }

        String result = buffer.toString(StandardCharsets.UTF_8);

        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class App.
     *
     * Since this has side effects; it is hard to test.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        String who = "王秀英";
        String greeting = "مرحبا";
        String message = greeting + " " + who + "!";

        App instance = new App();
        instance.setWho(who);
        instance.setGreeting(greeting);

        String expResult = message + System.lineSeparator();
        PrintStream sysOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(buffer);
        try {
            System.setOut(testOut);
            instance.run();
        } finally {
            System.setOut(sysOut);
            testOut.close();
        }

        String result = buffer.toString(StandardCharsets.UTF_8);

        assertEquals(expResult, result);
    }

    @Test
    public void testDefaultMessage() {
        String expResult = "Hello World!";
        App instance = new App();
        String result = instance.getMessage();

        assertEquals(expResult, result);
    }

    @Test
    public void testCustomMessage() {
        String who = "王秀英";
        String greeting = "مرحبا";
        String message = greeting + " " + who + "!";

        App instance = new App();
        instance.setWho(who);
        instance.setGreeting(greeting);
        String expResult = message;
        String result = instance.getMessage();

        assertEquals(expResult, result);
    }

}
