package tools;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CarParametersFromCLI {

  private static final Scanner scn = new Scanner(System.in);

  public static String getBrand() {
    System.out.print("Brand: ");
    return scn.nextLine();
  }

  public static String getModel() {
    System.out.print("Model: ");
    return scn.nextLine();
  }

  public static String getManufacturer() {
    System.out.print("Manufacturer: ");
    return scn.nextLine();
  }

  public static Integer getNumber() {
    System.out.print("Number: ");
    return Integer.parseInt(scn.nextLine());
  }

  public static Integer getPower() {
    System.out.print("Power: ");
    return Integer.parseInt(scn.nextLine());
  }

  public static Calendar getManufacturingDate() {
    System.out.print("Manufacturing Year: ");
    int year = Integer.parseInt(scn.nextLine());

    System.out.print("Manufacturing Month: ");
    int month = Integer.parseInt(scn.nextLine());

    System.out.print("Manufacturing Day: ");
    int day = Integer.parseInt(scn.nextLine());

    return new GregorianCalendar(year, month, day);
  }

}
