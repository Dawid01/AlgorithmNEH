package com.szczepaniak.dawid;

public class TempSwapInfo {
    private int index, c;
    private boolean front;

    public TempSwapInfo(int index, int c, boolean front) {
        this.index = index;
        this.c = c;
        this.front = front;
    }

    public int getIndex() {
        return index;
    }

    public int getC() {
        return c;
    }

    public boolean isFront() {
        return front;
    }
}
