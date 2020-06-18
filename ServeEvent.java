package cs2030.simulator;

/**
 * ServeEvent class to simulate event of serving customer.
 */

public class ServeEvent extends Event {
    /**
     * Server involved with this event.
     */
    private Server server;

    public ServeEvent(int identifier, double time, String type, Server server) {
        super(identifier, time, type);
        this.server = server;
    }

    public Server getServer() {
        return server;
    }    

    /**
     * DoneEvent is created with the timing of
     * server's nextAvailableTime.
     * Server's nextAvailableTime is also updated.
     * @return DoneEvent
     */
    public DoneEvent handleDoneEvent(double serviceTime) {
        server.setAvailableTime(this.time, serviceTime);
        return new DoneEvent(identifier, server.getAvailableTime(), type, server);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s%sserved by %s %s", time, 
                identifier, type, server.getType(), server.getID());
    }
}

