package com.szczepaniak.dawid;

import java.util.List;

public class DataNEH {

    private List<Job> jobs;
    private int cmax;

    public DataNEH(List<Job> jobs, int cmax) {
        this.jobs = jobs;
        this.cmax = cmax;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public int getCmax() {
        return cmax;
    }
}
