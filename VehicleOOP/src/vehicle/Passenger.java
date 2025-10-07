package vehicle;

public class Passenger extends AbstractVehicle {
    private int _numOfPassengers;

    public Passenger(String color, int numOfPassengers) {
        super(color);
        _numOfPassengers = numOfPassengers;
    }

    public int getNumOfPassengers() {
        return _numOfPassengers;
    }

    @Override
    public Object clone() {
        return (Passenger) super.clone();
    }
    
}
