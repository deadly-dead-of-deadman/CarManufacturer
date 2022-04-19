package entity.cars;

import entity.Car;

import java.text.SimpleDateFormat;

public class SUV extends Car {

  public SUV(){
    super(null, null, null, null, null, null, null);
  }

  @Override
  public String toString() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return "SUV{" +
            "brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", number=" + number +
            ", manufacturing_date=" + format.format(manufacturingDate.getTime()) +
            '}';
  }
}
