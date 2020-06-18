package cs2030.simulator;

/**
 * Statistics class to keep track of total waiting time of customer,
 * number of customers who left without being served,
 * the number of customers who are served.
 */
public class Statistics {
    private double waitingTime = 0;
    private int numberServed = 0;
    private int numberLeft = 0;

    public Statistics() {
    }

    /**
     * To increase the waiting time of customers.
     * @param time the time the customer got served minus the time it arrived
     */

    public void addTime(double time) {
        waitingTime += time;
    }

    /**
     * To increase the number of customers who are served by one each time
     * a customer is served.
     */

    public void addServed() {
        numberServed += 1;
    }

    /** 
     * To increase the number of customers who left without being served
     * each time a customer leaves.
     */

    public void addLeft() {
        numberLeft += 1;
    }

    @Override
    public String toString() {
        double avg = waitingTime / numberServed;
        if (waitingTime == 0) {
            avg = 0.000;
        }
        return String.format("[%.3f %s %s]", avg, numberServed, numberLeft);
    }
}
