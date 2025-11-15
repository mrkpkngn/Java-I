import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class ReadFromURL {
    public static void main(String[] args) {
        try {
            URI uri = new URI("http://vondrak.vsb.cz/index.html");
            URL url = uri.toURL();
            try(InputStream in = url.openStream()){
                int data;
                while((data = in.read()) != -1){
                    System.out.print((char) data);
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
