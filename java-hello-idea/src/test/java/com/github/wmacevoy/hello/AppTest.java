package com.github.wmacevoy.hello;


import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @BeforeAll
    static void beforeAll() {
    }
    
    @AfterAll
    static void afterAll() {
    }
    @BeforeEach
    void beforeEach() {
    }

    @AfterEach
    void afterEach() {
    }

    @Test
    void main() throws Exception {
        var saveIn = System.in;
        var saveOut = System.out;
        var saveErr = System.err;
        var app = new App();
        byte[] input = String.format("Test Main%n").getBytes(UTF_8);
        var output = new ByteArrayOutputStream();
        var error = new ByteArrayOutputStream();
        var args = new String[] {};
        try (var in = new ByteArrayInputStream(input);
             var out = new PrintStream(output,false,UTF_8);
             var err = new PrintStream(error,false,UTF_8)) {
            System.setIn(in);
            System.setOut(out);
            System.setErr(err);
            App.main(args);
        } finally {
            System.setIn(saveIn);
            System.setOut(saveOut);
            System.setErr(saveErr);
        }
        assertEquals(String.format("Hello, Test Main!%n"), output.toString(UTF_8));
    }

    @Test
    public void run() {
        var app = new App();
        app.in = new Scanner(String.format("Test Run%n"));
        var output = new ByteArrayOutputStream();
        var out = new PrintStream(output,false,UTF_8);
        app.out = out;
        app.run();
        out.close();
        assertEquals(String.format("Hello, Test Run!%n"), output.toString(UTF_8));
    }

    @Test
    public void read() {
        var app = new App();
        app.in = new Scanner(String.format("Test Read%n"));
        app.read();
        assertEquals("Test Read",app.guest);
    }

    @Test
    public void solve() {
        var app = new App();
        app.guest = "Test Solve";
        app.solve();
        assertEquals("Hello, Test Solve!",app.message);

    }

    @Test
    void write() {
        var app = new App();
        var output = new ByteArrayOutputStream();
        try (var out = new PrintStream(output)) {
            app.out = out;
            app.message = "Hello, Test Write!";
            app.write();
        }
        assertEquals(String.format("Hello, Test Write!%n"), output.toString(UTF_8));
    }

    @Test
    void unicode() throws Exception {
        byte[] expect;
        // println("Hello, 😀!") as UTF-8
        if (System.lineSeparator().equals("\n")) {
            expect = new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x2C, 0x20, (byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80, 0x21, 0x0A};
        } else {
            expect = new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x2C, 0x20, (byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80, 0x21, 0x0D, 0x0A};
        }
        var saveIn = System.in;
        var saveOut = System.out;
        var saveErr = System.err;
        var app = new App();
        byte [] input;
        if (System.lineSeparator().equals("\n")) {
            input = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80, 0x0A};
        } else {
            input = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x80, 0x0D, 0x0A};
        }
        var output = new ByteArrayOutputStream();
        var error = new ByteArrayOutputStream();
        var args = new String[] {};
        try (var in = new ByteArrayInputStream(input);
             var out = new PrintStream(output,false,UTF_8);
             var err = new PrintStream(error,false,UTF_8)) {
            System.setIn(in);
            System.setOut(out);
            System.setErr(err);
            App.main(args);
        } finally {
            System.setIn(saveIn);
            System.setOut(saveOut);
            System.setErr(saveErr);
        }
        var result=output.toByteArray();
        assertArrayEquals(expect,result);

    }

    final int N = 1_000;
    @Test
    void unicodes() throws Exception {
        for (int i=0; i<N; ++i) {
            unicode();
        }
    }

    @Test
    public void mains() throws Exception {
        for (int i=0; i<N; ++i) {
            main();
        }
    }
}
