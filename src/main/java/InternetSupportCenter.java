import java.util.Arrays;

/**
 * Integer Support Center contains some {@link Supporter supporters} which they are service to the {@link Costumer costumers } which they are waiting to call.
 * in Internet support center every {@link Supporter} just can services to a {@link Costumer} and all supporter can work together in the same time
 *
 * <hr>
 * @since 1.0v
 * @author Mohsen Gholami - iMohsen02
 * 
 * @see #InternetSupportCenter(int)  InternetSupportCenter
 * @see #waitToCallToSupporter(Costumer)
 * @see #startAllThreads()
 */
public class InternetSupportCenter {


    /**
     * {@link Services} include all service time which every supporter can do.
     *
     * @see #getDelay()
     */
    public enum Services {
        SELL(300L),
        TECHNICAL(500L),
        CONFIG(200L),
        REST(100L),
        EXTRA_REST(500L);
        private final long delay;

        Services(long delay) {
            this.delay = delay;
        }

        /**
         * Return the delay time of the service
         * @return the delay time of the service
         */
        public long getDelay() { return this.delay; }
    }

    /**
     * all supporters
     */
    private Supporter[] supporters;

    /**
     * it determines when a costumer call the internet support center which supporter should do service
     */
    private int selectedSupporter = 0;

    /**
     * Set the number of supporters which work in {@link InternetSupportCenter Internet Support Center}
     * @param numberOfSupporters the number of supporters work in Internet support center
     */
    public InternetSupportCenter(int numberOfSupporters) {
        this.supporters = new Supporter[numberOfSupporters];
        Arrays.setAll(supporters, i -> new Supporter("Supporter " + i));
    }

    /**
     * Start all {@link Supporter} to work together
     */
    public void startAllThreads() {
        for (Supporter supporter : supporters) supporter.start();
    }

    /**
     * set all costumers and supporters. somehow the costumers call the supporters and waiting for answers
     * @param costumer the costumer which call the costumer
     */
    public void waitToCallToSupporter(Costumer costumer) {
        supporters[selectedSupporter].addToCostumer(costumer);
        selectedSupporter = (selectedSupporter + 1) % supporters.length;
    }
}
