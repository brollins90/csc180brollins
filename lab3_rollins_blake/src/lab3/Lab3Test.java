package lab3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lab3Test {
    public static void main(String[] args) {
        HashTree tree1 = new HashTree();

        System.out.println("Adding Tests:");
        System.out.println("Adding 'A': " + (tree1.add("A") == true));
        System.out.println("Adding 'Tom': " + (tree1.add("Tom") == true));
        System.out.println("Adding '007': " + (tree1.add("007") == true));
        System.out.println("Adding 'null': " + (tree1.add(null) == true));
        System.out.println("Adding 'null': " + (tree1.add(null) == false));

        System.out.println("");
        System.out.println("Hashing Tests:");
        // Verify correct hash codes.
        System.out.println("A = 10: " + (tree1.hash("A") == 10));
        System.out.println("Tom = 230: " + (tree1.hash("Tom") == 230));
        System.out.println("007 = 3: " + (tree1.hash("007") == 3));
        System.out.println("null = 0: " + (tree1.hash(null) == 0));

        System.out.println("");
        System.out.println("Display Test:");
        // Expected output:
        // 007
        // A
        // Tom
        tree1.displayTree(tree1.getRootNode());

        HashTree t2 = new HashTree();
        new Lab3Test().addContactsToHashTree(t2);
        t2.displayTree(t2.getRootNode());

    }

    private void addContactsToHashTree(HashTree t) {
        BufferedReader br = null;
        try {
            String path = "contacts.csv";
            br = new BufferedReader(new FileReader(path));

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                t.add(line);
            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to open the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
