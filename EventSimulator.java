package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.Iterator;


/*
 * Simulator class to simulate the change of events.
 */

public class EventSimulator {
    /**
     * It has an array of servers,
     * a PriorityQueue to store events,
     * a Statistics object to keep track of customer data,
     * a RandomGenerator gen for generating arrival
     * and service times,
     * and probability of server resting.
     */
    private Server[] servers;
    private PriorityQueue<Event> queue;
    private Statistics stats = new Statistics();
    private RandomGenerator gen;
    private double probability;
    /**
     * It creates an EventSimulator object which
     * creates a Server array with server objects, a
     * PriorityQueue and a RandomGenerator.
     * @param baseSeed denoting the base seed for RandomGenerator object
     * @param numOfServers number of servers
     * @param numOfCheckOut number of checkout counters
     * @param maxQueue the max limit of each Server's queue
     * @param numOfCustomers number of customers
     * @param arrivalRate arrival rate, mu in RandomGenerator constructor
     * @param serviceRate service rate, lambda in RandomGenerator constructor
     * @param restRate rest rate, p in RandomGenerator constructor
     * @param probability probability of server resting
     */

    public EventSimulator(int baseSeed, int numOfServers, int numOfCheckOut, 
            int maxQueue, int numOfCustomers, double arrivalRate, double serviceRate,
            double restRate, double probability) {
       
        this.servers = new Server[numOfServers + numOfCheckOut]; 
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(maxQueue);
        }

        this.queue = new PriorityQueue<>(new MyComparator());        
        PriorityQueue<Event> checkOutQueue = new PriorityQueue<>(maxQueue,new MyComparator());
        for (int j = numOfServers; j < numOfServers + numOfCheckOut; j++) {
            servers[j] = new SelfCheckOut(maxQueue, checkOutQueue);
        }

        this.gen = new RandomGenerator(baseSeed, arrivalRate, serviceRate, restRate); 
        this.probability = probability;        
    }

    /**
     * Helps in creating a Customer or GreedyCustomer and ArrivalEvents 
     * in randomised time, then adding them into the PriorityQueue.
     */
    public void createCustomer(int numOfCustomers, double probabilityG) {
        double time = 0;

        //0.000 1 arrives
        double randomGreedy = gen.genCustomerType();
        boolean greedy = false;
        if (randomGreedy < probabilityG) {
            greedy = true;
        }
        Customer customer = new Customer(time, greedy);
        ArrivalEvent event = new ArrivalEvent(customer.getID(), 
                customer.getArrivalTime(), customer.getType());
        queue.add(event);

        for (int i = 0; i < numOfCustomers - 1; i++) {
            double interTime = gen.genInterArrivalTime();
            time = time + interTime;

            randomGreedy = gen.genCustomerType();
            greedy = false;
            if (randomGreedy < probabilityG) {
                greedy = true;
            } 
            customer = new Customer(time, greedy);
            event = new ArrivalEvent(customer.getID(), 
                    customer.getArrivalTime(), customer.getType());

            queue.add(event);
        }
    }

    /**
     *  Creates a eventManager to deal with the sequence of events.
     *  This process is split into few stages.
     *  1) EventSimulator starts doing a service by first 
     *  polling a priority event from the priority queue.
     *  2) Information of the event printed to signal the start of processing it.
     *  3) Depending on the type of event, 
     *  the eventManager would assigned them to another event which comes after.
     *  4) After getting the next event, it would be added back to the queue.
     *  5) Print the statistics when the PriorityQueue is empty.
     */    
    public void doService() {
        EventManager eventManager = new EventManager();
        while (!queue.isEmpty()) {
            Event firstEvent = queue.poll();
            if (!(firstEvent instanceof ServerBackEvent) &&
                    !(firstEvent instanceof ServerRestEvent)) {
                System.out.println(firstEvent);
            } 
            Event nextEvent = eventManager.getNext(servers, gen, firstEvent, stats, probability);
            if (nextEvent != null) {
                queue.add(nextEvent);
            }
        }
        System.out.println(stats);
        

    }    

}
