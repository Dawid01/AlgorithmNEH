package com.szczepaniak.dawid;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("NEH");
        System.out.println("=====================================\n\n");

        for(int i = 0; i < 120; i++){
            String tail = formatNumber(i);
            String dir = "src/data_" + tail + ".txt";
            DataNEH dataNEH = FileReader.readFile(dir, " ");
            System.out.println("NEH " + dir.replace("src/", ""));
            ResultNEH resultNEH = NEH.getResult(dataNEH.getJobs(), NEH.NEH);
            assert resultNEH != null;
            System.out.println(resultNEH);
            System.out.println(resultNEH.getJobsTime() == dataNEH.getCmax() ? "OK" : "NOT OK" + "  " + resultNEH.getJobsTime() + "  vs  " + dataNEH.getCmax());
        }

        System.out.println("QNEH");
        System.out.println("=====================================\n\n");

        for(int i = 0; i <= 120; i++){
            String tail = formatNumber(i);
            String dir = "src/data_" + tail + ".txt";
            DataNEH dataNEH = FileReader.readFile(dir, " ");
            System.out.println("QNEH " + dir.replace("src/", ""));
            ResultNEH resultNEH = NEH.getResult(dataNEH.getJobs(), NEH.QNEH);
            assert resultNEH != null;
            System.out.println(resultNEH);
            System.out.println(resultNEH.getJobsTime() == dataNEH.getCmax() ? "OK" : "NOT OK" + "  " + resultNEH.getJobsTime() + "  vs  " + dataNEH.getCmax());

        }

    }

    private static String formatNumber(int number) {
        String numberStr = "" + number;
        while (numberStr.length() < 3) {
            numberStr = "0" + numberStr;
        }
        return numberStr;
    }
}
