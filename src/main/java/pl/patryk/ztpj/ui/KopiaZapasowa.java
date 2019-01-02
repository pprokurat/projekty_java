package pl.patryk.ztpj.ui;

import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.fileCompression.Compress;
import pl.patryk.ztpj.fileCompression.Decompress;
import pl.patryk.ztpj.model.Dyrektor;
import pl.patryk.ztpj.model.Handlowiec;
import pl.patryk.ztpj.model.Pracownik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class KopiaZapasowa {

    private PracownikDao pracownikDao = new PracownikDao();
    private List<Pracownik> lista = pracownikDao.getAll();

    public void print() throws IOException, ClassNotFoundException {
        System.out.println("4. Kopia zapasowa");
        System.out.print("[Z]achowaj/[O]dtwórz : ");
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        System.out.println("------------------------");
        System.out.print("Kompresja [G]zip/[Z]ip : ");
        String input2 = "";
        try {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            input2 = br2.readLine();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }
        System.out.print("Nazwa pliku : ");
        String input3 = "";
        try {
            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
            input3 = br3.readLine();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            Menu.print();
        }

        switch (input.toLowerCase()) {
            case "z":
                System.out.println("------------------------");
                System.out.println("[Enter] – potwierdź");
                System.out.println("[Q] – porzuć");
                try {
                    int x = 0;
                    while (x == 0) {
                        x = 0;
                        BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
                        String input4 = br4.readLine();
                        switch (input4.toLowerCase()) {
                            case "q":
                                Menu.print();
                                x += 1;
                                break;
                            case "":
                                pracownikDao.writeToFile(lista,"target/files/Pracownicy.bin");
                                if (input2.toLowerCase().equals("g")) {
                                    Compress.compress_gzip("target/files/Pracownicy.bin", "target/files/" + input3 + ".gzip");
                                } else if (input2.toLowerCase().equals("z")) {
                                    Compress.compress_zip("target/files/Pracownicy.bin", "target/files/" + input3 + ".zip");
                                    System.out.println("hello");
                                } else {
                                    System.out.println("Wybierz kompresję G lub Z");
                                }
                                System.out.println("Zapisano dane do pliku\n");
                                x += 1;
                                break;
                            default:
                                System.out.println("Wybierz Enter lub Q");
                                print();
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "o":
                System.out.println("------------------------");
                System.out.println("[Enter] – potwierdź");
                System.out.println("[Q] – porzuć");
                try {
                    int x = 0;
                    while (x == 0) {
                        x = 0;
                        BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
                        String input4 = br4.readLine();
                        switch (input4.toLowerCase()) {
                            case "q":
                                Menu.print();
                                x += 1;
                                break;
                            case "":
                                if (input2.toLowerCase().equals("g")) {
                                    Decompress.decompress_gzip("target/files/" + input3 + ".gzip", "target/files/Pracownicy.bin");
                                } else if (input2.toLowerCase().equals("z")) {
                                    Decompress.decompress_zip("target/files/" + input3 + ".zip", "target/files");
                                } else {
                                    System.out.println("Wybierz dekompresję G lub Z");
                                }

                                for (Pracownik pracownik : lista) {
                                    pracownikDao.delete(pracownik);
                                }

                                List<Pracownik> lista_odczyt = pracownikDao.readFile("target/files/Pracownicy.bin");

                                for (Pracownik pracownik : lista_odczyt) {
                                    pracownikDao.save(pracownik);
                                }
                                System.out.println("Wczytano dane");
                                x += 1;
                                break;
                            default:
                                System.out.println("Wybierz Enter lub Q");
                                print();
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Menu.print();
                break;
        }
        Menu.print();
    }

}
