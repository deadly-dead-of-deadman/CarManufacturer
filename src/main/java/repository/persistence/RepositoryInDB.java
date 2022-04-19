package repository.persistence;

import entity.CarFactory;
import entity.CarType;
import entity.Car;
import repository.Repository;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class RepositoryInDB implements Repository<Car> {

  private final ConnectionPool connectionPool;

  public RepositoryInDB(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public List<Car> getAll() {
    try {
      Connection connection = connectionPool.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");
      connectionPool.releaseConnection(connection);
      return extractor.extract(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public void add(Car car) {
    try {
      Connection connection = connectionPool.getConnection();
      PreparedStatement ps = connection.prepareStatement(
        "insert into CARS(brand, model, manufacturer, number, power, manufacturing_date, car_type) " +
          "values ( ? , ? , ? , ? , ? , ? , ? )"
      );
      ps.setObject(1, car.getBrand(), Types.VARCHAR);
      ps.setObject(2, car.getModel(), Types.VARCHAR);
      ps.setObject(3, car.getManufacturer(), Types.VARCHAR);
      ps.setObject(4, car.getNumber(), Types.INTEGER);
      ps.setObject(5, car.getPower(), Types.INTEGER);
      if(car.getManufacturingDate() == null){
        ps.setNull(6, Types.DATE);
      } else {
        ps.setDate(6, new java.sql.Date(car.getManufacturingDate().getTimeInMillis()));
      }
      ps.setObject(7, Objects.requireNonNull(CarType.getTypeFromClass(car.getClass())).getTypeNumber(), Types.INTEGER);
      ps.execute();
      connectionPool.releaseConnection(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Car car) {
    Long id = car.getId();
    if (id == null) return;
    try {
      Connection connection = connectionPool.getConnection();
      Statement statement = connection.createStatement();
      statement.execute(String.format("DELETE CARS " +
        "WHERE ID = %s;", id));
      connectionPool.releaseConnection(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private final Extractor<Car> extractor = rs -> {
    List<Car> cars = new ArrayList<>();
    while (rs.next()) {
      Car car = CarFactory.createEmptyCar(CarType.getTypeFromTypeNumber(rs.getInt("car_type")));
      if (car == null) continue;

      car.setId(rs.getLong("id"));
      car.setBrand(rs.getString("brand"));
      car.setModel(rs.getString("model"));
      car.setManufacturer(rs.getString("manufacturer"));
      car.setNumber(rs.getInt("number"));
      car.setPower(rs.getInt("power"));
      car.setManufacturingDate(toCalendar(rs.getDate("manufacturing_date")));

      cars.add(car);
    }
    return cars;
  };

  private static Calendar toCalendar(Date date) {
    if (date == null) return null;
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }
}
