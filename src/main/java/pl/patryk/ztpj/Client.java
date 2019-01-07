package pl.patryk.ztpj;

import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;

import java.io.*;
import java.net.*;
import java.util.List;

public class Client {
    private static String host;
    private static int port;

    public Client(){
        host = "localhost";
        port = 1234;
    }

    public Client(String address, int port){
        host = address;
        this.port = port;
    }

    public List<Pracownik> runClient() throws IOException, ClassNotFoundException {
        Socket socket1 = new Socket(host,Server.PORT,InetAddress.getByName(host),port);

        DataOutputStream dataOutputStream = new DataOutputStream(socket1.getOutputStream());
        dataOutputStream.writeUTF("GET");

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
        List<Pracownik> lista_pobrana = (List<Pracownik>)ois.readObject();

        socket1.close();

        return lista_pobrana;
    }

}
