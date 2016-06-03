package event;

/**
 *
 * @author Daniel
 */
public class OutputEvent extends ArrivalEvent {

    /**
     * Indicates if the petiton has been served or rejected
     */
    private final boolean served;
    /**
     * Absolute time in which the petition was asigned to a thread
     */
    private final Float serverTime;
    /**
     * Absolute time in which the petition ended (was finished serving)
     */
    private final Float outTime;

    public OutputEvent(int eventID, float arrivalTime, float serviceTime, boolean served, Float serverTime, Float outTime) {
        super(eventID, arrivalTime, serviceTime);

        //served petitions must include the serverTime and outTime, petitions rejected not
        if (served) {
            assert serverTime != null && outTime != null;
        } else {
            assert serverTime == null && outTime == null;
        }

        this.served = served;
        this.serverTime = serverTime;
        this.outTime = outTime;
    }

    public OutputEvent(ArrivalEvent arrivalEvent, boolean served, Float serverTime, Float outTime) {
        super(arrivalEvent);

        this.served = served;
        this.serverTime = serverTime;
        this.outTime = outTime;
    }

    public boolean isServed() {
        return served;
    }

    public float getServerTime() {
        return serverTime;
    }

    public float getOutTime() {
        return outTime;
    }

    @Override
    public String toString() {
        return "Output "
                + super.toString().substring(super.toString().indexOf(' ') + 1)
                + "served: " + this.served
                + "\nserverTime: " + this.serverTime
                + "\noutTime: " + this.outTime
                + "\n";

    }
}
