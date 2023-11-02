package hw03;

import java.util.ArrayList;
import java.util.Calendar;

public class Car {

  private String make;
  private String model;
  private int year;
  private int mileage;
  private float price;

  private ArrayList<Car> carList;
  private int dominationCount = 0;

  public Car(String make, String model, int year, int mileage, float price) {
    validateInputs(make, model, year, mileage, price);
    
    this.make = make;
    this.model = model;
    this.year = year; 
    this.mileage = mileage;
    this.price = price;
  }

  private void validateInputs(String make, String model, int year, int mileage, float price) {
    if (make == null || model == null) {
      throw new IllegalArgumentException("Make and model cannot be null");
    }

    if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
      throw new IllegalArgumentException("Invalid year: " + year);
    }

    if (mileage < 0) {
      throw new IllegalArgumentException("Mileage cannot be negative"); 
    }

    if (price <= 0) {
      throw new IllegalArgumentException("Price must be greater than 0");
    }
  }

  // Getter methods
 public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public float getPrice() {
        return price;
    }
    
    public ArrayList<Car> getcarList() {
        return carList;
    }
    
    public int getDominationCount() {
        return dominationCount;
    }
    
    
  // Setter methods 

  public void setCars(ArrayList<Car> carList) {
    this.carList = carList;
  }

  public void setDominationCount(ArrayList<Car> cars) {
    this.dominationCount = 0;

    for (Car car : cars) {
      if (car.getYear() >= this.getYear() && 
          car.getMileage() >= this.getMileage() &&
          car.getPrice() >= this.getPrice()) {
        dominationCount++;
      }
    }
  }

}
