package repository;

import entity.Car;

import java.util.ArrayList;
import java.util.List;

public class RepositoryInMemory implements Repository<Car> {

  private static RepositoryInMemory INSTANCE;
  private final List<Car> cars;
  private Long id = 1L;

  private RepositoryInMemory() {
    cars = new ArrayList<>();
  }

  public static RepositoryInMemory getInstance() {
    if (INSTANCE == null) INSTANCE = new RepositoryInMemory();
    return INSTANCE;
  }

  @Override
  public List<Car> getAll() {
    return cars;
  }

  @Override
  public void add(Car car) {
    car.setId(id);
    id++;
    cars.add(car);
  }

  @Override
  public void delete(Car item) {
    cars.remove(item);
  }

}
