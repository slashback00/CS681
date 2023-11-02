package hw03;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    ArrayList<Car> cars = new ArrayList<>();
    
    // Add some sample cars
    cars.add(new Car("Toyota", "Camry", 2019, 20000, 25000)); 
    cars.add(new Car("Honda", "Civic", 2018, 30000, 20000));
    cars.add(new Car("Ford", "Mustang", 2017, 5000, 35000));
    cars.add(new Car("Tesla", "Model 3", 2020, 10000, 40000));
    
    double avgPrice = cars.stream()
                          .map(Car::getPrice)
                          .reduce(new CarPriceResultHolder(), 
                                 (r, p) -> {
                                   r.addCar(p);
                                   return r;
                                 },
                                 (r1, r2) -> {
                                   return r1;  
                                 })
                          .getAveragePrice();
    
    System.out.println("Average car price: " + avgPrice);

  }

}
