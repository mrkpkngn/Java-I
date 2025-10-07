package vehicle;

public class Truck extends AbstractVehicle {
    int _loading;

    public Truck(String color, int loading) {
        super(color);
        this._loading = loading;
    }

    @Override
    public void drive(int newSpeed)
    {
        if(newSpeed <= 100){
            super.drive(newSpeed);
        }
        else{
            super.drive(100);
        }
    }

    public void load(int loading) {
        this._loading = loading;
    }

    public int getLoading() {
        return _loading;
    }

}
