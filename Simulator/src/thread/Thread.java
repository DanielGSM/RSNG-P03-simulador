package thread;

import event.ArrivalEvent;
import event.OutputEvent;

/**
 * Represents a server's thread that contains and is serving a petition
 * 
 * @author Daniel
 */
public class Thread {

    protected OutputEvent petition;

    /**
     * Constructor, simulates a petition entering in the thread
     * @param petition A petition to serve in form of a OutputEvent.
     * @param serverTime The absolute time in which the petition enters in the system/thread
     */
    public Thread(ArrivalEvent petition, float serverTime) {
        this.petition = new OutputEvent(petition, true, serverTime, serverTime + petition.getServiceTime());
    }
}
