package pl.patryk.ztpj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8191;
    public void runServer() throws IOException {
        int i = 1;
        ServerSocket s = new ServerSocket(PORT);
        while (true) {
            Socket incoming = s.accept();
            //System.out.println("Spawning"+i);
            System.out.println("Łączenie...");
            Runnable r = new ThreadHandler(incoming, incoming.getInetAddress(),incoming.getPort());
            Thread t = new Thread(r);
            t.start();
            i++;
        }
    }
}
