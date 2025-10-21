package counter;

public class StopWatch implements ICountable, IPrintable {
    private int hours, minutes, seconds;

    @Override
    public void increment() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
        }
    }

    @Override
    public void decrement(){
        if(hours == 0 && minutes == 0 && seconds == 0) {
            return;
        }
        seconds--;
        if (seconds < 0) {
            seconds = 59;
            minutes--;
            if (minutes < 0) {
                minutes = 59;
                hours--;
            }
        }
    }

    @Override
    public void printMessage(){
        System.out.println("Stopwatch: " + hours + " hours " + minutes + " mins " + seconds + " secs");
    }
}
