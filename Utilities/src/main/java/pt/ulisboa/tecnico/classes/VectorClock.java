package pt.ulisboa.tecnico.classes;

import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class VectorClock {
    private HashMap<Integer, Integer> clock;

    public VectorClock() {
        this.clock = new HashMap<Integer, Integer>();
    }

    public VectorClock(int serverId) {
        this.clock = new HashMap<Integer, Integer>();
        this.clock.put(serverId, 0);
    }

    public HashMap<Integer, Integer> getClock() {
        return clock;
    }

    public Integer get(int pos) {
        return this.clock.get(pos);
    }

    public int size() {
        return this.clock.size();
    }

    public void setClock(int key, int value) {
        if (this.clock.containsKey(key)) {
            this.clock.replace(key, value);
        }
        else {
            this.clock.put(key, value);
        }
    }

    public void resize(List<Integer> serverList) { // called when executes lookup
        for (int serverId : serverList) { // case when a server has been created
            if (clock.containsKey(serverId)) {
                continue;
            }

            else {
                clock.put(serverId, 0);
            }
        }

        for (Map.Entry<Integer, Integer> entry : clock.entrySet()) { // case when a server has been deleted
            if (serverList.contains(entry.getKey())) {
                continue;
            }

            else {
                clock.remove(entry.getKey());
            }
        }
    }

    public void increment(int id) {
        this.clock.replace(id, this.clock.get(id) + 1);
    }

    public boolean happenedBefore(VectorClock vectorClock) { //called when receives a request from a client
        if (vectorClock == null || vectorClock.getClock() == null) {
            return true;
        }

        for (Map.Entry<Integer, Integer> entry : vectorClock.getClock().entrySet()) { // case when a server has been created
            if (!clock.containsKey(entry.getKey())) {
                clock.put(entry.getKey(), 0);
            }
        }

        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : clock.entrySet()) { // case when a server has been deleted
            if (!vectorClock.getClock().containsKey(entry.getKey())) {
                toRemove.add(entry.getKey());
            }
        }

        for (Integer key : toRemove) {
            clock.remove(key);
        }

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) == null || vectorClock.get(i) == null) {
                return false;
            }

            if (this.get(i) > vectorClock.get(i)) {
                return false;
            }
        }

        return true;
    }

    public void update(VectorClock vectorClock) {
        for (Map.Entry<Integer, Integer> entry : vectorClock.clock.entrySet()) {
            if (this.clock.containsKey(entry.getKey())) {
                this.clock.replace(entry.getKey(), Math.max(this.clock.get(entry.getKey()), entry.getValue()));
            }

            else {
                this.clock.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public ArrayList<ClassesDefinitions.VectorClockMessage> proto() {
        ArrayList<ClassesDefinitions.VectorClockMessage> vectorClock = new ArrayList<ClassesDefinitions.VectorClockMessage>();

        for (Entry<Integer, Integer> entry : this.clock.entrySet()) {
            ClassesDefinitions.VectorClockMessage msg = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getKey())
                    .setValue(entry.getValue())
                    .build();

            vectorClock.add(msg);
        }

        return vectorClock;
    }

    public String toString() {
        return this.clock.toString();
    }
}
