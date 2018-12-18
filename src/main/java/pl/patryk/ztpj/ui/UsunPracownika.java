package pl.patryk.ztpj.ui;

import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Dyrektor;
import pl.patryk.ztpj.model.Handlowiec;
import pl.patryk.ztpj.model.Pracownik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UsunPracownika implements UI {

    private PracownikDao pracownikDao = new PracownikDao();
    private List<Pracownik> lista = pracownikDao.getAll();

    public void print() {
        System.out.println("3. Usuń pracownika");
        System.out.print("Podaj identyfikator PESEL : ");
        if (lista.isEmpty()) {
            System.out.println("\nBrak rekordów w bazie");
            Menu.print();
        } else {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                Long longInput = Long.parseLong(input);
                for (Pracownik pracownik : lista) {
                    if (pracownik.getPesel().equals(longInput)) {
                        System.out.println("3. Usuń pracownika");
                        System.out.print("Podaj identyfikator PESEL : " + longInput);
                        System.out.println("\n------------------------");
                        if (pracownik instanceof Dyrektor) {
                            Dyrektor dyrektor = (Dyrektor) pracownik;
                            System.out.println("Imię : " + dyrektor.getImie());
                            System.out.println("Nazwisko : " + dyrektor.getNazwisko());
                            System.out.println("Stanowisko : Dyrektor");
                            System.out.println("Wynagrodzenie (zł) : " + dyrektor.getWynagrodzenie());
                            System.out.println("Telefon służbowy numer : " + dyrektor.getTelefon());
                            System.out.println("Dodatek służbowy (zł) : " + dyrektor.getDodatek_sluzbowy());
                            System.out.println("Karta służbowa numer : " + dyrektor.getKarta_sluzbowa());
                            System.out.println("Limit kosztów/miesiąc (zł) : " + dyrektor.getLimit_kosztow());
                        } else if (pracownik instanceof Handlowiec) {
                            Handlowiec handlowiec = (Handlowiec) pracownik;
                            System.out.println("Imię : " + handlowiec.getImie());
                            System.out.println("Nazwisko : " + handlowiec.getNazwisko());
                            System.out.println("Stanowisko : Handlowiec");
                            System.out.println("Wynagrodzenie (zł) : " + handlowiec.getWynagrodzenie());
                            System.out.println("Telefon służbowy numer : " + handlowiec.getTelefon());
                            System.out.println("Prowizja (%) : " + handlowiec.getProwizja());
                            System.out.println("Limit prowizji/miesiąc (zł) : " + handlowiec.getLimit_prowizji());
                        } else {
                            throw new IllegalArgumentException();
                        }
                        System.out.println("[Enter] – potwierdź");
                        System.out.println("[Q] – porzuć");
                        try {
                            int x = 0;
                            while (x == 0) {
                                x = 0;
                                BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                                String input2 = br2.readLine();
                                switch (input2.toLowerCase()) {
                                    case "q":
                                        Menu.print();
                                        x+=1;
                                        break;
                                    case "":
                                        //pracownikDao.delete(pracownik);
                                        x+=1;
                                        break;
                                    default:
                                        System.out.println("Wybierz Enter lub Q");
                                        print();
                                        break;
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Błąd odczytu");
                            Menu.print();
                        }
                        break;
                    }

                }
            } catch (IOException e) {
                System.out.println("Błąd odczytu");
                Menu.print();
            }
            Menu.print();
        }
    }

}
