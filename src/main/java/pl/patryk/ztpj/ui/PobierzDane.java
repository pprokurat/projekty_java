package pl.patryk.ztpj.ui;

import pl.patryk.ztpj.Client;
import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PobierzDane {

    public void print() throws IOException, ClassNotFoundException {
        System.out.println("5. Pobierz dane z sieci");
        System.out.print("Adres : ");
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        if(input.equals("")) input = "localhost";
        System.out.print("Port : ");
        String input2 = "";
        int intInput = 0;
        try {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            input2 = br2.readLine();
            intInput = Integer.parseInt(input2);
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        if(intInput == 0) intInput = 1234;

        Client klient = new Client(input, intInput);

        List<Pracownik> lista_pobrana = klient.runClient();

        System.out.println("------------------------");

        PracownikDao pracownikDao = new PracownikDao();
        List<Pracownik> lista = pracownikDao.getAll();

        System.out.print("Czy zapisać pobrane dane? [T]/[N] ");

        String input3 = "";
        try {
            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
            input3 = br3.readLine();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }

        switch (input3.toLowerCase()) {
            case "t":
                for (Pracownik pracownik : lista) {
                    pracownikDao.delete(pracownik);
                }

                for (Pracownik pracownik : lista_pobrana) {
                    pracownikDao.save(pracownik);
                }
                System.out.println("Zapisano dane");
                break;
            case "n":
                System.out.println("Odrzucono dane");
                break;
            default:
                System.out.println("Wybierz [T] lub [N]");
                print();
                break;
        }

        System.out.println("[ENTER] - powrót do ekranu głównego");

        String input4 = "";
        try {
            BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
            input3 = br4.readLine();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }

        switch (input4.toLowerCase()) {
            case "":
                Menu.print();
                break;
            default:
                Menu.print();
                break;
        }



    }
}
