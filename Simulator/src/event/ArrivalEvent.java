package event;

/**
 *
 * @author Daniel
 */
public class ArrivalEvent extends Event {

    /**
     * The time at wich the event arrives at the system
     */
    private float arrivalTime;
    /**
     * The time that the petition needs to be served
     */
    private float serviceTime;

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
