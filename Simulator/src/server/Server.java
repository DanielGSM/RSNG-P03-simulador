package server;

import event.ArrivalEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ThreadsList;

/**
 * A server with threads and a queue.
 *
 * The petitions in the queue are ordered by their arrival time; the one with
 * the lowest one will be the first to enter in the system when a thread becomes
 * empty.
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

    /**
     *
     * @param numThreads The number of threads in the server.
     * @param queueSize The size of the waiting queue.
     * @throws Exception Throws an exception when the number of threads is
     * lesser than one.
     */
    public Server(int numThreads, int queueSize) throws Exception {
        this.numThreads = numThreads;
        this.queueSize = queueSize;

        this.threads = new ThreadsList(numThreads);
        this.queue = new ServerQueue(queueSize);
    }

    /**
     * Simulates a petition arriving to the server.
     *
     * The petition goes to the server queue if all the threads are busy. If the
     * queue is empty and there are
     *
     * @param arrivalEvent
     */
    public void petitionArrival(ArrivalEvent arrivalEvent) {
        if (this.threads.busyThreads() < this.numThreads) {
            try {
                this.threads.processPetition(arrivalEvent, arrivalEvent.getArrivalTime());
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            assert this.threads.busyThreads() == this.numThreads;

            if (!this.queue.isFull()) {
                try {
                    this.enqueuePetition(arrivalEvent);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Enqueues a petition in the server's queue.
     *
     * All the threads must be busy.
     *
     * @param arrivalEvent The petition to enqueue.
     */
    private void enqueuePetition(ArrivalEvent arrivalEvent) {
        assert !this.queue.isFull() && this.threads.busyThreads() == numThreads;

        try {
            this.queue.addEvent(arrivalEvent);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
