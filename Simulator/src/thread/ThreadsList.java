package thread;

import event.ArrivalEvent;
import event.OutputEvent;

/**
 * Represents the group of threads in a server.
 *
 * @author Daniel
 */
public class ThreadsList {

    /**
     * The number of threads.
     */
    private int numThreads;
    /**
     * The group of threads.
     */
    private Thread[] threads;

    /**
     * Constructor; initializes the threads of the class
     *
     * @param numThreads The number of Threads to create
     * @throws java.lang.Exception
     */
    public ThreadsList(int numThreads) throws Exception {
        if (numThreads < 1) {
            throw new Exception("Error; you need a minimum of one thread");
        } else {
            this.numThreads = numThreads;

            this.threads = new Thread[numThreads];
            for (int i = 0; i < numThreads; i++) {
                this.threads[i] = new Thread();
            }
        }
    }

    /**
     * Between all the petitions being serverd, the absolute time of the output
     * time of the first petition which will finish being served.
     *
     * @return ^That thing from above.
     * @throws java.lang.Exception when all the threads are idle.
     */
    public double nextOutTime() throws Exception {
        if (this.busyThreads() == 0) {
            throw new Exception("Error: There is not a next output time because there are no busy thread");
        }

        int i = 0;
        //we search the first busy thread
        while (!this.threads[i].isBusy()) {
            i++;
        }

        //we set the first minimum time found
        double minTime = this.threads[i].nextIdleTime();
        i++;

        //we search in the rest of the busy threads lesser times
        while (i < this.numThreads) {
            if (this.threads[i].isBusy() && this.threads[i].nextIdleTime() < minTime) {
                minTime = this.threads[i].nextIdleTime();
            }
            i++;
        }

        return minTime;
    }

    public int getNumThreads() {
        return this.numThreads;
    }

    public int busyThreads() {
        int x = 0;

        for (int i = 0; i < this.numThreads; i++) {
            if (this.threads[i].isBusy()) {
                x++;
            }
        }

        return x;
    }

    /**
     * Assigns a petition to a thread.
     *
     * @param arrivalEvent The petition to serve.
     * @param threadTime the absolute time in which the petition enters at the
     * system.
     * @throws Exception Fails if are the threads are busy.
     */
    public void AssignPetition(ArrivalEvent arrivalEvent, double threadTime) throws Exception {
        if (this.busyThreads() == this.numThreads) {
            throw new Exception("Error: all the threads are busy");
        } else {
            for (int i = 0; i < this.numThreads; i++) {
                if (!this.threads[i].isBusy()) {
                    this.threads[i].asignPetition(arrivalEvent, threadTime);
                    return;
                }
            }
        }
    }

    /**
     * From all the petitions being served, finishes processing the earlier one
     * and frees the thread to which that petition was assigned.
     *
     * @return The petition that finished being served.
     * @throws java.lang.Exception when there are now busy threads.
     */
    public OutputEvent advance() throws Exception {
        //TODO: find the thread with the next outTime, finish serving the petition
        //for that thread and assign the next petition in the queue to it
        double time = this.nextOutTime();

        OutputEvent out = null;
        for (int i = 0; i < this.numThreads; i++) {
            if (this.threads[i].isBusy() && this.threads[i].nextIdleTime() == time) {
                out = this.threads[i].finishServingPetition();
                break;
            }
        }

        return out;
    }
}
