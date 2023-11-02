package hw06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shooting {

    public static void main(String[] args) {
        Path path = Paths.get("Shooting.csv");

        List<List<String>>[] matrix = new List[1];
        matrix[0] = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            matrix[0] = lines.skip(1)  // Skip the header line
                    .map(line -> Arrays.asList(line.split(",")))  // Split each line into a list of fields
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create Runnable objects for data processing tasks
        Runnable dataProcessing1 = () -> {
            // Count shootings Fatality
            Map<String, Long> shootingsFatality = new HashMap<>();
            matrix[0].stream()
                    .filter(record -> record.size() > 3)
                    .map(record -> record.get(3))
                    .collect(Collectors.groupingBy(
                            fatality -> fatality,
                            Collectors.counting()
                    ))
                    .forEach((key, value) -> shootingsFatality.put(key, value));
            System.out.println("Shootings Fatality: " + shootingsFatality);
        };

        Runnable dataProcessing2 = () -> {
            // Count shootings per race
            Map<String, Long> shootingsPerRace = new HashMap<>();
            matrix[0].stream()
                    .filter(record -> record.size() > 5)
                    .map(record -> record.get(5))
                    .collect(Collectors.groupingBy(
                            race -> race,
                            Collectors.counting()
                    ))
                    .forEach((key, value) -> shootingsPerRace.put(key, value));
            System.out.println("Shootings Per Race: " + shootingsPerRace);
        };

        Runnable dataProcessing3 = () -> {
            // Number of victim from same district
            Map<String, Long> shootingsdistrict = new HashMap<>();
            matrix[0].stream()
                    .filter(record -> record.size() > 1)
                    .map(record -> record.get(2))
                    .collect(Collectors.groupingBy(
                            district -> district,
                            Collectors.counting()
                    ))
                    .forEach((key, value) -> shootingsdistrict.put(key, value));
            System.out.println("Number of victim from same district: " + shootingsdistrict);
        };

        // Create threads for data processing tasks
        Thread thread1 = new Thread(dataProcessing1);
        Thread thread2 = new Thread(dataProcessing2);
        Thread thread3 = new Thread(dataProcessing3);

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to finish using join()
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
