import person.*;

public class App {
    public static void main(String[] args) throws Exception {
        Person person = new Person("John Doe", 30, "123 Main St", "Anytown");
        person.displayPersonInfo();
    }
}
