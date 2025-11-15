import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterApp {
    public static void main(String[] args) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("BufferedOutput.txt"))){
            bw.write("Hello, Pookies!");
            bw.newLine();
            bw.write("Pookies, I love you!");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
