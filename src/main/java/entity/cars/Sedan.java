package entity.cars;

import entity.Car;

public class Sedan extends Car {

  public Sedan(){
    super(null, null, null, null, null, null, null);
  }

  @Override
  public String toString() {
    return "Sedan{" +
      "brand='" + brand + '\'' +
      ", model='" + model + '\'' +
      ", manufacturer='" + manufacturer + '\'' +
      ", power=" + power +
      '}';
  }

}
