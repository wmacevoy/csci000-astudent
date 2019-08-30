/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.coloradomesa.cs310;

/**
 *
 * @author wmacevoy
 */
public class App {

    private String who = "World";

    /**
     * Get the value of who
     *
     * @return the value of who
     */
    public String getWho() {
        return who;
    }

    /**
     * Set the value of who
     *
     * @param who new value of who
     */
    public void setWho(String who) {
        this.who = who;
    }

    private String greeting = "Hello";

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void run() {
        System.out.println(getGreeting() + " " + getWho() + "!");
    }

}
