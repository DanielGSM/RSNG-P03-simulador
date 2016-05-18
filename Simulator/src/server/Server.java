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
    private int numThreads;
    /**
     * the size of the server's queue
     */
    private int queueSize;
    /**
     * The server's threads
     */
    private ThreadsList threads;
    private ServerQueue queue;

    public Server(int numThreads, int queueSize) throws Exception {
        this.numThreads = numThreads;
        this.queueSize = queueSize;

        this.threads = new ThreadsList(numThreads);
        this.queue = new ServerQueue(queueSize);
    }
}
