package exercise12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Exercise12 {

    public static void main(String[] args) {

        // TASK 1
        System.out.println("TASK 1");
        Animal animal1 = new Animal();
        animal1.setDateOfBirth(new Date("04/19/1990"));
        animal1.setDomesticated(true);
        animal1.setName("Blake");
        animal1.setSpecies("Human");

        try {
            JAXBContext context = JAXBContext.newInstance(Animal.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(animal1, System.out);
            m.marshal(animal1, new File("animalxml.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();

        }


        // TASK 2
        System.out.println("TASK 2");
        try {
            JAXBContext context = JAXBContext.newInstance(Storage.class);
            Marshaller m = context.createMarshaller();
            Unmarshaller um = context.createUnmarshaller();
            Storage foodCollection = (Storage) um.unmarshal(new FileReader("inventory.xml"));
            for (Food f : foodCollection.getFoodList()) {
                System.out.println(f);
            }
        } catch (JAXBException e) {
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
