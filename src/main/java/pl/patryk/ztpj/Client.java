package pl.patryk.ztpj;

import jdk.nashorn.internal.parser.Token;
import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;
import pl.patryk.ztpj.rmi.Validator;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Client {
//    private static String host;
//    private static int port;
    private static Validator look_up;
    private String token;

//    public Client(){
//        host = "localhost";
//        port = 1234;
//    }

//    public Client(String address, int port){
//        host = address;
//        this.port = port;
//    }

    public boolean authenticate(String login, String pass) throws RemoteException, NotBoundException, MalformedURLException {


        look_up = (Validator) Naming.lookup("localhost");
        //String txt1 = JOptionPane.showInputDialog("Podaj login:");

        token = look_up.validate(login,pass);
        //JOptionPane.showMessageDialog(null, response);

        return !token.isEmpty();

    }

    public List<Pracownik> runClient(String host, int port) throws IOException, ClassNotFoundException, NotBoundException {

        Socket socket1 = new Socket(host,Server.PORT,InetAddress.getByName(host),port);

        DataOutputStream dataOutputStream = new DataOutputStream(socket1.getOutputStream());
        dataOutputStream.writeUTF(token);

        ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
        List<Pracownik> lista_pobrana = (List<Pracownik>)ois.readObject();

        socket1.close();

        return lista_pobrana;
    }

}
