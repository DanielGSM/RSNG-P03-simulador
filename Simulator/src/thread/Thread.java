package thread;

import event.ArrivalEvent;
import event.OutputEvent;

/**
 * Represents a server's thread that contains and is serving a petition
 *
 * @author Daniel
 */
public class Thread {

    private OutputEvent petition;

    /**
     * Constructor; creates an empty thread
     */
    public Thread() {
        this.petition = null;
    }

    /**
     * Indicates if a thread is idle (is not serving a petition, is empty)
     *
     * @return True if the thread is idle. False if it's serving a petition
     */
    public boolean isIdle() {
        return this.petition == null;
    }

    /**
     * Simulates a petition entering in the thread
     *
     * @param petition A petition to serve in form of a ArrivalEvent.
     * @param serverTime The absolute time in which the petition enters in the
     * system/thread
     * @throws java.lang.Exception
     */
    public void asignPetition(ArrivalEvent petition, float serverTime) throws Exception {
        if (!this.isIdle()) {
            throw new Exception("Error; can't assign a petition to a busy thread");
        } else {
            this.petition = new OutputEvent(petition, true, serverTime, serverTime + petition.getServiceTime());
        }
    }

    /**
     * Finishes serving a petition and returns it in form of an OutputEvent
     *
     * @return The OutputEvent that represents the served petition
     */
    public OutputEvent finishServingPetition() {
        OutputEvent temp = this.petition;
        this.petition = null;

        return temp;
    }

    /**
     * Returns the next absolute time where the thread will be idle; that is,
     * the time in which the current petition finishes being served.
     *
     * @return The time in which the current petition finishes being served.
     */
    public float nextIdleTime() {
        return this.petition.getOutTime();
    }
}
