import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class BufferedReaderApp {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("../README.md"))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
