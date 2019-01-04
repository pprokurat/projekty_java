package pl.patryk.ztpj.utils;

import pl.patryk.ztpj.Server;

import java.io.IOException;

public class NowyWatek implements Runnable {

    private String name;
    Thread t;

    public NowyWatek(String nazwa) {
        this.name = nazwa;
        t = new Thread(this, nazwa);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void run() {

        Server server = new Server();
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}