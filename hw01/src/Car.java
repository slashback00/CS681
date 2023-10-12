package hw01;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

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
    

        // Define thresholds for HIGH and LOW groups
        int mileageThreshold = 15;
        float priceThreshold = 150000;
        int yearThreshold = 2022;
        int dominationCountThreshold = 2;

        // Separate cars into HIGH and LOW groups based on different sorting policies
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

        // Sort each group according to the specified criteria
        Collections.sort(highMileageCars, Comparator.comparing(Car::getMileage, Comparator.reverseOrder()));
        Collections.sort(lowMileageCars, Comparator.comparing(Car::getMileage, Comparator.reverseOrder()));
        Collections.sort(highPriceCars, Comparator.comparing(Car::getPrice, Comparator.reverseOrder()));
        Collections.sort(lowPriceCars, Comparator.comparing(Car::getPrice, Comparator.reverseOrder()));
        Collections.sort(recentCars, Comparator.comparing(Car::getYear, Comparator.reverseOrder()));
        Collections.sort(oldCars, Comparator.comparing(Car::getYear, Comparator.reverseOrder()));
        Collections.sort(highDominationCountCars, Comparator.comparing(Car::getDominationCount, Comparator.reverseOrder()));
        Collections.sort(lowDominationCountCars, Comparator.comparing(Car::getDominationCount, Comparator.reverseOrder()));

        // Print the sorted lists
        System.out.println("High Mileage Cars: ");
        System.out.println(highMileageCars);

        System.out.println("Low Mileage Cars: ");
        System.out.println(lowMileageCars);

        System.out.println("High Price Cars: ");
        System.out.println(highPriceCars);

        System.out.println("Low Price Cars: ");
        System.out.println(lowPriceCars);

        System.out.println("Recent Cars: ");
        System.out.println(recentCars);

        System.out.println("Old Cars: ");
        System.out.println(oldCars);

        System.out.println("High Domination Count Cars: ");
        System.out.println(highDominationCountCars);

        System.out.println("Low Domination Count Cars: ");
        System.out.println(lowDominationCountCars);

        // Calculate average, highest, and lowest values for each group
        float highMileageAverage = calculateAverage(highMileageCars, "mileage");
        float lowMileageAverage = calculateAverage(lowMileageCars, "mileage");
        float highPriceAverage = calculateAverage(highPriceCars, "price");
        float lowPriceAverage = calculateAverage(lowPriceCars, "price");
        float recentYearAverage = calculateAverage(recentCars, "year");
        float oldYearAverage = calculateAverage(oldCars, "year");
        int highDominationCountTotal = calculateTotal(highDominationCountCars, "dominationCount");
        int lowDominationCountTotal = calculateTotal(lowDominationCountCars, "dominationCount");

        float highMileageMax = calculateMax(highMileageCars, "mileage");
        float lowMileageMax = calculateMax(lowMileageCars, "mileage");
        float highPriceMax = calculateMax(highPriceCars, "price");
        float lowPriceMax = calculateMax(lowPriceCars, "price");
        float recentYearMax = calculateMax(recentCars, "year");
        float oldYearMax = calculateMax(oldCars, "year");
        float highDominationCountMax = calculateMax(highDominationCountCars, "dominationCount");
        float lowDominationCountMax = calculateMax(lowDominationCountCars, "dominationCount");

        float highMileageMin = calculateMin(highMileageCars, "mileage");
        float lowMileageMin = calculateMin(lowMileageCars, "mileage");
        float highPriceMin = calculateMin(highPriceCars, "price");
        float lowPriceMin = calculateMin(lowPriceCars, "price");
        float recentYearMin = calculateMin(recentCars, "year");
        float oldYearMin = calculateMin(oldCars, "year");
        float highDominationCountMin = calculateMin(highDominationCountCars, "dominationCount");
        float lowDominationCountMin = calculateMin(lowDominationCountCars, "dominationCount");

        // Print the sorted lists
        System.out.println("High Mileage Cars: ");
        System.out.println(highMileageCars);
        System.out.println("Average Mileage in High Mileage Cars: " + highMileageAverage);
        System.out.println("Highest Mileage in High Mileage Cars: " + highMileageMax);
        System.out.println("Lowest Mileage in High Mileage Cars: " + highMileageMin);

        System.out.println("Low Mileage Cars: ");
        System.out.println(lowMileageCars);
        System.out.println("Average Mileage in Low Mileage Cars: " + lowMileageAverage);
        System.out.println("Highest Mileage in Low Mileage Cars: " + lowMileageMax);
        System.out.println("Lowest Mileage in Low Mileage Cars: " + lowMileageMin);

        System.out.println("High Price Cars: ");
        System.out.println(highPriceCars);
        System.out.println("Average Price in High Price Cars: " + highPriceAverage);
        System.out.println("Highest Price in High Price Cars: " + highPriceMax);
        System.out.println("Lowest Price in High Price Cars: " + highPriceMin);

        System.out.println("Low Price Cars: ");
        System.out.println(lowPriceCars);
        System.out.println("Average Price in Low Price Cars: " + lowPriceAverage);
        System.out.println("Highest Price in Low Price Cars: " + lowPriceMax);
        System.out.println("Lowest Price in Low Price Cars: " + lowPriceMin);

        System.out.println("Recent Cars: ");
        System.out.println(recentCars);
        System.out.println("Average Year in Recent Cars: " + recentYearAverage);
        System.out.println("Highest Year in Recent Cars: " + recentYearMax);
        System.out.println("Lowest Year in Recent Cars: " + recentYearMin);

        System.out.println("Old Cars: ");
        System.out.println(oldCars);
        System.out.println("Average Year in Old Cars: " + oldYearAverage);
        System.out.println("Highest Year in Old Cars: " + oldYearMax);
        System.out.println("Lowest Year in Old Cars: " + oldYearMin);

        System.out.println("High Domination Count Cars: ");
        System.out.println(highDominationCountCars);
        System.out.println("Total Domination Count in High Domination Count Cars: " + highDominationCountTotal);
        System.out.println("Highest Domination Count in High Domination Count Cars: " + highDominationCountMax);
        System.out.println("Lowest Domination Count in High Domination Count Cars: " + highDominationCountMin);

        System.out.println("Low Domination Count Cars: ");
        System.out.println(lowDominationCountCars);
        System.out.println("Total Domination Count in Low Domination Count Cars: " + lowDominationCountTotal);
        System.out.println("Highest Domination Count in Low Domination Count Cars: " + lowDominationCountMax);
        System.out.println("Lowest Domination Count in Low Domination Count Cars: " + lowDominationCountMin);

    }

    // Helper method to calculate the average value in a list of cars based on a specified field
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

    // Helper method to calculate the highest value in a list of cars based on a specified field
    private static float calculateMax(LinkedList<Car> cars, String field) {
        float max = Float.MIN_VALUE;
        int maxInt = Integer.MIN_VALUE;
        for (Car car : cars) {
            switch (field) {
                case "mileage":
                    max = Math.max(max, car.getMileage());
                    break;
                case "price":
                    max = Math.max(max, car.getPrice());
                    break;
                case "year":
                    maxInt = Math.max(maxInt, car.getYear());
                    break;
                case "dominationCount":
                    maxInt = Math.max(maxInt, car.getDominationCount());
                    break;
                default:
                    break;
            }
        }
        return field.equals("year") || field.equals("dominationCount") ? maxInt : max;
    }

    // Helper method to calculate the lowest value in a list of cars based on a specified field
    private static float calculateMin(LinkedList<Car> cars, String field) {
        float min = Float.MAX_VALUE;
        int minInt = Integer.MAX_VALUE;
        for (Car car : cars) {
            switch (field) {
                case "mileage":
                    min = Math.min(min, car.getMileage());
                    break;
                case "price":
                    min = Math.min(min, car.getPrice());
                    break;
                case "year":
                    minInt = Math.min(minInt, car.getYear());
                    break;
                case "dominationCount":
                    minInt = Math.min(minInt, car.getDominationCount());
                    break;
                default:
                    break;
            }
        }
        return field.equals("year") || field.equals("dominationCount") ? minInt : min;
    }

    // Helper method to calculate the total of a specific field in a list of cars
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
