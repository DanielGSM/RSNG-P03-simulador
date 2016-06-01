package thread;

import event.ArrivalEvent;

/**
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
     */
    public float nextOutTime() {
        float minTime = threads[0].nextIdleTime();

        for (int i = 1; i < this.numThreads; i++) {
            if (this.threads[i].nextIdleTime() < minTime) {
                minTime = this.threads[i].nextIdleTime();
            }
        }

        return minTime;
    }

    /**
     * The number of threads.
     *
     * @return The number of threads.
     */
    public int getNumThreads() {
        return this.numThreads;
    }

    /**
     * The number of busy threads.
     *
     * @return The number of busy threads.
     */
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
     * @param serverTime the absolute time in which the petition enters at the system.
     * @throws Exception Fails if are the threads are busy.
     */
    public void processPetition(ArrivalEvent arrivalEvent, float serverTime) throws Exception {
        if (this.busyThreads() == this.numThreads) {
            throw new Exception("Error: all the threads are busy");
        } else {
            for (int i = 0; i < this.numThreads; i++) {
                if (!this.threads[i].isBusy()) {
                    this.threads[i].asignPetition(arrivalEvent, serverTime);
                }
            }
        }
    }
}
