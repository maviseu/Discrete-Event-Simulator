package cs2030.simulator;

/**
 * LeaveEvent class to simulate event of customer leaving.
 */

public class LeaveEvent extends Event {

    public LeaveEvent(int identifier, double time, String type) {
        super(identifier, time, type);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s%sleaves", time, identifier, type);
    }
}

