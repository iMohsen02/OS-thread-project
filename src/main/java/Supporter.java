import java.time.LocalTime;
import java.util.ArrayList;

import static Color.AnsiColor.*;

public class Supporter extends Thread {
    private String name;

    private ArrayList<Costumer> costumers;

    private long spendTime = 0;

    @Override
    public void run() {
//        System.out.println(FOREGROUND_BRIGHT_BLUE96 + this + RESET_COLOR + " start");

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

//        System.out.println(FOREGROUND_BRIGHT_BLUE96 + this + RESET_COLOR + "end");
    }

    public void addToCostumer(Costumer costumer) {
        this.costumers.add(costumer);
    }

    private void rest(long restTime) {
        doSleep(this + " went rest." , restTime, this + " came back.");
    }

    private void doSleep(String startText, long sleepTime, String endText) {
        System.out.println(startText + "\ttime: " + LocalTime.now() + "\tduration: " + sleepTime / 1000.0 + "s");

        try {
            sleep(sleepTime);
        } catch (InterruptedException interruptedException) {
            System.err.println(currentThread().getName() + " can't sleep :(");
        }

        System.out.println(endText + "\ttime: " + LocalTime.now());
    }


    public Supporter(String name) {
        this.name = name;
        this.costumers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return FOREGROUND_BRIGHT_BLUE96 + "Supporter[ " +  FOREGROUND_RED91 + this.name + FOREGROUND_BRIGHT_BLUE96 + " ]" + RESET_COLOR;
    }
}
