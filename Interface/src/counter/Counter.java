package counter;

public class Counter implements ICountable, IPrintable {
    private int _count = 0;

    public Counter(int count)
    {
        this._count = count;
    }

    public Counter()
    {
        this._count = 0;
    }

    @Override
    public void increment() {
        this._count++;
    }

    @Override
    public void decrement() {
        this._count--;
    }

    @Override
    public void printMessage() {
        System.out.println("Count: " + this._count);
    }

    public int getCount() {
        return this._count;
    }

    protected void close(){
        System.out.println("Counter object is being destroyed.");
    }
    
}
