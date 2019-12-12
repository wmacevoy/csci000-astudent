/*
 * tested solution to:
 *  https://open.kattis.com/problems/billiard
 
 */
package com.github.wmacevoy.billiard;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author wmacevoy
 */
public class App {
    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;
    int a,b,s,n,m;
    double A,velocity;
    
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    void run() {
        while (true) {
            read();
            if (finished()) break;
            solve();
            write();
        }
    }

    void read() {
        a = in.nextInt();
        b = in.nextInt();
        s = in.nextInt();
        m = in.nextInt();
        n = in.nextInt();
    }
    
    void write() {
        out.printf("%1.2f %1.2f%n", A, velocity);
    }

    boolean finished() {
        return (a == 0 && b == 0 && s == 0 && m == 0 && n == 0);
    }

    void solve() {
        A = Math.toDegrees(Math.atan2(n*b,m*a));
        velocity = Math.sqrt(Math.pow(n*b,2)+Math.pow(m*a,2))/((double)s);
    }
}
