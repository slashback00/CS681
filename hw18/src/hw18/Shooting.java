package hw18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Shooting {

    public static void main(String[] args) {
        Path path = Paths.get("Shooting.csv");

        try {
            List<List<String>> matrix = Files.lines(path)
                    .skip(1) 
                    .map(line -> Arrays.asList(line.split(","))) 
                    .collect(Collectors.toList());

            ForkJoinPool customThreadPool = new ForkJoinPool(4);
            try {
                customThreadPool.submit(() -> {
                    AtomicLong totalFatalities = new AtomicLong();
                    ConcurrentHashMap<String, Long> shootingsPerRace = new ConcurrentHashMap<>();
                    ConcurrentHashMap<String, Long> shootingsDistrict = new ConcurrentHashMap<>();

                    matrix.parallelStream().forEach(record -> {
                        if (record.size() > 5) {
                            shootingsPerRace.compute(record.get(5), (key, val) -> val == null ? 1 : val + 1);
                        }
                        if (record.size() > 3 && "Fatal".equals(record.get(3))) {
                            totalFatalities.incrementAndGet();
                        }
                        if (record.size() > 2) {
                            shootingsDistrict.compute(record.get(2), (key, val) -> val == null ? 1 : val + 1);
                        }
                    });

                    System.out.println("Total Fatalities: " + totalFatalities.get());
                    System.out.println("Shootings Per Race: " + shootingsPerRace);
                    System.out.println("Number of victims from same district: " + shootingsDistrict);
                }).get();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                customThreadPool.shutdown();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
