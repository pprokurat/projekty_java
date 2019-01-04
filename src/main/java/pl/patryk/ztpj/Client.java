package pl.patryk.ztpj;

import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;

import java.io.*;
import java.net.*;
import java.util.List;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public void runClient() throws IOException, ClassNotFoundException {
        Socket socket1 = new Socket(HOST,Server.PORT,InetAddress.getByName(HOST),PORT);

        DataOutputStream dataOutputStream = new DataOutputStream(socket1.getOutputStream());
        dataOutputStream.writeUTF("GET");

        ObjectInputStream oos = new ObjectInputStream(socket1.getInputStream());
        List<Pracownik> lista_pobrana = (List<Pracownik>)oos.readObject();
        PracownikDao pracownikDao = new PracownikDao();
        List<Pracownik> lista = pracownikDao.getAll();

        for (Pracownik pracownik : lista) {
            pracownikDao.delete(pracownik);
        }

        for (Pracownik pracownik : lista_pobrana) {
            pracownikDao.save(pracownik);
        }
        socket1.close();
    }

}
