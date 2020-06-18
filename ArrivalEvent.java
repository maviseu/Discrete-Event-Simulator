package cs2030.simulator;

/**
 * ArrivalEvent class to simulate the event of customer arriving.
 */

public class ArrivalEvent extends Event {

    public ArrivalEvent(int identifier, double time, String type) {
        super(identifier, time, type);
    }

    /**
     * Creates a ServedEvent by involving the freeServer.
     * At the same time, setting the server's availablity to be 
     * occupied.
     * @return ServeEvent
     */
    public ServeEvent handleServeEvent(Server server) {
        server.setServe();
        return new ServeEvent(identifier, time, type, server);
    }

    /**
     * Creates a WaitEvent by involving the freeServer.
     * At the same time, adding the event into server's queue.
     * @return WaitEvent
     */
    public WaitEvent handleWaitEvent(Server server) {
        WaitEvent nextEvent = new WaitEvent(identifier, time, type, server);
        server.setQueue(nextEvent);
        return nextEvent;
    }

    /**
     * Creates a LeaveEvent.
     * @return LeaveEvent
     */
    public LeaveEvent handleLeaveEvent() {
        return new LeaveEvent(identifier,time,type);
    }
    

    /**
     * Takes in a server array to check which server is available.
     * If servers can serve and they are not resting, server found
     * will be returned. If server is still not found,
     * check for availability in the server's queue.
     * If customer is greedy, available server with the shortest
     * queue will be returned. 
     * If customer is normal, first available server will be returned.
     * @return Server if the server is free or has shortest queue
     *     null if none are free.
     */

    public Server getFreeServer(Server[] servers) {
        boolean availableServer = false;
        Server thisServer = null;

        for (int i = 0; i < servers.length; i++) {
            if (servers[i].canServe() && !servers[i].isResting()) {
                return servers[i];
            }
        }

        if (!availableServer) {
            int minLength = Integer.MAX_VALUE;
            Server shortestServer = null;
        
            for (int j = 0; j < servers.length; j++) {
                if (type == "(greedy) ") {
                    if (servers[j].canQueue() && servers[j].getQueue().size() < minLength) {
                        minLength = servers[j].getQueue().size();
                        shortestServer = servers[j];
                    }
                } else {
                    if (servers[j].canQueue()) {
                        availableServer = true;
                        thisServer = servers[j];
                        break;
                    }
                }
            }

            if (availableServer) {
                return thisServer;
            } 

            if (shortestServer != null) { 
                return shortestServer;
            }
            
        }
        return null;
    }
    

    @Override
    public String toString() {
        return String.format("%.3f %s%sarrives", time, identifier, type);
    }


}
