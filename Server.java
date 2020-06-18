package cs2030.simulator;

import java.util.PriorityQueue;

/** 
 * Class to simulate the server, who determines
 * the change of events depending on their status.
 */

public class Server {
    /**
     * Server has unique serverID, availabletime,
     * booleans to keep track of whether it is serving, or resting.
     * It also has a priority queue of event and a queue limit.
     * as well as a static variable to generate serverID.
     */
    protected int serverID;
    protected boolean serving;
    protected boolean resting;
    protected double availableTime = 0;
    protected static int numOfServers = 0;
    protected double maxQueue;
    protected PriorityQueue<Event> queue;

    /**
     * Creates a server object with a unique ServerID.
     * Serving is to false since server is idle when created.
     * Resting is set to false as well.
     * Each server has a max limit to their priority queue.
     */
    public Server(int maxQueue) {
        this.serverID = numOfServers + 1;
        this.serving = false;
        this.resting = false;
        numOfServers++;
        this.maxQueue = maxQueue;
        this.queue = new PriorityQueue<>(maxQueue, new MyComparator());
    }

    /**
     * To get the server ID.
     */

    public int getID() {
        return serverID;
    }

    /**
     * To set server's next available timing by adding
     * current time and service time.
     */

    public void setAvailableTime(double time, double serviceTime) {
        availableTime = time + serviceTime;
    }

    /**
     * To get server's next available timing.
     */

    public double getAvailableTime() {
        return availableTime;
    }

    /**
     * To get server's type.
     */

    public String getType() {
        return "server";
    }

    /*
     * To get server's queue.
     */

    public PriorityQueue<Event> getQueue() {
        return queue;
    }
    

    /**
     * When server is done server, toggle serving to false or
     * when server is done resting, toggle resting to false.
     */

    public void reset() {
        if (serving) {
            this.serving = false;
        } else if (resting) {
            this.resting = false;
        }
    }

    /**
     * To toggle serving to true when server is currently serving.
     */

    public void setServe() {
        this.serving = true;
    }

    /**
     * To toggle resting to true when serving is currently resting.
     */

    public void setRest() {
        this.resting = true;
    }

    /**
     * To add event to queue to when customer is waiting in queue.
     */

    public void setQueue(Event event) {
        queue.add(event);
    }

    /**
     * It has a boolean canServe() to check whether
     * the server can serve a customer.
     */

    public boolean canServe() {
        if (!serving) {
            return true;
        } else {
            return false;
        }
    }
   
    /**
     *  It has a boolean canQueue() to check whether
     *  the server have space in the queue.
     */
    public boolean canQueue() {
        if (queue.size() == maxQueue) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * It has a boolean isResting() to check whether
     * the server is resting.
     */

    public boolean isResting() {
        if (resting) {
            return true;
        } else {
            return false;
        }
    }
}




