package server;

import thread.ThreadsList;

/**
 * A server with threads
 *
 * @author Daniel
 */
public class Server {

    /**
     * The number of threads in the server
     */
    private final int numThreads;
    /**
     * the size of the server's queue
     */
    private final int queueSize;
    /**
     * The server's threads
     */
    private final ThreadsList threads;
    /**
     * The server's queue
     */
    private final ServerQueue queue;

    public Server(int numThreads, int queueSize) throws Exception {
        this.numThreads = numThreads;
        this.queueSize = queueSize;

        this.threads = new ThreadsList(numThreads);
        this.queue = new ServerQueue(queueSize);
    }
}
