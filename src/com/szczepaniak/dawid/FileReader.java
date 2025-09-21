package com.szczepaniak.dawid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static DataNEH readFile(String fileDir, String delimiter) throws FileNotFoundException {
        File file = new File(fileDir);
        Scanner scanner = new Scanner(file);

        List<Job> tasks = new ArrayList<>();
        int index = 0;
        int tasksCount, machinesCount;

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            tasksCount = lineScanner.nextInt();
            machinesCount = lineScanner.nextInt();
            lineScanner.close();
        } else {
            scanner.close();
            throw new RuntimeException("File format incorrect: no data for tasksCount and machinesCount.");
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.contains("neh:")) {
                break;
            }

            String[] tokens = line.split(delimiter);
            Integer[] values = new Integer[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                values[i] = Integer.parseInt(tokens[i]);
            }
            tasks.add(new Job(index, values));
            index++;
        }

        int optimalCMax = 0;
        if (scanner.hasNextInt()) {
            optimalCMax = scanner.nextInt();
        }

        List<Integer> optimalSolution = new ArrayList<>();
        while (scanner.hasNextInt()) {
            optimalSolution.add(scanner.nextInt());
        }

        scanner.close();

        return new DataNEH(tasks, optimalCMax);
    }
}