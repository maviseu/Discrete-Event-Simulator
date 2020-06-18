package cs2030.simulator;

/** 
 * Customer class for keeping customer's information.
 */
public class Customer {
    /**
     * Each customer has an unique customerID and
     * the time they arrive.
     * It has a static variable to keep track 
     * of number of customers and generate customerID.
     * It also has a boolean to determine whether
     * customer is greedy.
     */
    private final int customerID;
    private final double arrivalTime;
    private static int numOfCustomers = 0;
    private boolean greedy;

    /** 
     * Creates a customer object.
     * @param arrivalTime the time they arrive
     * @param greedy to determine whether customer is greedy
     */
    public Customer(double arrivalTime, boolean greedy) {
        this.customerID = numOfCustomers + 1;
        this.arrivalTime = arrivalTime; 
        this.greedy = greedy;
        numOfCustomers++;
    }

    public int getID() {
        return this.customerID;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Returns a string which determine's customer type.
     */
    public String getType() {
        if (greedy) {
            return "(greedy) ";
        } else {
            return " ";
        }
    } 

}

