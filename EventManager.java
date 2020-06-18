package cs2030.simulator;

/**
 * EventManager class that handles the transitions of Events.
 */

public class EventManager {

    public EventManager() {
    }
    
    /**
     * Creates a next Event based on the current Event.
     * @param servers an array of Servers to be checked
     * @param gen RandomGenerator object to generate timings
     * @param firstEvent current event to be evaluated
     * @param stats Statistics object to update statistics
     * @param probability probability of server resting
     * @return the next event based on the current event
     */
    public Event getNext(Server[] servers, RandomGenerator gen, 
            Event firstEvent, Statistics stats, double probability) {
        if (firstEvent instanceof ArrivalEvent) {
            ArrivalEvent event = (ArrivalEvent) firstEvent;
            return getNextArrival(event, servers, stats);
        } else if (firstEvent instanceof ServeEvent) {
            ServeEvent event = (ServeEvent) firstEvent;
            return getNextServe(event, gen);
        } else if (firstEvent instanceof DoneEvent) {
            DoneEvent event = (DoneEvent) firstEvent;            
            return getNextDone(event, gen, stats, probability);
        } else if (firstEvent instanceof ServerBackEvent) {
            ServerBackEvent event = (ServerBackEvent) firstEvent;            
            return getNextServerRest(event, stats);
        } else {
            return null;
        }
    }

    /**
     * Helps to get next event for ArrivalEvents.
     * @param event an ArrivalEvent to be evaluated
     * @param servers an array of servers to be checked
     * @param stats Statistics object to update statistics
     * @return the next event based on server's availability
     */ 
    public Event getNextArrival(ArrivalEvent event, Server[] servers, Statistics stats) {   
        Server freeServer = event.getFreeServer(servers);        
        if (freeServer == null) {
            stats.addLeft();
            return event.handleLeaveEvent();
        } else if (freeServer.canServe() && !freeServer.isResting()) {
            return event.handleServeEvent(freeServer); 
        } else if (freeServer.canQueue()) {
            return event.handleWaitEvent(freeServer);
        } else {
            return null;
        }
    }

    /**
     * Helps to get next event for ServeEvents.
     * @param event a ServeEvent to be evaluated
     * @param gen RandomGenerator object to generate timings
     * @return a DoneEvent as customer is done being served
     */
    public DoneEvent getNextServe(ServeEvent event, RandomGenerator gen) {
        double serviceTime = gen.genServiceTime();  
        return event.handleDoneEvent(serviceTime);
    }

    /**
     * Helps to get next event for DoneEvents.
     * @param event a DoneEvent to be evaluated
     * @param gen RandomGenerator object to generate timings
     * @param stats Statistics object to update statistics
     * @param probability probability of server resting
     * @return the next event based on server's availability
     */
    public Event getNextDone(DoneEvent event, RandomGenerator gen, 
            Statistics stats, double probability) {
        Server thisServer = event.getServer();
        thisServer.reset();
        stats.addServed();
            
        if (thisServer.getType() == "server") {
            double randomRest = gen.genRandomRest();
            if (randomRest < probability) {
                ServerRestEvent serverRest = new ServerRestEvent(thisServer.getID(), 
                        event.getArrivalTime(), thisServer.getType(), thisServer);
                thisServer.setRest();
                double restPeriod = gen.genRestPeriod();
                return serverRest.handleServerBackEvent(restPeriod);
            }
        }
        return serveNext(event, thisServer, stats);
    }

    /**
     * Helps to get next event for ServerRestEvents.
     * @param event ServerBackEvent to be evaluated
     * @param stats Statistics object to update statistics
     * @return ServeEvent after server is back
     */
    public ServeEvent getNextServerRest(ServerBackEvent event, Statistics stats) {
        Server thisServer = event.getServer();
        thisServer.reset();
        return serveNext(event, thisServer, stats);
    }

    /**
     * Helps to serve the next customer in server's queue, if there is.
     * @param event Event to be evaluated
     * @param server server of current event
     * @param stats Statistics object to update statistics
     * @return ServeEvent if there are still customers to serve
     */
    public ServeEvent serveNext(Event event, Server server, Statistics stats) {
        if (!server.getQueue().isEmpty()) {
            Event nextServe = server.getQueue().poll();
            server.setServe();
            double duration = event.getArrivalTime() - nextServe.getArrivalTime();
            stats.addTime(duration);
            return new ServeEvent(nextServe.getID(), server.getAvailableTime(), 
                    nextServe.getType(), server);
        } 
        return null;
    }

}
        

    

