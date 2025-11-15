import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your your date of birth: ");
        int dateOfBirth = scanner.nextInt(); 
        int currentAge = 2025 - dateOfBirth;
        System.out.println("Hello " + name + "!\nYou are " + currentAge + " years old.");
        scanner.close();
    }
}
