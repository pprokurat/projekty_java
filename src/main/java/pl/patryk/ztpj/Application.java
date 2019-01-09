package pl.patryk.ztpj;

import pl.patryk.ztpj.ui.Menu;
import pl.patryk.ztpj.utils.NowyWatek;

import java.io.IOException;


public class Application {
    public static void main(String[] args) {

        NowyWatek thread1 = new NowyWatek("thread1");

        Menu.print();
    }
}
