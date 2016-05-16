package event;

/**
 *
 * @author Daniel
 */
public class ArrivalEvent extends Event {

    private float arrivalTime;
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
