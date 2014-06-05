package exercise11;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JFileChooser;


public class Client {
    //private Scanner scan;
    public final static String ADDRESS = "192.168.173.1";
    public final static int PORT = 12345;

    public Client() {
        //scan = new Scanner(System.in);
    }

//    public String readLine() {
//        return scan.nextLine();
//    }

    public static void main(String[] args) {
        
        
        System.out.println("Starting...");
        System.out.println("attempting to connect to " + ADDRESS + ":" + PORT);
        Socket client = null;
        try {
            client = new Socket(ADDRESS, PORT);
            System.out.println("Client connected to (Remote): " + client.getRemoteSocketAddress());
            System.out.println("Client connected to (Local): " + client.getLocalSocketAddress());
            
            InputStream is = client.getInputStream();
            System.out.println("opened input stream");
            try (ObjectInputStream in = new ObjectInputStream(is)) {
                System.out.println("reading input stream...");
                Object obj = in.readObject();
                System.out.println("Data received: ");// + obj);
                
                if (obj instanceof DataFile) {
                    DataFile f = (DataFile) obj;
                    byte ba[] = new byte[f.getFileSize()];
                    
                    int readCount = is.read(ba);
                    if (readCount == f.getFileSize()) {
                        System.out.println("read the correct number of bytes into the array: " + readCount);
                    } else {
                        System.out.println("read the wrong number of bytes... " + readCount + ", instead of " + f.getFileSize());
                    }
                    
                    String fileName = f.getFileName();
                    
                    JFileChooser chooser = new JFileChooser();
                    chooser.setSelectedFile(new File(fileName));
//                    JFrame frame = new JFrame();
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    int returnVal = chooser.showOpenDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        fileName = chooser.getSelectedFile().getAbsolutePath();                       
                    }
                    System.out.println("You chose to open this file: " + fileName);
//                    frame.dispose();
                    FileOutputStream fos = new FileOutputStream(fileName);
                    BufferedOutputStream bos = new BufferedOutputStream( fos);
                    bos.write(ba);
                    bos.close();
                    fos.close();
                    System.out.println("wrote file :)");
                } else {
                    System.out.println("data was not a DataFile...");
                }
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("ClassNotFoundException or IOException: " + e.getMessage());
            }
            System.out.println("Closing the input stream.");
            is.close();
            System.out.println("is closed");

        } catch (BindException | ConnectException e) {
            System.out.println("BindException or ConnectException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                System.out.println("closing client");
                client.close();
                System.out.println("client closed");
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                e.printStackTrace();
            }
            
        }
        System.out.println("we should be done now.");
    }

}
