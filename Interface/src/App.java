
import counter.*;

public class App {
    public static void main(String[] args) throws Exception {
        ICountable counter = createCounter();
        IPrintable printer = (IPrintable) counter;
        
        for(int x = 0; x < 15; x++)
        {
            counter.increment();
        }
        
        printer.printMessage();
    }

    public static ICountable createCounter() {
        return new Counter(20);
    }
}
