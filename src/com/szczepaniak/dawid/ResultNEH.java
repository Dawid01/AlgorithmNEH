package com.szczepaniak.dawid;

import java.util.List;

public class ResultNEH {

    private int jobsTime;
    private List<Job> resultJobs;
    private long time;

    public  ResultNEH(){}

    public ResultNEH(int jobsTime, List<Job> resultJobs, long time){
        this.jobsTime = jobsTime;
        this.resultJobs = resultJobs;
        this.time = time;
    }

    public int getJobsTime() {
        return jobsTime;
    }

    public List<Job> getJobsIndex() {
        return resultJobs;
    }

    public long getTime() {
        return time;
    }
    
    public String toString(){
        StringBuilder result = new StringBuilder("CMax: " + jobsTime + "\n Kolejność prac: ");
        for(Job job : resultJobs){
            result.append(job.getIndex()).append(" ");
        }
        return result + "\n Czas algorytmu: " + time + "ms";
    }
}
