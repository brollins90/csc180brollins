package exercise10;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class Client
{
    private Scanner scan;
    public final static String ADDRESS = "192.168.173.1";
    public final static int PORT = 12345;
    
    public Client() {
        scan = new Scanner(System.in);
    }
    
    public String readLine() {
        return scan.nextLine();
    }
    
    public static void main(String[] args)
    {
        try(Socket client = new Socket(ADDRESS, PORT))
        {
            System.out.println("Client connected to (Remote): " + client.getRemoteSocketAddress());
            System.out.println("Client connected to (Local): " + client.getLocalSocketAddress());
            
//            try(ObjectInputStream in = new ObjectInputStream(client.getInputStream()))
//            {
//                Object obj = in.readObject();
//                System.out.println("Data received: " + obj);
//            }
//            catch(ClassNotFoundException | IOException e)
//            {
//                System.out.println(e.getMessage());
//            }
            
            try(ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream()))
             {
             out.writeObject("Hello!");
             System.out.println("Message sent successfully!");
             }
             catch(IOException e)
             {
             System.out.println(e.getMessage());
             }
        }
        catch(BindException | ConnectException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
