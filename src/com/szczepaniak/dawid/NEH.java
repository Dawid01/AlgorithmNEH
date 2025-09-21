package com.szczepaniak.dawid;

import java.util.*;

public class NEH {

    static final int NEH = 0;
    static final int QNEH = 1;

    static ResultNEH getResult(List<Job> data, final int method){
       switch (method){
           case NEH:
               return getResultNEH(data);
           case QNEH:
               return getResultQNEH(data);
       }
       return null;
    }

    private static ResultNEH getResultNEH(List<Job> data) {
        long startTime = System.currentTimeMillis();
        int cmax = 0;
        List<Job> result = new ArrayList<>();
        data.sort((j1, j2) -> j2.getWeight() - j1.getWeight());

        for (int i = 0; i < data.size(); i++) {
            Job nextJob = data.get(i);
            int minCmax = Integer.MAX_VALUE;
            int bestIndex = 0;

            for (int j = 0; j <= result.size(); j++) {
                result.add(j, nextJob);
                int currentCmax = calculateCMAX(result);

                if (currentCmax < minCmax) {
                    minCmax = currentCmax;
                    bestIndex = j;
                }

                result.remove(j);
            }

            result.add(bestIndex, nextJob);
        }

        cmax = calculateCMAX(result);

        long endTime = System.currentTimeMillis();

        return new ResultNEH(cmax, result, endTime - startTime);
    }

//    private static ResultNEH getResultQNEH(List<Job> data) {
//        long startTime = System.currentTimeMillis();
//        int cmax = 0;
//        List<Job> result = new ArrayList<>();
//        data.sort((j1, j2) -> j2.getWeight() - j1.getWeight());
//        //result.add(data.get(0));
//
//        for (int i = 0; i < data.size(); i++) {
//            Job nextJob = data.get(i);
//            int minCmax = Integer.MAX_VALUE;
//            int bestIndex = 0;
//
//            List<int[]> left = calculateCMAXs(result, true);
//            List<int[]> right = calculateCMAXs(result, false);
//
//            for (int j = 0; j < result.size(); j++) {
//
//                int m = nextJob.getMachinesCount();
//                int[] tabL = (j > 0) ? left.get(j) : new int[m];
//                int[] tabR = (j < result.size() - 1) ? right.get(j + 1) : new int[m];
//                int[] helpTab = new int[m];
//                //int[] tabR = right.get(j);
//                int max = 0;
//                int upR = 0;
//                int upL = 0;
//
//                if(j > 0) {
//                    for (int x = 0; x < m; x++) {
//                        int l = Math.max(tabL[x], upR) + nextJob.getMachinesTime()[x];
//                        upR = l;
//                        helpTab[x] = tabR[x] + l;
//                        max = Math.max(helpTab[x], max);
//                    }
//                }
//
//                if(j < result.size() - 1) {
//                    for (int x = m - 1; x >= 0; x--) {
//                        int r = Math.max(tabR[x], upL) + nextJob.getMachinesTime()[x];
//                        upL = r;
//                        max = Math.max(tabL[x] + r, max);
//                    }
//                }
//
//
//                if (max < minCmax) {
//                    minCmax = max;
//                    bestIndex = j;
//                }
//            }
//
//            if (i == data.size() - 1) {
//                cmax = minCmax;
//            }
//
//            result.add(bestIndex, nextJob);
//        }
//        long endTime = System.currentTimeMillis();
//        return new ResultNEH(calculateCMAX(result), result, endTime - startTime);
//    }

    private static ResultNEH getResultQNEH(List<Job> data) {
        long startTime = System.currentTimeMillis();

        data.sort(Comparator.comparingInt(Job::getWeight).reversed());
        List<Job> result = new ArrayList<>();

        for (Job nextJob : data) {
            int minCmax = Integer.MAX_VALUE;
            int bestIndex = 0;

            List<int[]> left = calculateCMAXs(result, true);
            List<int[]> right = calculateCMAXs(result, false);

            for (int j = 0; j <= result.size(); j++) {
                int m = nextJob.getMachinesCount();
                int[] tabL = (j > 0) ? left.get(j - 1) : new int[m];
                int[] tabR = (j < result.size()) ? right.get(j) : new int[m];
                int[] helpTab = new int[m];
                int max = 0;
                int upR = 0;
                int upL = 0;

                for (int x = 0; x < m; x++) {
                    int l = Math.max(tabL[x], upR) + nextJob.getMachinesTime()[x];
                    upR = l;
                    helpTab[x] = tabR[x] + l;
                    max = Math.max(helpTab[x], max);
                }

                if (max < minCmax) {
                    minCmax = max;
                    bestIndex = j;
                }
            }

            result.add(bestIndex, nextJob);
        }

        int cmax = calculateCMAX(result);
        long endTime = System.currentTimeMillis();
        return new ResultNEH(cmax, result, endTime - startTime);
    }

    private static int calculateCMAX(List<Job> temp) {
        int mSize = temp.get(0).getMachinesCount();
        int result = 0;
        int[] tUp = new int[temp.size()];
        int[] tLeft = new int[mSize];

        for (int j = 0; j < temp.size(); j++) {
            for (int m = 0; m < mSize; m++) {
                int t = temp.get(j).getMachinesTime()[m];
                int t1 = tLeft[m] + t;
                int t2 = tUp[j] + t;
                int max = Math.max(t1, t2);
                tLeft[m] = tUp[j] = max;
                result = max;
            }
        }
        return result;
    }

    private static List<int[]> calculateCMAXs(List<Job> temp, boolean fromLeft) {
        List<int[]> result = new ArrayList<>();
        if(temp.size() > 0) {
            int mSize = temp.get(0).getMachinesCount();
            int[] tUp = new int[temp.size()];
            int[] tLeft = new int[mSize];

            if (fromLeft) {
                for (int j = 0; j < temp.size(); j++) {
                    int[] info = new int[mSize];
                    for (int m = 0; m < mSize; m++) {
                        int t = temp.get(j).getMachinesTime()[m];
                        int t1 = tLeft[m] + t;
                        int t2 = tUp[j] + t;
                        int max = Math.max(t1, t2);
                        tLeft[m] = tUp[j] = max;
                        info[m] = max;
                    }
                    result.add(info);
                }
            } else {
                for (int j = temp.size() - 1; j >= 0; j--) {
                    int[] info = new int[mSize];
                    for (int m = mSize - 1; m >= 0; m--) {
                        int t = temp.get(j).getMachinesTime()[m];
                        int t1 = tLeft[m] + t;
                        int t2 = tUp[j] + t;
                        int max = Math.max(t1, t2);
                        tLeft[m] = tUp[j] = max;
                        info[m] = max;
                    }
                    result.add(0, info);
                }
            }
        }
        return result;
    }
}
