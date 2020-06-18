package cs2030.simulator;

import java.util.Comparator;

/**
 * Comparator class to create comparison criteria for PriorityQueue.
 */
public class MyComparator implements Comparator<Event> {
    /**
     * Compares 2 events and decides which has a higher priority
     * based on their arrival timing. If there is a tie,
     * customer ID is checked instead.
     * @param o1 first event
     * @param o2 second event
     * @return -1 if first event is prioritised over than second
     *     1 if the second event is prioritised over the first
     *     0 if no priority
     */

    public int compare(Event o1, Event o2) {
        if (o1.getArrivalTime() < o2.getArrivalTime()) { 
            return -1; //o1 is earlier than o2
        } else if (o1.getArrivalTime() > o2.getArrivalTime()) {
            return 1; //o2 is earlier than o1
        } else if (o1.getID() < o2.getID()) {
            return -1; //o1 has priority
        } else if (o1.getID() > o2.getID()) {
            return 1; //o2 has priority
        } else {
            return 0;
        }
    }
}
