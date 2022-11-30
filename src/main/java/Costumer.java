import java.time.LocalTime;
import java.util.Random;

import static Color.AnsiColor.*;

public class Costumer extends Thread {
    public enum Delays {
        WEAK_SIGNAL(100L),
        SALE(300L),
        TECHNICAL(500L),
        CONFIG(200L),
        POOL(100L),
        CRITICISM(200L);
        private final long delay;

        Delays(long delay) {
            this.delay = delay;
        }

        public long getValue() {
            return delay;
        }
    }

    private String name;
    private boolean isSignalWeak;
    private InternetSupportCenter.Services service;
    private boolean wantToPoll;

    public Costumer(String name, boolean isSignalWeak, InternetSupportCenter.Services service) {
        this.name = name;
        this.isSignalWeak = isSignalWeak;
        this.service = service;
        this.wantToPoll = wantToPoll();
    }

    public boolean wantToPoll() {
        return new Random().nextBoolean();
    }

    @Override
    public void run() {
//        System.out.println(FOREGROUND_BRIGHT_BLUE96 + "Thread" + FOREGROUND_RED91 + "["+ this.name +"] - " + FOREGROUND_BRIGHT_BLUE96 + "Costumer " + RESET_COLOR + "start");

//        startService();
        doPool();

//        System.out.println(FOREGROUND_BRIGHT_BLUE96 + "Thread" + FOREGROUND_RED91 + "["+ this.name +"] - " + FOREGROUND_BRIGHT_BLUE96 + "Costumer " + RESET_COLOR + "end");
    }

    public void doTasks() {

    }

    public void startService() {
        doSleep("service: " + this.service.name() + " from: " + this + " start",
                isSignalWeak ? this.service.getDelay() + Delays.WEAK_SIGNAL.delay: service.getDelay(),
                "service: " + this.service.name() + " from: " + this + " stopped");
    }

    public void doPool() {
        if (this.wantToPoll) {
            doSleep(this.name + FOREGROUND_BRIGHT_GREEN + " start to poll" + RESET_COLOR, Delays.POOL.delay,  this.name + FOREGROUND_BRIGHT_GREEN + " end of poll time..." + RESET_COLOR);
            doSleep(this.name + FOREGROUND_BRIGHT_GREEN + " start to criticism" + RESET_COLOR, Delays.CRITICISM.delay, this.name + FOREGROUND_BRIGHT_GREEN + " end of call" + RESET_COLOR);
        } else
            doSleep(this.name + FOREGROUND_BRIGHT_GREEN + " start to poll" + RESET_COLOR, Delays.POOL.delay,  this.name + FOREGROUND_BRIGHT_GREEN + " end of call" + RESET_COLOR);
    }

    private void doSleep(String startText, long sleepTime, String endText) {
        System.out.println(startText + "\ttime: " + LocalTime.now());

        try {
            sleep(sleepTime);
        } catch (InterruptedException interruptedException) {
            System.err.println(currentThread().getName() + " can't sleep :(");
        }

        System.out.println(endText + "\ttime: " + LocalTime.now());
    }

    @Override
    public String toString() {
        return FOREGROUND_BLUE94 + "costumer { name: "+ FOREGROUND_RED91 + this.name + FOREGROUND_BLUE94 + "\tisWeakSignal: " + FOREGROUND_RED91 + this.isSignalWeak + FOREGROUND_BLUE94 +"\twantToPool: " + FOREGROUND_RED91 + this.wantToPoll + FOREGROUND_BLUE94 + "\tService:" + FOREGROUND_RED91 + this.service + FOREGROUND_BLUE94 + " }" + RESET_COLOR;
    }

    public InternetSupportCenter.Services getService() {
        return service;
    }
}
