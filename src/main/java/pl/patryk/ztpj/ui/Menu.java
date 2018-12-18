package pl.patryk.ztpj.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu implements UI{

    public static void print() {
        System.out.println("MENU");
        System.out.println("1. Lista pracowników");
        System.out.println("2. Dodaj pracownika");
        System.out.println("3. Usuń pracownika");
        System.out.println("4. Kopia zapasowa");
        System.out.print("Wybór>");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            int intInput = Integer.parseInt(input);
            switch (intInput) {
                case 1:
                    ListaPracownikow lista = new ListaPracownikow();
                    lista.print();
                    break;
                case 2:
                    DodajPracownika dodaj = new DodajPracownika();
                    dodaj.print();
                    break;
                case 3:
                    UsunPracownika usun = new UsunPracownika();
                    usun.print();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Podaj liczbę z zakresu 1-4");
                    Menu.print();
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Podaj liczbę");
            Menu.print();
        }

    }
}
