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

    /**
     * Served petitions must have valid values for serverTime and outTime.
     * Rejected petitions must have those values at null.
     */
    public OutputEvent(int eventID, float arrivalTime, float serviceTime, boolean served, Float serverTime, Float outTime) {
        super(eventID, arrivalTime, serviceTime);

        this.served = served;

        if (this.served) {
            this.serverTime = serverTime;
            this.outTime = outTime;
        } else {
            this.serverTime = null;
            this.outTime = null;
        }

        assert (this.served && this.serverTime != null && this.outTime != null)
                || (!this.served && this.serverTime == null && this.outTime == null);

    }

    /**
     * Served petitions must have valid values for serverTime and outTime.
     * Rejected petitions must have those values at null.
     */
    public OutputEvent(ArrivalEvent arrivalEvent, boolean served, Float serverTime, Float outTime) {
        this(arrivalEvent.getId(), arrivalEvent.getArrivalTime(), arrivalEvent.getServiceTime(), served, serverTime, outTime);
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
