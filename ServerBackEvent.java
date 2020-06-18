package cs2030.simulator;

/**
 * ServerBackEvent class to simulate event of serving
 * coming back to serve.
 */

public class ServerBackEvent extends Event {
    /*
     * Server involved with this event.
     */
    private Server server;

    public ServerBackEvent(int identifier, double time, String type, Server server) {
        super(identifier, time, type);
        this.server = server;
    }

    public Server getServer() {
        return server;
    }
    
}
