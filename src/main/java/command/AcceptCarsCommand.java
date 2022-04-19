package command;

import services.RepositoryAdapter;

public class AcceptCarsCommand implements Command {

  private final RepositoryAdapter adapter;

  public AcceptCarsCommand(RepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void execute() {
    adapter.acceptCars();
  }

  @Override
  public String toString() {
    return "Manufacture cars";
  }
}
