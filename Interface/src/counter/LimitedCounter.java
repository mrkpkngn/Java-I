package counter;

public class LimitedCounter extends Counter {
    private int _limit;

    public LimitedCounter(int limit) {
        this._limit = limit;
    }

    @Override
    public void increment(){
        if(getCount() < this._limit){
            super.increment();
        }
    }
}
