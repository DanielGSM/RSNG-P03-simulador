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
    private final Float threadTime;
    /**
     * Absolute time in which the petition ended (was finished serving)
     */
    private final Float outTime;

    /**
     * Served petitions must have valid values for threadTime and outTime.
     * Rejected petitions must have those values at null.
     */
    public OutputEvent(int eventID, float arrivalTime, float serviceTime, boolean served, Float threadTime, Float outTime) {
        super(eventID, arrivalTime, serviceTime);

        this.served = served;

        if (this.served) {
            this.threadTime = threadTime;
            this.outTime = outTime;
        } else {
            this.threadTime = null;
            this.outTime = null;
        }

        assert (this.served && this.threadTime != null && this.outTime != null)
                || (!this.served && this.threadTime == null && this.outTime == null);

    }

    /**
     * Served petitions must have valid values for threadTime and outTime.
     * Rejected petitions must have those values at null.
     */
    public OutputEvent(ArrivalEvent arrivalEvent, boolean served, Float threadTime, Float outTime) {
        this(arrivalEvent.getId(), arrivalEvent.getArrivalTime(), arrivalEvent.getServiceTime(), served, threadTime, outTime);
    }

    public boolean isServed() {
        return served;
    }

    public float getThreadTime() {
        return threadTime;
    }

    public float getOutTime() {
        return outTime;
    }

    @Override
    public String toString() {
        return "Output "
                + super.toString().substring(super.toString().indexOf(' ') + 1)
                + "served: " + this.served
                + "\nthreadTime: " + this.threadTime
                + "\noutTime: " + this.outTime
                + "\n";

    }
}
