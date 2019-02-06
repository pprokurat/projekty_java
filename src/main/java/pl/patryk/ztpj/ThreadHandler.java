package pl.patryk.ztpj;

import pl.patryk.ztpj.dao.PracownikDao;
import pl.patryk.ztpj.model.Pracownik;
import pl.patryk.ztpj.ui.KopiaZapasowa;
import org.apache.commons.io.IOUtils;

import javax.sound.sampled.Port;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

//            StringWriter writer = new StringWriter();
//            IOUtils.copy(inputStream, writer, "UTF-8");
//            String token = writer.toString();
            String token = inputStream.readUTF();


            if (!tokenExpired(token))
            {
                PracownikDao pracownikDao = new PracownikDao();
                List<Pracownik> lista_odczyt = pracownikDao.readFile(KopiaZapasowa.PATH + "Pracownicy.bin");

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(lista_odczyt);
                oos.flush();
                socket.close();
            }
            else
            {
                List<Pracownik> lista_expired = new ArrayList<>();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(lista_expired);
                oos.flush();
                socket.close();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean tokenExpired(String token) {
        long expiryTime = Server.tokens.get(token);
        long difference = System.currentTimeMillis() - expiryTime;
        return difference > 120000;
    }
}
