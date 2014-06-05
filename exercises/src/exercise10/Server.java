package exercise10;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    // 185

    public final static int PORT = 12345;

    public static void main(String[] args) {
        
        try (ServerSocket socket = new ServerSocket(PORT)) {
            // wait 15 seconds for a connection
            // socket.setSoTimeout(15000);
            System.out.println("Listening...");

            try (Socket server = socket.accept()) {
                System.out.println("Client connected from (Remote): " + server.getRemoteSocketAddress());
                System.out.println("Client connected from (Local): " + server.getLocalSocketAddress());


                try (ObjectInputStream in = new ObjectInputStream(server.getInputStream())) {
                    while (true) {

                        Object obj = in.readObject();
                        System.out.println("Data received: " + obj);
                    }
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println(e.getMessage());
                }
            } catch (SocketTimeoutException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (BindException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
