package hw03;

public class CarPriceResultHolder {

  private int numCars;
  private double totalPrice;

  public CarPriceResultHolder() {
    this.numCars = 0; 
    this.totalPrice = 0.0;
  }

  public void addCar(double price) {
    numCars++;
    totalPrice += price;
  }

  public int getNumCars() {
    return numCars;
  }

  public double getTotalPrice() {
    return totalPrice; 
  }

  public double getAveragePrice() {
    return totalPrice / numCars;
  }

}