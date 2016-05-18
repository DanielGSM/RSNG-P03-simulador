/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import event.ArrivalEvent;
import java.util.ArrayList;
import java.util.Map;
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

    public boolean addEvent(ArrivalEvent ev) {
        if(list.size() <= SIZE) {
            Float key = ev.getArrivalTime();
            list.put(key, ev);
            return true;
        }
        else
            return false;
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