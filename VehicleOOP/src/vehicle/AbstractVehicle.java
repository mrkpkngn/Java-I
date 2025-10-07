package vehicle;

public abstract class AbstractVehicle implements Cloneable {
    private int _speed;
    private int _direction;
    private String _color;

    public AbstractVehicle(String color) {
        this._color = color;
    }

    public int getSpeed() {
        return _speed;
    }

    public int getDirection() {
        return _direction;
    }

    public String getColor() {
        return _color;
    }

    public void drive(int speed) {
        this._speed = speed;
    }

    public void turn(int direction) {
        this._direction = direction;
    }

    public void stop() {
        this._speed = 0;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AbstractVehicle) {
            AbstractVehicle other = (AbstractVehicle) obj;
            return this._speed == other._speed &&
                   this._direction == other._direction &&
                   this._color.equals(other._color);
        }
        else {
            return false;
        }
    }
}