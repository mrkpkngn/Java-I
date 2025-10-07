
import vehicle.*;


public class App {
    public static void main(String[] args) throws Exception {
        Passenger myCar, yourCar;
        yourCar = new Passenger("red", 4);
        yourCar.drive(25);
        yourCar.turn(90);
        yourCar.stop();
        myCar = (Passenger) yourCar.clone();
        myCar.drive(100);
        System.out.println("My car speed is " + myCar.getSpeed());
        System.out.println("Your car speed is " + yourCar.getSpeed());
        System.out.println(myCar == yourCar);
        myCar.stop();
        System.out.println(myCar.equals(yourCar));
    }
}
