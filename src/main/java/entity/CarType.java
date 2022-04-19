package entity;

import entity.cars.Sedan;
import entity.cars.Hatchback;
import entity.cars.SUV;

public enum CarType {

  SEDAN(1, Sedan.class),
  HATCHBACK(2, Hatchback.class),
  SUV(3, SUV.class);

  private final int typeNumber;
  private final Class<? extends Car> carClass;

  CarType(int typeNumber, Class<? extends Car> carClass) {
    this.typeNumber = typeNumber;
    this.carClass = carClass;
  }

  public int getTypeNumber() {
    return typeNumber;
  }

  public Class<? extends Car> getCarClass() {
    return carClass;
  }

  public static CarType getTypeFromTypeNumber(int typeNumber){
    for (CarType carType : CarType.values()) {
      if (carType.getTypeNumber() == typeNumber) return carType;
    }
    return null;
  }

  public static CarType getTypeFromClass(Class<? extends Car> carClass) {
    for (CarType carType : CarType.values()) {
      if (carType.getCarClass() == carClass) return carType;
    }
    return null;
  }

}
