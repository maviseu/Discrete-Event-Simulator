package cs2030.simulator;


/**
 * WaitEvent class to simulate event of customer waiting.
 */
public class WaitEvent extends Event {
    /**
     * Server involved with this event.
     */
    private Server server;

    public WaitEvent(int identifier, double time, String type, Server server) {
        super(identifier, time, type);
        this.server = server;
    }
   
    public Server getServer() {
        return server;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s%swaits to be served by %s %s", time, 
                identifier, type, server.getType(), server.getID());
    }
}

