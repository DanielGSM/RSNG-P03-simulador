package server;

import event.ArrivalEvent;
import event.EventsWriter;
import event.OutputEvent;
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

    private float clock;

    EventsWriter eventsWriter;

    /**
     *
     * @param numThreads The number of threads in the server.
     * @param queueSize The size of the waiting queue.
     * @param eventsWriter The events writer
     * @throws Exception Throws an exception when the number of threads is
     * lesser than one.
     */
    public Server(int numThreads, int queueSize, EventsWriter eventsWriter) throws Exception {
        this.numThreads = numThreads;
        this.queueSize = queueSize;

        this.threads = new ThreadsList(numThreads);
        this.queue = new ServerQueue(queueSize);

        this.eventsWriter = eventsWriter;

        this.clock = 0;
    }

    /**
     * Simulates a petition arriving to the server.
     *
     * The petition goes to the server queue if all the threads are busy. If the
     * queue is empty and there are
     *
     * @param arrivalEvent
     * @throws java.lang.Exception
     */
    public void petitionArrival(ArrivalEvent arrivalEvent) throws Exception {
        if (this.queue.isFull()) {
            //the petition is denied
            OutputEvent outputEvent = new OutputEvent(arrivalEvent, false, null, null);
        } else if ((this.queue.eventsInQueue() > 0) || (this.threads.busyThreads() == this.numThreads)) {
            //the petition is enqueued
            this.enqueuePetition(arrivalEvent);
        } else {
            //the petition enthers directly in a thread
            assert !this.queue.isFull() && this.queue.eventsInQueue() == 0 && this.threads.busyThreads() < this.numThreads;
            this.threads.processPetition(arrivalEvent, arrivalEvent.getArrivalTime());
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

    /**
     * The number of petitions in the server (queue+threads)
     *
     * @return
     */
    public int petitionsInServer() {
        return this.queue.eventsInQueue() + this.threads.busyThreads();
    }

    /**
     * Between all the petitions being serverd, the absolute time of the output
     * time of the first petition which will finish being served.
     *
     * @return
     */
    public float nextOutTime() {
        return this.threads.nextOutTime();
    }

    /**
     * Processes the internal petitions until reaching the time.
     */
    public void advanceClock(float time) {
        if (this.clock < time) {
            //TODO: procesar peticiones
            
            
            this.clock = time;
        }
    }
}
