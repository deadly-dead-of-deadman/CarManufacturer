package entity.cars;

import entity.Car;

import java.text.SimpleDateFormat;

public class Hatchback extends Car {

  public Hatchback(){
    super(null, null, null, null, null, null, null);
  }

  @Override
  public String toString() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return "Hatchback{" +
      "brand='" + brand + '\'' +
      ", number=" + number +
      ", power=" + power +
      ", manufacturing_date=" + format.format(manufacturingDate.getTime()) +
      '}';
  }
}
