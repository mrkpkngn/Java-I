import com.example.myapp.MyClass;
import com.example.myapp.utils.Utility;;

public class App {
    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass();
        Utility utility = new Utility();

        myClass.sendMessage();
        utility.printMessage();
    }
}