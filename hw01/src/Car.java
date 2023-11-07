package hw01;

import java.util.*;
import java.util.stream.Collectors;

public class Car {
    private String make;
    private String model;
    private int mileage;
    private int year;
    private float price;
    private int dominationCount;

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setDominationCount(List<Car> cars) {
        this.dominationCount = (int) cars.stream()
                .filter(car -> car.getYear() >= this.getYear() && car.getMileage() <= this.getMileage() && car.getPrice() <= this.getPrice())
                .filter(car -> car.getYear() > this.getYear() || car.getMileage() < this.getMileage() || car.getPrice() < this.getPrice())
                .count();
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                ", year=" + year +
                ", price=" + price +
                ", dominationCount=" + dominationCount +
                '}';
    }

    public static void main(String[] args) {
        Car c1 = new Car("Honda", "Odessey", 18, 2020, 132225);
        Car c2 = new Car("Audi", "Q8", 14, 2021, 155590);
        Car c3 = new Car("Tesla", "X", 20, 2023, 350000);
        Car c4 = new Car("Toyota", "Camry", 22, 2022, 25000);
        Car c5 = new Car("Ford", "Mustang", 10, 2023, 50000);
        Car c6 = new Car("Chevrolet", "Malibu", 15, 2021, 20000);

        List<Car> cars = Arrays.asList(c1, c2, c3, c4, c5, c6);

        cars.forEach(car -> car.setDominationCount(new LinkedList<>(cars)));

        int mileageThreshold = 15;
        float priceThreshold = 150000;
        int yearThreshold = 2022;
        int dominationCountThreshold = 2;

            List<Car> highMileageCars = cars.stream()
                .filter(car -> car.getMileage() >= mileageThreshold)
                .collect(Collectors.toList());

            List<Car> lowMileageCars = cars.stream()
                .filter(car -> car.getMileage() < mileageThreshold)
                .collect(Collectors.toList());

            List<Car> highPriceCars = cars.stream()
                .filter(car -> car.getPrice() >= priceThreshold)
                .collect(Collectors.toList());

            List<Car> lowPriceCars = cars.stream()
                .filter(car -> car.getPrice() < priceThreshold)
                .collect(Collectors.toList());

            List<Car> recentCars = cars.stream()
                .filter(car -> car.getYear() >= yearThreshold)
                .collect(Collectors.toList());

            List<Car> oldCars = cars.stream()
                .filter(car -> car.getYear() < yearThreshold)
                .collect(Collectors.toList());

            List<Car> highDominationCountCars = cars.stream()
                .filter(car -> car.getDominationCount() >= dominationCountThreshold)
                .collect(Collectors.toList());

            List<Car> lowDominationCountCars = cars.stream()
                .filter(car -> car.getDominationCount() < dominationCountThreshold)
                .collect(Collectors.toList());

               float highMileageAverage = calculateAverage(highMileageCars, "mileage");
               float lowMileageAverage = calculateAverage(lowMileageCars, "mileage");
               float highPriceAverage = calculateAverage(highPriceCars, "price");
               float lowPriceAverage = calculateAverage(lowPriceCars, "price");
               float recentYearAverage = calculateAverage(recentCars, "year");
               float oldYearAverage = calculateAverage(oldCars, "year");
               int highDominationCountTotal = calculateTotal(highDominationCountCars, "dominationCount");
               int lowDominationCountTotal = calculateTotal(lowDominationCountCars, "dominationCount");

        System.out.println("High Mileage Cars: ");
        highMileageCars.forEach(car -> System.out.println(car));
        System.out.println("Average Mileage in High Mileage Cars: " + highMileageAverage);
        System.out.println("Highest Mileage in High Mileage Cars: " + calculateMax(highMileageCars, "mileage"));
        System.out.println("Lowest Mileage in High Mileage Cars: " + calculateMin(highMileageCars, "mileage"));

        System.out.println("Low Mileage Cars: ");
        lowMileageCars.forEach(car -> System.out.println(car));
        System.out.println("Average Mileage in Low Mileage Cars: " + lowMileageAverage);
        System.out.println("Highest Mileage in Low Mileage Cars: " + calculateMax(lowMileageCars, "mileage"));
        System.out.println("Lowest Mileage in Low Mileage Cars: " + calculateMin(lowMileageCars, "mileage"));

        System.out.println("High Price Cars: ");
        highPriceCars.forEach(car -> System.out.println(car));
        System.out.println("Average Price in High Price Cars: " + highPriceAverage);
        System.out.println("Highest Price in High Price Cars: " + calculateMax(highPriceCars, "price"));
        System.out.println("Lowest Price in High Price Cars: " + calculateMin(highPriceCars, "price"));

        System.out.println("Low Price Cars: ");
        lowPriceCars.forEach(car -> System.out.println(car));
        System.out.println("Average Price in Low Price Cars: " + lowPriceAverage);
        System.out.println("Highest Price in Low Price Cars: " + calculateMax(lowPriceCars, "price"));
        System.out.println("Lowest Price in Low Price Cars: " + calculateMin(lowPriceCars, "price"));

        System.out.println("Recent Cars: ");
        recentCars.forEach(car -> System.out.println(car));
        System.out.println("Average Year in Recent Cars: " + recentYearAverage);
        System.out.println("Highest Year in Recent Cars: " + calculateMax(recentCars, "year"));
        System.out.println("Lowest Year in Recent Cars: " + calculateMin(recentCars, "year"));

        System.out.println("Old Cars: ");
        oldCars.forEach(car -> System.out.println(car));
        System.out.println("Average Year in Old Cars: " + oldYearAverage);
        System.out.println("Highest Year in Old Cars: " + calculateMax(oldCars, "year"));
        System.out.println("Lowest Year in Old Cars: " + calculateMin(oldCars, "year"));

        System.out.println("High Domination Count Cars: ");
        highDominationCountCars.forEach(car -> System.out.println(car));
        System.out.println("Total Domination Count in High Domination Count Cars: " + highDominationCountTotal);
        System.out.println("Highest Domination Count in High Domination Count Cars: " + calculateMax(highDominationCountCars, "dominationCount"));
        System.out.println("Lowest Domination Count in High Domination Count Cars: " + calculateMin(highDominationCountCars, "dominationCount"));

        System.out.println("Low Domination Count Cars: ");
        lowDominationCountCars.forEach(car -> System.out.println(car));
        System.out.println("Total Domination Count in Low Domination Count Cars: " + lowDominationCountTotal);
        System.out.println("Highest Domination Count in Low Domination Count Cars: " + calculateMax(lowDominationCountCars, "dominationCount"));
        System.out.println("Lowest Domination Count in Low Domination Count Cars: " + calculateMin(lowDominationCountCars, "dominationCount"));
    }

    private static float calculateAverage(List<Car> cars, String field) {
        return (float) cars.stream()
                    .mapToDouble(car -> {
                        switch (field) {
                            case "mileage": return car.getMileage();
                            case "price": return car.getPrice();
                            case "year": return car.getYear();
                            default: return 0.0;
                        }
                    })
                    .average()
                    .orElse(0.0);
    }

    private static int calculateMax(List<Car> cars, String field) {
        return cars.stream()
                   .mapToInt(car -> {
                       switch (field) {
                           case "mileage": return car.getMileage();
                           case "price": return (int) car.getPrice();
                           case "year": return car.getYear();
                           case "dominationCount": return car.getDominationCount();
                           default: return Integer.MIN_VALUE;
                       }
                   })
                   .max()
                   .orElse(Integer.MIN_VALUE);
    }

    private static int calculateMin(List<Car> cars, String field) {
        return cars.stream()
                   .mapToInt(car -> {
                       switch (field) {
                           case "mileage": return car.getMileage();
                           case "price": return (int) car.getPrice();
                           case "year": return car.getYear();
                           case "dominationCount": return car.getDominationCount();
                           default: return Integer.MAX_VALUE;
                       }
                   })
                   .min()
                   .orElse(Integer.MAX_VALUE);
    }

    private static int calculateTotal(List<Car> cars, String field) {
        return cars.stream()
                   .filter(car -> field.equals("dominationCount"))
                   .mapToInt(Car::getDominationCount)
                   .sum();
    }
}