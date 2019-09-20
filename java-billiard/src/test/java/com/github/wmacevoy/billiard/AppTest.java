/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wmacevoy.billiard;

import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author wmacevoy
 */
public class AppTest {

    App ex1() {
        App app = new App();
        app.a = 100;
        app.b = 100;
        app.s = 1;
        app.m = 1;
        app.n = 1;
        app.velocity = 141.42;
        app.A = 45.00;
        return app;
    }

    App ex2() {
        App app = new App();
        app.a = 200;
        app.b = 100;
        app.s = 5;
        app.m = 3;
        app.n = 4;
        app.A = 33.69;
        app.velocity = 144.22;
        return app;
    }

    App ex3() {
        App app = new App();
        app.a = 201;
        app.b = 132;
        app.s = 48;
        app.m = 1900;
        app.n = 156;
        app.A = 3.09;
        app.velocity = 7967.81;
        return app;
    }
    
    App ex4() {
        App app = new App();
        app.a = 10_000;
        app.b = 10_000;
        app.s = 10_000;
        app.m = 10_000;
        app.n = 10_000;
        app.A = 45.00;
        app.velocity = Math.sqrt(2.0)*10_000;
        return app;
        
    }

    App ex(int k) {
        switch (k) {
            case 1:
                return ex1();
            case 2:
                return ex2();
            case 3:
                return ex3();
            case 4:
                return ex4();
        }
        throw new IndexOutOfBoundsException("k=" + k);
    }

    void assertAppInEquals(App expect, App result) {
        assertEquals(expect.a, result.a);
        assertEquals(expect.b, result.b);
        assertEquals(expect.s, result.s);
        assertEquals(expect.n, result.n);
        assertEquals(expect.m, result.m);
    }

    void assertAppOutEquals(App expect, App result, double tolerance) {
        assertEquals(expect.A, result.A, tolerance);
        assertEquals(expect.velocity, result.velocity, tolerance);
    }

    public void testSample(int testcase) {
        App expected = ex(testcase);
        App result = ex(testcase);
        result.A = 0;
        result.velocity = 0;
        result.solve();
        assertAppOutEquals(expected, result, 0.01);
    }

    @Test
    public void testSample1() {
        testSample(1);
    }

    @Test
    public void testSample2() {
        testSample(2);
    }

    @Test
    public void testSample3() {
        testSample(3);
    }

    @Test
    public void testSample4() {
        testSample(4);
    }
    @Test
    public void testRead3() {
        App expect = ex3();
        App result = new App();
        result.in = new Scanner("201 132 48 1900 156");
        result.read();
        assertAppInEquals(expect, result);
    }

    @Test
    public void testWrite3() {
        String expect = "3.09 7967.81" + System.lineSeparator();
        App app = new App();
        var bos = new ByteArrayOutputStream();
        app.out = new PrintStream(bos);
        app.A = 3.09324234;
        app.velocity = 7967.805001;
        app.write();
        app.out.close();
        String result = bos.toString();
        assertEquals(expect, result);
    }

    /**
     * Test of finished method, of class App.
     */
    @Test
    public void testNotFinished() {
        App app = ex1();
        app.a = 33;
        assertFalse(app.finished());
    }

    @Test
    public void testFinished() {
        App app = ex1();
        app.a = 0;
        app.b = 0;
        app.s = 0;
        app.n = 0;
        app.m = 0;
        assertTrue(app.finished());
    }

    @Test
    public void testRun() {
        App app = new App();
        
        String EOL = System.lineSeparator();
        app.in = new Scanner("100 100 1 1 1" + EOL
                + "200 100 5 3 4" + EOL
                + "201 132 48 1900 156" + EOL
                + "0 0 0 0 0" + EOL);
        var bos = new ByteArrayOutputStream();
        app.out = new PrintStream(bos);
        app.run();
        app.out.close();
        String expect = "45.00 141.42" + EOL
                + "33.69 144.22" + EOL
                + "3.09 7967.81" + EOL;
        String result = bos.toString();
        assertEquals(expect, result);
    }
}
