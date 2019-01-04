package pl.patryk.ztpj;

import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;

import javax.sound.sampled.Port;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import static pl.patryk.ztpj.Server.PORT;

public class ThreadHandler implements Runnable {
    private InetAddress inet;
    private int port;
    private Socket socket;


    public ThreadHandler(Socket s, InetAddress i, int p) {
         inet = i;
         port = p;
         socket = s;
    }

    @Override
    public void run() {
        try {
            PracownikDao pracownikDao = new PracownikDao();
            List<Pracownik> lista_odczyt = pracownikDao.readFile("target/files/Pracownicy.bin");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(lista_odczyt);
            oos.flush();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
