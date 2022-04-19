package command;

import services.RepositoryAdapter;

public class SellCarCommand implements Command {

  private final RepositoryAdapter adapter;

  public SellCarCommand(RepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void execute() {
    adapter.sellCar();
  }

  @Override
  public String toString() {
    return "Sell car";
  }
}
