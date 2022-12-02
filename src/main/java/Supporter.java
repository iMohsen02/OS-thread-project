import java.time.LocalTime;
import java.util.ArrayList;

import static Color.AnsiColor.*;

/**
 * Supporter work in {@link InternetSupportCenter internet support center}. {@link Costumer costumers} call the supporters they provide some {@link InternetSupportCenter.Services services} to each costumer.
 * Supporters can provide services at the same time without any conflict.
 * <hr>
 * @since 1.0v
 * @author Mohsen Gholami - iMohsen02
 * @see #addToCostumer(Costumer)
 * @see #rest(long)
 * @see #doSleep(String, long, String)
 * @see #Supporter(String)  Supporter
 *
 */
public class Supporter extends Thread {

    /**
     * name of supporter
     */
    private String name;

    /**
     * the costumers are waiting that supporter answer the call
     */
    private ArrayList<Costumer> costumers;

    /**
     * the time supporter services to costumers. if the time grows more than 1s supporter should rest 0.5 more than normal rest
     */
    private long spendTime = 0;

    @Override
    public void run() {

        while(!this.costumers.isEmpty()) {

            doSleep(this + " start call to [ " +   this.costumers.get(0) + " ]",
                    this.costumers.get(0).getService().getDelay(),
                    this + " end of call to [ " + this.costumers.get(0) + " ]"
            );

            spendTime += this.costumers.get(0).getService().getDelay();
            this.costumers.remove(0).start();

            if (spendTime > 1000L) {
                rest(InternetSupportCenter.Services.REST.getDelay() + InternetSupportCenter.Services.EXTRA_REST.getDelay());
                spendTime = 0;
            } else rest(InternetSupportCenter.Services.REST.getDelay());
        }

    }

    /**
     * costumer call the supporter
     * @param costumer the costumer call the supporter and waiting for answer
     */
    public void addToCostumer(Costumer costumer) {
        this.costumers.add(costumer);
    }

    /**
     * supporter will rest after every call and  if {@link #spendTime} grows more than 1s supporter should rest +0.5s.
     * while supporter is relaxing. he doesn't do any things
     * @param restTime the time which supporter should rest
     */
    private void rest(long restTime) {
        doSleep(this + " went rest." , restTime, this + " came back.");
    }

    /**
     * supporter can do something a takes a time to do it, and it can't do any things else while it is doing some task.
     * it sleeps the thread.
     *
     * @param startText text to print before start sleeping the thread(give a task to do supporter)
     * @param sleepTime the time should thread sleep(time of task to do)
     * @param endText text to print after end sleeping the thread(end of task to do supporter)
     */
    private void doSleep(String startText, long sleepTime, String endText) {
        System.out.println(startText + "\ttime: " + LocalTime.now() + "\tduration: " + sleepTime / 1000.0 + "s");

        try {
            sleep(sleepTime);
        } catch (InterruptedException interruptedException) {
            System.err.println(currentThread().getName() + " can't sleep :(");
        }

        System.out.println(endText + "\ttime: " + LocalTime.now());
    }


    /**
     * Create a new supporter
     * @param name the name of supporter
     */
    public Supporter(String name) {
        this.name = name;
        this.costumers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return FOREGROUND_BRIGHT_BLUE96 + "Supporter[ " +  FOREGROUND_RED91 + this.name + FOREGROUND_BRIGHT_BLUE96 + " ]" + RESET_COLOR;
    }
}
