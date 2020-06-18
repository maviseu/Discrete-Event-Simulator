package cs2030.simulator;

/**
 * DoneEvent class to simulate the event of completion of service.
 */

public class DoneEvent extends Event {
    /**
     * Server involved with this event.
     */
    private Server server;

    public DoneEvent(int identifier, double time, String type, Server server) {
        super(identifier, time, type);
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s%sdone serving by %s %s", time, 
                identifier, type, server.getType(), server.getID());
    }
}

