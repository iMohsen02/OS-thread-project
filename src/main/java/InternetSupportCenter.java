import java.util.Arrays;

public class InternetSupportCenter {
    public void startAllThreads() {
        for (Supporter supporter : supporters) supporter.start();
    }

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

        public long getDelay() { return this.delay; }
    }

    private Supporter[] supporters;
    private int selectedSupporter = 0;
    public InternetSupportCenter(int numberOfSupporters) {
        this.supporters = new Supporter[numberOfSupporters];
        Arrays.setAll(supporters, i -> new Supporter("Supporter " + i));
    }

    public void waitToCallToSupporter(Costumer costumer) {
        supporters[selectedSupporter].addToCostumer(costumer);
        selectedSupporter = (selectedSupporter + 1) % supporters.length;
    }
}
