package pl.patryk.ztpj.ui;

import pl.patryk.ztpj.Client;
import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;

import java.io.IOException;
import java.util.*;

public class PobierzDane {

    public void print() throws IOException, ClassNotFoundException {
        //Adres: localhost, Port: 1234
        System.out.println("5. Pobierz dane z sieci");
        System.out.print("Adres : ");
        String stringInput = "";
        try {
            Scanner input = new Scanner(System.in);
            stringInput = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        if(stringInput.equals("")) stringInput = "localhost";
        System.out.print("Port : ");
        int intInput = 0;
        try {
            Scanner input2 = new Scanner(System.in);
            intInput = input2.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        if(intInput == 0) intInput = 1234;

        Client klient = new Client(stringInput, intInput);

        List<Pracownik> lista_pobrana = klient.runClient();

        System.out.println("------------------------");

        PracownikDao pracownikDao = new PracownikDao();
        List<Pracownik> lista = pracownikDao.getAll();

        System.out.print("Czy zapisać pobrane dane? [T]/[N] ");

        String stringInput2 = "";
        try {
            Scanner input3 = new Scanner(System.in);
            stringInput2 = input3.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }

        switch (stringInput2.toLowerCase()) {
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

        String stringInput3 = "";
        try {
            Scanner input4 = new Scanner(System.in);
            stringInput3 = input4.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }

        switch (stringInput3.toLowerCase()) {
            case "":
                Menu.print();
                break;
            default:
                Menu.print();
                break;
        }



    }
}
