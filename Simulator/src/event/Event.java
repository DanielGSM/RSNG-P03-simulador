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
    private float time;
    /**
     * The duration of the event
     */
    private float duration;

    public Event(int eventID, float time, float duration) {
        this.id = eventID;
        this.time = time;
        this.duration = duration;
    }

    public int getId() {
        return this.id;
    }

    public float getTime() {
        return this.time;
    }

    public float getDuration() {
        return this.duration;
    }
}
