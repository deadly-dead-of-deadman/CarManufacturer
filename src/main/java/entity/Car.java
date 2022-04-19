package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Objects;

public abstract class Car {

  @Getter @Setter
  protected Long id;
  @Getter @Setter
  protected String brand, model, manufacturer;
  @Getter @Setter
  protected Integer number, power;
  @Getter @Setter
  protected Calendar manufacturingDate;

  public Car(Long id, String brand, String model, String manufacturer, Integer number, Integer power, Calendar manufacturingDate) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.manufacturer = manufacturer;
    this.number = number;
    this.power = power;
    this.manufacturingDate = manufacturingDate;
  }

  /**
   * Checks field equality without regard to id
   */
  public boolean sameTo(Car item) {
    return Objects.equals(brand, item.brand) &&
      Objects.equals(model, item.model) &&
      Objects.equals(manufacturer, item.manufacturer) &&
      Objects.equals(number, item.number) &&
      Objects.equals(power, item.power) &&
      Objects.equals(manufacturingDate, item.manufacturingDate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return Objects.equals(id, car.id) &&
      Objects.equals(brand, car.brand) &&
      Objects.equals(model, car.model) &&
      Objects.equals(manufacturer, car.manufacturer) &&
      Objects.equals(number, car.number) &&
      Objects.equals(power, car.power) &&
      Objects.equals(manufacturingDate, car.manufacturingDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, model, model, manufacturer, number, power, manufacturingDate);
  }
}
