package services.CLIAdapters;

import entity.Car;
import entity.CarFactory;
import entity.CarType;
import entity.configurator.CarFromCLIConfigurator;
import repository.Repository;
import services.RepositoryAdapter;

import java.util.*;

public class RepositoryCLIAdapter implements RepositoryAdapter {

  private final Repository<Car> repository;
  private final Scanner scn = new Scanner(System.in);

  public RepositoryCLIAdapter(Repository<Car> repository) {
    this.repository = repository;
  }

  private static boolean containCar(List<Car> cars, Car toCheck) {
    for (Car car : cars) {
      if (car.sameTo(toCheck)) return true;
    }
    return false;
  }

  private static Car containCar(Map<Car, Integer> cars, Car toCheck) {
    for (Map.Entry<Car, Integer> entry : cars.entrySet()) {
      if (entry.getKey().sameTo(toCheck)) return entry.getKey();
    }
    return null;
  }

  @Override
  public void acceptCars() {
    System.out.println("Select the car you want to manufacture:\n");
    Car car = chooseAndCreateCar();
    if (car == null){
      System.out.println("Error!!!");
      return;
    }
    System.out.println("How much?");
    int choose = Integer.parseInt(scn.nextLine());
    if (choose > 0) {
      for (int i = 0; i < choose; i++) repository.add(car);
    } else {
      System.out.println("Incorrect input!");
    }
  }

  @Override
  public void sellCar() {
    List<Car> cars = getDifferentCars();

    if (cars.size() == 0) return;

    System.out.println("Select the car you want to sell:");
    for (int i = 0; i < cars.size(); i++) System.out.println((i + 1) + ") " + cars.get(i));

    int choose = Integer.parseInt(scn.nextLine()) - 1;
    if (choose >= 0 && choose < cars.size()) {
      repository.delete(repository.getAll().get(choose));
    } else {
      System.out.println("Out of range!");
    }
  }

  @Override
  public void viewCatalog() {
    System.out.println("----------Catalog----------");
    Map<Car, Integer> catalog = getCatalog();
    for (Map.Entry<Car, Integer> entry : catalog.entrySet()) {
      System.out.println(entry.getKey() + "; Amount in repository: " + entry.getValue());
    }
  }

  private Car chooseAndCreateCar() {
    for (CarType carType : CarType.values()) {
      System.out.println(carType.getTypeNumber() + " -> " + carType.getCarClass().getSimpleName());
    }
    int choose = Integer.parseInt(scn.nextLine());
    return CarFactory.create(CarType.getTypeFromTypeNumber(choose), new CarFromCLIConfigurator());
  }

  /**
   * returns only cars with unique fields
   */
  private List<Car> getDifferentCars() {
    List<Car> cars = repository.getAll();
    List<Car> result = new ArrayList<>();
    for (Car car : cars) {
      if (!containCar(result, car)) result.add(car);
    }
    return result;
  }

  /**
   * returns cars only with unique fields and counts similar in repo
   */
  private Map<Car, Integer> getCatalog() {
    Map<Car, Integer> catalog = new HashMap<>();
    List<Car> cars = repository.getAll();
    for (Car car : cars) {
      Car carToPut = containCar(catalog, car);
      if (carToPut != null) {
        catalog.put(carToPut, catalog.get(carToPut) + 1);
      } else {
        catalog.put(car, 1);
      }
    }
    return catalog;
  }
}
