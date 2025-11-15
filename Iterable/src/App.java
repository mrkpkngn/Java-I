import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(100);

        for (int num : numbers){
            System.out.println(num);
        }

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
