package thread;

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

            threads = new Thread[numThreads];
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread();
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
            if (threads[i].nextIdleTime() < minTime) {
                minTime = threads[i].nextIdleTime();
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
}
