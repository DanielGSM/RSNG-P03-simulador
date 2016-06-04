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
    private final float arrivalTime;
    /**
     * The time that the petition needs to be served (the time it needs to spend
     * in a thread).
     */
    private final float serviceTime;

    public ArrivalEvent(int eventID, float arrivalTime, float serviceTime) {
        super(eventID);
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public float getServiceTime() {
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
