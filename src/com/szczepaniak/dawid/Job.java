package com.szczepaniak.dawid;

public class Job {
    private int index;
    private Integer[] machinesTime;
    private int weight;

    public Job(int index, Integer[] machinesTime){
        this.index = index;
        this.machinesTime = machinesTime;
        for(int i = 0; i < machinesTime.length; i++){
            weight +=machinesTime[i];
        }
    }

    public int getIndex() {
        return index;
    }

    public Integer[] getMachinesTime() {
        return machinesTime;
    }

    public int getWeight() {
        return weight;
    }

    public String toString(){
        String result = "Index: " + index + " | ";
        for(int i : machinesTime){
            result = result + i + " ";
        }
        return result + "| " + weight;
    }

    public int getMachinesCount(){
        return machinesTime.length;
    }
}
