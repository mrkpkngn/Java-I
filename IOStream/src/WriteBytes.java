
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytes {
    public static void main(String[] args){
        try(FileOutputStream fos = new FileOutputStream("output.txt"))
        {
            String data = "Hello, World!";
            fos.write(data.getBytes());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }   
    }

}
