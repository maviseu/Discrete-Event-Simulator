import java.util.Scanner;
import java.util.PriorityQueue;
import cs2030.simulator.Event;
import cs2030.simulator.Customer;
import cs2030.simulator.MyComparator;
import cs2030.simulator.EventSimulator;
import cs2030.simulator.ArrivalEvent;
import cs2030.simulator.Statistics;
import cs2030.simulator.Server;

/** 
 * Main class to take in inputs.
 */
class Main {
    /** 
     * Has a scanner object to take in inputs.
     * It then creates a EventSimulator with the inputs received
     * to stimulate the execution of activities.
     * @param args the inputs of user
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int baseSeed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfCheckOut = sc.nextInt();
        int maxQueue = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate =  sc.nextDouble();
        double restRate = sc.nextDouble();
        double probability = sc.nextDouble();
        double probabilityG = sc.nextDouble(); 
        sc.close();
        
        EventSimulator eventSimulator = new EventSimulator(baseSeed, numOfServers, 
                numOfCheckOut, maxQueue, numOfCustomers, arrivalRate, serviceRate, 
                restRate, probability);
        eventSimulator.createCustomer(numOfCustomers, probabilityG);
        eventSimulator.doService();
    }
}
