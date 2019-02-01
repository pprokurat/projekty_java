package pl.patryk.ztpj.utils;

import pl.patryk.ztpj.Server;
import pl.patryk.ztpj.rmi.ValidatorImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

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

        try {
            Naming.rebind("localhost",new ValidatorImpl());
            //System.out.println("Login server open for business");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException me) {
            System.out.println("MalformedURLException " + me);
        }

        Server server = new Server();
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}