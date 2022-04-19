package entity;

import entity.configurator.CarConfigurator;
import lombok.SneakyThrows;

public class CarFactory {

  @SneakyThrows
  public static Car create(CarType type, CarConfigurator configurator) {
    if (type == null) return null;
    Car car = type.getCarClass().getDeclaredConstructor().newInstance();
    configurator.configure(car);
    return car;
  }

  @SneakyThrows
  public static Car createEmptyCar(CarType type) {
    if (type == null) return null;
    return type.getCarClass().getDeclaredConstructor().newInstance();
  }
}
