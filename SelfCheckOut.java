package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * Class to simulate the self check-out counter.
 */

public class SelfCheckOut extends Server {

    /**
     * Creates a self checkout counter with a unique ID.
     * Serving is to false since counter is idle when created.
     * Resting is always set to false.
     * Each counter has a max limit to their priority queue.
     */

    public SelfCheckOut(int maxQueue,PriorityQueue<Event> queue) {
        super(maxQueue);
        this.serverID = numOfServers;
        this.serving = false;
        this.resting = false;
        this.queue = queue;
    }

    /**
     * Since resting is always false unlike human servers.
     */
    @Override
    public void setRest() {
    }

    @Override
    public String getType() {
        return "self-check";
    }
}
