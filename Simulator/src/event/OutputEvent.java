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
    private final Double threadTime;
    /**
     * Absolute time in which the petition ended (was finished serving)
     */
    private final Double outTime;

    /**
     * Served petitions must have valid values for threadTime and outTime.
     * Rejected petitions must have those values at null.
     */
    public OutputEvent(int eventID, double arrivalTime, double serviceTime, boolean served, Double threadTime, Double outTime) {
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
    public OutputEvent(ArrivalEvent arrivalEvent, boolean served, Double threadTime, Double outTime) {
        this(arrivalEvent.getId(), arrivalEvent.getArrivalTime(), arrivalEvent.getServiceTime(), served, threadTime, outTime);
    }

    public boolean isServed() {
        return served;
    }

    public double getThreadTime() {
        return threadTime;
    }

    public double getOutTime() {
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
