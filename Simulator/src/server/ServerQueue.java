package server;

import event.ArrivalEvent;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author inigo
 */
public class ServerQueue {

    private final TreeMap<Float, ArrivalEvent> list;
    private static int SIZE;

    public ServerQueue(int size) {
        this.list = new TreeMap<Float, ArrivalEvent>();
        ServerQueue.SIZE = size;
    }

    public void addEvent(ArrivalEvent ev) throws Exception {
        if (!this.isFull()) {
            Float key = ev.getArrivalTime();
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
        Float key = ev.getArrivalTime();
        list.remove(key);
    }

    public void delEvent(Float key) {
        list.remove(key);
    }

    public void delEvent(float key) {
        list.remove(key);
    }

    public ArrivalEvent popNextEvent() {
        Float key = list.firstKey();
        ArrivalEvent e = list.get(key);
        delEvent(key);
        return e;
    }

    public ArrivalEvent getEvent(Float key) {
        return list.get(key);
    }

    public ArrivalEvent getEvent(float key) {
        return list.get(key);
    }

    public ArrayList<ArrivalEvent> toArrayList() {
        return new ArrayList<ArrivalEvent>(list.values());
    }
}
