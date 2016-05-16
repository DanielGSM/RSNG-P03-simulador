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

    public Event(int eventID) {
        this.id = eventID;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Event\n"
                + "id: " + this.id
                + "\n";
    }
}
