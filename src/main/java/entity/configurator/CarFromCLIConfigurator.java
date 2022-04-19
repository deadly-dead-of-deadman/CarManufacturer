package entity.configurator;

import entity.Car;
import entity.cars.Sedan;
import entity.cars.Hatchback;
import entity.cars.SUV;

import tools.CarParametersFromCLI;

public class CarFromCLIConfigurator implements CarConfigurator{

  @Override
  public void configure(Car car) {
    if (car.getClass().equals(Sedan.class)) configureCar(car);
    if (car.getClass().equals(Hatchback.class)) configureManufacturer(car);
    if (car.getClass().equals(SUV.class)) configureCarType(car);
  }

  private void configureCar(Car car){
    car.setBrand(CarParametersFromCLI.getBrand());
    car.setModel(CarParametersFromCLI.getModel());
    car.setManufacturer(CarParametersFromCLI.getManufacturer());
    car.setPower(CarParametersFromCLI.getPower());
  }

  private void configureManufacturer(Car car){
    car.setBrand(CarParametersFromCLI.getBrand());
    car.setNumber(CarParametersFromCLI.getNumber());
    car.setPower(CarParametersFromCLI.getPower());
    car.setManufacturingDate(CarParametersFromCLI.getManufacturingDate());
  }

  private void configureCarType(Car car){
    car.setBrand(CarParametersFromCLI.getBrand());
    car.setNumber(CarParametersFromCLI.getNumber());
    car.setManufacturingDate(CarParametersFromCLI.getManufacturingDate());
  }

}
