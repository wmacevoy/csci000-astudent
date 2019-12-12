package com.github.wmacevoy.hello;

import java.io.PrintStream;
import java.util.Scanner;

public class App {
    String[] args = {};
    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;
    PrintStream err = System.err;
    String guest;
    String message;

    public static void main(String[] args) {
        App app = new App();
        app.args = args;
        app.run();
    }


    void run() {
        read();
        solve();
        write();
    }

    void read() {
        guest = in.nextLine();
    }


    void solve() {
        message = String.format("Hello, %s!",guest);
    }

    void write() {
        out.println(message);
    }

}
