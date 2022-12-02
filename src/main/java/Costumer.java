import java.time.LocalTime;
import java.util.Random;

import static Color.AnsiColor.*;

/**
 * Costumer is a person which have the {@link #name} and call the {@link Supporter } to receive {@link InternetSupportCenter.Services services}.
 * the signal of the call probably can be weak, and it takes more time to receive services from supporters. after each call costumers have to connect to servery.
 * they can submit the poll or not. depends on each costumer it is different.
 *
 * <hr>
 *
 * @version 1.0
 * @since 2022
 * @author Mohsen Gholami - iMohsen02
 *
 * @see #Costumer(String, boolean, InternetSupportCenter.Services)  Costumer
 * @see #wantToPoll
 * @see #doSleep(String, long, String)
 * @see #getService()
 */
public class Costumer extends Thread {

    /**
     * Provide the delay may affect on costumer process
     */
    public enum Delays {
        WEAK_SIGNAL(100L),
        POOL(100L),
        CRITICISM(200L);
        private final long delay;

        Delays(long delay) {
            this.delay = delay;
        }

        /**
         * Return the delay
         * @return Return the delay
         */
        public long getValue() {
            return delay;
        }
    }

    /** name of costumer */
    private String name;

    /** it shows signal is weak or not */
    private boolean isSignalWeak;

    /** the service that costumer want */
    private InternetSupportCenter.Services service;
    /** it shows costumer wants to submit the poll or not */
    private boolean wantToPoll;

    /**
     * Create new costumer
     * @param name name of costumer
     * @param isSignalWeak it shows the signal is weak or not
     * @param service the service that costumer want
     */
    public Costumer(String name, boolean isSignalWeak, InternetSupportCenter.Services service) {
        this.name = name;
        this.isSignalWeak = isSignalWeak;
        this.service = service;
        this.wantToPoll = wantToPoll();
    }

    /**
     * Randomly Return a boolean that shows costumer want to submit the poll or not
     * @return a boolean that shows costumer want to submit the poll or not
     */
    public boolean wantToPoll() {
        return new Random().nextBoolean();
    }

    @Override
    public void run() {
        doPool();
    }

    /**
     * it sends the costumer to attend in servery. if costumer wants, he can submit the pool, otherwise can end the call
     */
    public void doPool() {
        if (this.wantToPoll) {
            doSleep(this.name + FOREGROUND_BRIGHT_GREEN + " start to poll" + RESET_COLOR, Delays.POOL.delay,  this.name + FOREGROUND_BRIGHT_GREEN + " end of poll time..." + RESET_COLOR);
            doSleep(this.name + FOREGROUND_BRIGHT_GREEN + " start to criticism" + RESET_COLOR, Delays.CRITICISM.delay, this.name + FOREGROUND_BRIGHT_GREEN + " end of call" + RESET_COLOR);
        } else
            doSleep(this.name + FOREGROUND_BRIGHT_GREEN + " start to poll" + RESET_COLOR, Delays.POOL.delay,  this.name + FOREGROUND_BRIGHT_GREEN + " end of call" + RESET_COLOR);
    }

    /**
     * it takes time to do some task for costumer.
     * @param startText the text print before task(like start pool...)
     * @param sleepTime the time that the task takes
     * @param endText the text print after task(like end pool...)
     */
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

    /**
     * Return the service costumer want
     * @return the service costumer want
     */
    public InternetSupportCenter.Services getService() {
        return service;
    }
}
