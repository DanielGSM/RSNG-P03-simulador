package event;

/**
 * Represents an incoming petition at a certain time, which needs a certain
 * amount of time to be served.
 *
 * @author Daniel
 */
public class ArrivalEvent extends Event {

    /**
     * The time at which the event arrives at the system.
     */
    private final double arrivalTime;
    /**
     * The time that the petition needs to be served (the time it needs to spend
     * in a thread).
     */
    private final double serviceTime;

    public ArrivalEvent(int eventID, double arrivalTime, double serviceTime) {
        super(eventID);
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "Arrival "
                + super.toString()
                + "arrivalTime: " + this.arrivalTime
                + "\nserviceTime: " + this.serviceTime
                + "\n";
    }
}
