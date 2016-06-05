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
        if (this.queue.isFull() && this.threads.busyThreads() == this.numThreads) {
            //the petition is denied
            OutputEvent outputEvent = new OutputEvent(arrivalEvent, false, null, null);
            //TODO: quitar sout
            System.out.println(outputEvent);
            eventsWriter.writeEvent(outputEvent);
        } else if (!this.queue.isFull() && this.threads.busyThreads() == this.numThreads) {
            //the petition is enqueued
            this.enqueuePetition(arrivalEvent);
        } else {
            //the petition enthers directly in a thread
            assert this.threads.busyThreads() < this.numThreads && this.queue.eventsInQueue() == 0;
            this.threads.AssignPetition(arrivalEvent, arrivalEvent.getArrivalTime());
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
     * @throws java.lang.Exception when there are no busy threads
     */
    public float nextOutTime() throws Exception {
        return this.threads.nextOutTime();
    }

    /**
     * Processes the internal events until reaching the given time.
     *
     * @param time The time desired for the server to advance to.
     */
    public void advanceClock(float time) {
        try {
            OutputEvent out;

            //we process petitions that have to happen before the given time
            while (this.threads.busyThreads() > 0 && this.nextOutTime() <= time) {
                out = this.threads.advance();
                System.out.println(out);
                eventsWriter.writeEvent(out);

                if (this.queue.eventsInQueue() > 0) {
                    //the next petition in queue enters in a thread just after a petition finishes being served
                    this.threads.AssignPetition(this.queue.popNextEvent(), out.getOutTime());
                }
            }
        } catch (Exception ex) {
            //should never reach this since, if there are busy threads, nextOutTime shouldn't throw an exception
        }
    }
}
