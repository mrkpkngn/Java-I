import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import person.*;

public class App {
    public static void main(String[] args) throws Exception {
        Person person = new Person("Mark", 20);
        try (FileOutputStream fos = new FileOutputStream("person.ser"))
        {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
            System.out.println("Person object serialized: " + person);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
