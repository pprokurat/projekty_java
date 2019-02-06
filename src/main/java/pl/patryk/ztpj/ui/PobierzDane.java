package pl.patryk.ztpj.ui;

import pl.patryk.ztpj.AuthenticationException;
import pl.patryk.ztpj.Client;
import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;

import java.io.Console;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.*;

public class PobierzDane {

    public void print() {
        //Adres: localhost, Port: 1234
        System.out.println("5. Pobierz dane z sieci");

        Client klient = new Client();

        try {
            System.out.print("Podaj użytkownika : ");
            Scanner input = new Scanner(System.in);
            String user = input.nextLine();
            System.out.print("Podaj hasło : ");
            Scanner input2 = new Scanner(System.in);
            String pass = input2.nextLine();

            if(klient.authenticate(user,pass)==false){
                throw new AuthenticationException();
            }

            System.out.print("Adres : ");
            String stringInput = "";
            input = new Scanner(System.in);
            stringInput = input.nextLine();
            if (stringInput.equals("")) {stringInput = "localhost";}
            System.out.print("Port : ");
            int intInput = 0;
            //Scanner input2;
            input2 = new Scanner(System.in);
            intInput = input2.nextInt();
            if (intInput == 0){ intInput = 1234;}


            List<Pracownik> lista_pobrana = null;
            lista_pobrana = klient.runClient(stringInput,intInput);


            System.out.println("------------------------");

            PracownikDao pracownikDao = new PracownikDao();
            List<Pracownik> lista = pracownikDao.getAll();

            System.out.print("Czy zapisać pobrane dane? [T]/[N] ");

            String stringInput2 = "";
            Scanner input3 = new Scanner(System.in);
            stringInput2 = input3.nextLine();


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
        }catch (NotBoundException e){
            System.out.println("Błąd połączenia serwera");
        } catch (ClassNotFoundException | IOException e){
            System.out.println("Błąd odczytu");
        } catch (AuthenticationException e){
            System.out.println("Błąd autentykacji");
        }

        System.out.println("[ENTER] - powrót do ekranu głównego");

        try {
            String stringInput3 = "";
            Scanner input4 = new Scanner(System.in);
            stringInput3 = input4.nextLine();
//            switch (stringInput3.toLowerCase()) {
//                case "":
//                    Menu.print();
//                    break;
//                default:
//                    Menu.print();
//                    break;
//            }
        } catch (InputMismatchException e) {
            System.out.println("Błąd odczytu");
        } finally {
            Menu.print();
        }




    }
}
