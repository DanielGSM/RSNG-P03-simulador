package event;

/**
 *
 * @author Daniel
 */
public class ArrivalEvent extends Event {

    public ArrivalEvent(int eventID, float arrivalTime, float duration) {
        super(eventID, arrivalTime, duration);
    }

    @Override
    public String toString() {
        return "Arrival " + super.toString();
    }
}
