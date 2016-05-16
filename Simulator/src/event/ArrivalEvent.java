package event;

/**
 *
 * @author Daniel
 */
public class ArrivalEvent extends Event {

    public ArrivalEvent(int eventID, float time, float duration) {
        super(eventID, time, duration);
    }

    @Override
    public String toString() {
        return "Arrival " + super.toString();
    }
}
