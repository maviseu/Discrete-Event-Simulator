package cs2030.simulator;

/**
 * ServerRestEvent class to simulate the event of server resting.
 */ 

public class ServerRestEvent extends Event {
    /**
     * Server involved with this event.
     */
    private Server server;

    public ServerRestEvent(int identifier, double time, String type, Server server) {
        super(identifier, time, type);
        this.server = server;
    }

    public Server getServer() {
        return server;
    }    

    /**
     * Creates a ServerBackEvent by involving the server.
     * At the same time, setting the server's next available timing.
     * @return ServerBackEvent
     */

    public ServerBackEvent handleServerBackEvent(double restTime) {
        server.setAvailableTime(this.time, restTime);
        return new ServerBackEvent(identifier, server.getAvailableTime(), type, server);
    }
   
}

