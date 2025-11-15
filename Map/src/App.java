import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        Map<String, Integer>map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        for(Map.Entry<String, Integer>entry : map.entrySet()){
            String Key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(Key + " is " + value + " years old.");
        }
    }
}
