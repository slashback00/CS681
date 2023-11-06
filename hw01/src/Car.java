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

    public void setDominationCount(LinkedList<Car> cars) {
        this.dominationCount = 0;
        for (Car car : cars) {
            if (car.getYear() >= this.getYear() && car.getMileage() <= this.getMileage() && car.getPrice() <= this.getPrice()) {
                if (car.getYear() > this.getYear() || car.getMileage() < this.getMileage() || car.getPrice() < this.getPrice()) {
                    dominationCount++;
                }
            }
        }
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

        LinkedList<Car> cars = new LinkedList<Car>();
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.add(c4);
        cars.add(c5);
        cars.add(c6);

        c1.setDominationCount(cars);
        c2.setDominationCount(cars);
        c3.setDominationCount(cars);
        c4.setDominationCount(cars);
        c5.setDominationCount(cars);
        c6.setDominationCount(cars);

        int mileageThreshold = 15;
        float priceThreshold = 150000;
        int yearThreshold = 2022;
        int dominationCountThreshold = 2;

        LinkedList<Car> highMileageCars = new LinkedList<>();
        LinkedList<Car> lowMileageCars = new LinkedList<>();
        LinkedList<Car> highPriceCars = new LinkedList<>();
        LinkedList<Car> lowPriceCars = new LinkedList<>();
        LinkedList<Car> recentCars = new LinkedList<>();
        LinkedList<Car> oldCars = new LinkedList<>();
        LinkedList<Car> highDominationCountCars = new LinkedList<>();
        LinkedList<Car> lowDominationCountCars = new LinkedList<>();

        for (Car car : cars) {
            if (car.getMileage() >= mileageThreshold) {
                highMileageCars.add(car);
            } else {
                lowMileageCars.add(car);
            }

            if (car.getPrice() >= priceThreshold) {
                highPriceCars.add(car);
            } else {
                lowPriceCars.add(car);
            }

            if (car.getYear() >= yearThreshold) {
                recentCars.add(car);
            } else {
                oldCars.add(car);
            }

            if (car.getDominationCount() >= dominationCountThreshold) {
                highDominationCountCars.add(car);
            } else {
                lowDominationCountCars.add(car);
            }
        }

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

    private static float calculateAverage(LinkedList<Car> cars, String field) {
        float sum = 0;
        for (Car car : cars) {
            switch (field) {
                case "mileage":
                    sum += car.getMileage();
                    break;
                case "price":
                    sum += car.getPrice();
                    break;
                case "year":
                    sum += car.getYear();
                    break;
                default:
                    break;
            }
        }
        return sum / cars.size();
    }

    private static int calculateMax(LinkedList<Car> cars, String field) {
        int max = Integer.MIN_VALUE;
        for (Car car : cars) {
            switch (field) {
                case "mileage":
                    max = Math.max(max, car.getMileage());
                    break;
                case "price":
                    max = Math.max(max, (int) car.getPrice());
                    break;
                case "year":
                    max = Math.max(max, car.getYear());
                    break;
                case "dominationCount":
                    max = Math.max(max, car.getDominationCount());
                    break;
                default:
                    break;
            }
        }
        return max;
    }

    private static int calculateMin(LinkedList<Car> cars, String field) {
        int min = Integer.MAX_VALUE;
        for (Car car : cars) {
            switch (field) {
                case "mileage":
                    min = Math.min(min, car.getMileage());
                    break;
                case "price":
                    min = Math.min(min, (int) car.getPrice());
                    break;
                case "year":
                    min = Math.min(min, car.getYear());
                    break;
                case "dominationCount":
                    min = Math.min(min, car.getDominationCount());
                    break;
                default:
                    break;
            }
        }
        return min;
    }

    private static int calculateTotal(LinkedList<Car> cars, String field) {
        int total = 0;
        for (Car car : cars) {
            if (field.equals("dominationCount")) {
                total += car.getDominationCount();
            }
        }
        return total;
    }
}
