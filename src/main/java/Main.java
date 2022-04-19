import command.*;
import entity.Car;
import repository.Repository;
import repository.persistence.BasicConnectionPool;
import repository.persistence.ConnectionPool;
import repository.persistence.RepositoryInDB;
import services.CLIAdapters.RepositoryCLIAdapter;
import services.RepositoryAdapter;
import ui.CLI;
import ui.UI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws SQLException {

    ConnectionPool connectionPool = BasicConnectionPool.create(
      "jdbc:h2:C:/Users/vanya/IdeaProjects/CarManufacturer/src/main/resources/carManufacture;MV_STORE=false",
      "Ivan",
      ""
    );
    Repository<Car> carRepository = new RepositoryInDB(connectionPool);
    RepositoryAdapter adapter = new RepositoryCLIAdapter(carRepository);

    List<Command> commands = new ArrayList<>(
      Arrays.asList(
        new AcceptCarsCommand(adapter),
        new SellCarCommand(adapter),
        new ViewCatalogCommand(adapter),
        new ShutdownCommand((Shutdownable) connectionPool)
      )
    );

    UI ui = new CLI(commands);
    ui.run();
  }
}
