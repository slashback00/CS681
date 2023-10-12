package hw02;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

enum AgeCat {
    OLD, MID, YOUNG
}

class Dose {
    private String vacProductName;
    private String lotNumber;
    private LocalDate date;
    private String vacSite;

    public Dose(String vacProductName, String lotNumber, LocalDate date, String vacSite) {
        this.vacProductName = vacProductName;
        this.lotNumber = lotNumber;
        this.date = date;
        this.vacSite = vacSite;
    }

    
}

class Person {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LinkedList<Dose> doses;

    public Person(String firstName, String lastName, LocalDate dob, LinkedList<Dose> doses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.doses = doses;
    }

    public int getAge() {
        return LocalDate.now().getYear() - dob.getYear();
    }

    public AgeCat getAgeCat() {
        int age = getAge();
        if (age >= 60) return AgeCat.OLD;
        if (age >= 30) return AgeCat.MID;
        return AgeCat.YOUNG;
    }

    public LinkedList<Dose> getDoses() {
        return doses;
    }

    public int getVacCount() {
        return doses.size();
    }

    public boolean isFullyVaccinated() {
        return getAge() >= 18 && getVacCount() >= 3;
    }
}

public class Vaccination {
    public static void main(String[] args) {
        LinkedList<Person> people = generateRandomPeople(1000);

        // Calculate the vaccination rate for 18+ year-olds
        long fullyVacCount = people.stream()
                .filter(Person::isFullyVaccinated)
                .count();
        float vacRate = (float) fullyVacCount / people.size() * 100;

        // Calculate the vaccination rate for each age category
        Map<AgeCat, Double> avgVacRateByAgeCat = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAgeCat,
                        Collectors.averagingDouble(p -> p.isFullyVaccinated() ? 1.0 : 0.0)
                ));

        
        for (AgeCat ageCat : AgeCat.values()) {
            avgVacRateByAgeCat.putIfAbsent(ageCat, 0.0);
        }

        // Calculate the average number of vaccinations administered in each age category
        Map<AgeCat, Double> avgNumOfVaccinationsByAgeCat = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAgeCat,
                        Collectors.averagingDouble(Person::getVacCount)
                ));

        
        for (AgeCat ageCat : AgeCat.values()) {
            avgNumOfVaccinationsByAgeCat.putIfAbsent(ageCat, 0.0);
        }

        // Calculate the average age of people who have never been vaccinated
        double avgAgeUnvaccinated = people.stream()
                .filter(p -> p.getVacCount() == 0)
                .collect(Collectors.averagingDouble(Person::getAge));

        System.out.println("Vaccination rates by age category: " + avgVacRateByAgeCat);
        System.out.println("Average number of doses by age category: " + avgNumOfVaccinationsByAgeCat);
        System.out.println("Average age of unvaccinated people: " + avgAgeUnvaccinated);        
    }

    private static LinkedList<Person> generateRandomPeople(int count) {
        LinkedList<Person> people = new LinkedList<>();
        Random random = new Random();
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Wilson", "Lee", "Garcia"};

        for (int i = 0; i < count; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            LocalDate dob = LocalDate.of(1960 + random.nextInt(30), random.nextInt(12) + 1, random.nextInt(28) + 1);

            LinkedList<Dose> doses = new LinkedList<>();
            int numDoses = random.nextInt(5);
            for (int j = 0; j < numDoses; j++) {
                doses.add(new Dose("Vaccine" + j, "Lot" + j, LocalDate.now().minusDays(random.nextInt(365)), "Site" + j));
            }

            Person person = new Person(firstName, lastName, dob, doses);
            people.add(person);
        }

        return people;
    }
}

