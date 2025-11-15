import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytes {
    public static void main(String[] args) throws Exception {
        try(FileInputStream fis = new FileInputStream("../README.md")){
            int data;
            while((data = fis.read()) != -1){
                System.out.print((char) data);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
