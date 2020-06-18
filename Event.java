package cs2030.simulator;

/**
 * Abstract Event class which allows
 * subclasses to get next Event and update statistics.
 */

public abstract class Event {
    /**
     * Event stores the ID of their personnel,
     * regardless it is customerID or serverID.
     * and the time of the event,
     * as well as the type of customer or server.
     */
    public final int identifier;
    public final double time;
    public final String type;


    /**
     * Creates an Event object.
     * @param identifier ID of the customer of the event
     * @param time time at which event is created
     */

    public Event(int identifier, double time, String type) {
        this.identifier = identifier;
        this.time = time;
        this.type = type;
    }
    
    public int getID() {
        return identifier;
    }

    public double getArrivalTime() {
        return time;
    }

    public String getType() {
        return type;
    }

}

