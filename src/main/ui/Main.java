package ui;

import model.logging.EventLog;

public class Main {
    public static void main(String[] args) {
        new SymmerApp();
        System.out.println("complete");
        System.out.println(EventLog.getInstance());

    }
}
