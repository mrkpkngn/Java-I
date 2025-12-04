import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws Exception {
        try{
            URI uri = new URI("https://www.ivondrak.cz/index.html");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responsonseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responsonseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        }
        catch(IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }
}
