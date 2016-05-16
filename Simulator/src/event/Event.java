package event;

/**
 *
 * @author Daniel
 */
public abstract class Event {

    /**
     * The id of the event
     */
    private int id;
    /**
     * The time at which the event happens
     */
    private float arrivalTime;
    /**
     * The duration of the event
     */
    private float duration;

    public Event(int eventID, float arrivalTime, float duration) {
        this.id = eventID;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public int getId() {
        return this.id;
    }

    public float getArrivalTime() {
        return this.arrivalTime;
    }

    public float getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "Event\n"
                + "id: " + this.id
                + "\ntime: " + this.arrivalTime
                + "\nduration: " + this.duration
                + "\n";
    }
}
