package server;

import event.ArrivalEvent;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author inigo
 */
public class ServerQueue {

    private final TreeMap<Double, ArrivalEvent> list;
    private static int SIZE;

    public ServerQueue(int size) {
        this.list = new TreeMap<Double, ArrivalEvent>();
        ServerQueue.SIZE = size;
    }

    public void addEvent(ArrivalEvent ev) throws Exception {
        if (!this.isFull()) {
            Double key = ev.getArrivalTime();
            list.put(key, ev);
        } else {
            throw new Exception("Error: la cola está llena");
        }
    }

    public boolean isFull() {
        return list.size() == SIZE;
    }

    /**
     * The number of event in the queue
     *
     * @return The number of events in the queue.
     */
    public int eventsInQueue() {
        return list.size();
    }

    public void delEvent(ArrivalEvent ev) {
        Double key = ev.getArrivalTime();
        list.remove(key);
    }

    public void delEvent(Double key) {
        list.remove(key);
    }

    public void delEvent(double key) {
        list.remove(key);
    }

    public ArrivalEvent popNextEvent() {
        Double key = list.firstKey();
        ArrivalEvent e = list.get(key);
        delEvent(key);
        return e;
    }

    public ArrivalEvent getEvent(Double key) {
        return list.get(key);
    }

    public ArrivalEvent getEvent(double key) {
        return list.get(key);
    }

    public ArrayList<ArrivalEvent> toArrayList() {
        return new ArrayList<ArrivalEvent>(list.values());
    }
}
