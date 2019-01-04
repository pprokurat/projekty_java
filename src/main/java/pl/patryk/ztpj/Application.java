package pl.patryk.ztpj;

import pl.patryk.ztpj.ui.Menu;
import pl.patryk.ztpj.utils.NowyWatek;

import java.io.IOException;


public class Application {
    public static void main(String[] args) {

        NowyWatek thread1 = new NowyWatek("thread1");
        Client klient = new Client();
        try {
            klient.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Menu.print();
    }
}
