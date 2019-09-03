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
        this.message = null;
    }

    private String greeting = "Hello";

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
        this.message = null;
    }

    private String message = null;
    
    public String getMessage() {
        if (message == null) {
            message = getGreeting() + " " + getWho() + "!";
        }
	return message;
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() {
        System.out.println(getMessage());
    }
}
