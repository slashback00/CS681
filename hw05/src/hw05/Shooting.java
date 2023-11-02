package hw05;

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

        List<List<String>> matrix = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            matrix = lines.skip(1)  // Skip the header line
                    .map(line -> Arrays.asList(line.split(",")))  // Split each line into a list of fields
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Count shootings Fatality
        Map<String, Long> shootingsFatality = new HashMap<>();
            matrix.stream()
                .filter(record -> record.size() > 3)
                .map(record -> record.get(3))
                .collect(Collectors.groupingBy(
                    fatality -> fatality,
                    Collectors.counting()
                ))
                .forEach((key, value) -> shootingsFatality.put(key, value));

        // Count shootings per race
        Map<String, Long> shootingsPerRace = new HashMap<>();
        matrix.stream()
              .filter(record -> record.size() > 5)
              .map(record -> record.get(5))
              .collect(Collectors.groupingBy(
                  race -> race,
                  Collectors.counting()
              ))
              .forEach((key, value) -> shootingsPerRace.put(key, value));

        // Most common time
        Map<String, Long> shootingsTimeMap = new HashMap<>();
        matrix.stream()
              .filter(record -> record.size() > 1)
              .map(record -> record.get(1))
              .collect(Collectors.groupingBy(
                  time -> time,
                  Collectors.counting()
              ))
              .forEach((key, value) -> shootingsTimeMap.put(key, value));

        String mostCommonTime = shootingsTimeMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        System.out.println(shootingsFatality);
        System.out.println(mostCommonTime);
        System.out.println(shootingsPerRace);
    }
}





