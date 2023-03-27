package org.example;

import org.example.model.BrokerThread;

public class Main2 {

    public static void main(String[] args) {
        BrokerThread brokerThread = new BrokerThread();

        System.out.println(args.length);
        if (args.length != 0) {
            if (args[0].equals("-p")) {
                System.out.println(brokerThread.getAllTimesForBD());
            }
        } else {
            brokerThread.start();
        }
    }
}
