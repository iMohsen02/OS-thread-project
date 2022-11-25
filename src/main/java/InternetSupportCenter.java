public class InternetSupportCenter {
    public enum Services {
        SELL_ADVICE(300L),
        TECHNICAL_ADVICE(500L),
        MODEM_CONFIG(200L);
        private final long delay;

        Services(long delay) {
            this.delay = delay;
        }

        public long getDelay() { return this.delay; }
    }
}
