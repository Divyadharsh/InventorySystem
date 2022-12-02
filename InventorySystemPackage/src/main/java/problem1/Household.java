package problem1;

/**
 * Household class stores units of the household product and other details by inheritting product class.
 * @author muruganandham.d
 */
public class Household extends Product {

  private int units;

  /**
   * Constructs Household constructor with the inheritted fields
   *
   * @param manufacturer name of the manufacturer
   * @param productName  name of the product
   * @param type         type of the product (ie Shampoo, Papertowel)
   * @param price        price of the given product
   * @param units        units of the given product
   */
  public Household(String manufacturer, String productName, String type, double price, int units) {
    super(manufacturer, productName, type, price);
    this.units = units;
  }

  /**
   * Constructs the Classes accordingly to the given inputs.
   *
   * @param manufacturer name of the manufacturer
   * @param productName  name of the product
   * @param type         type of the product (ie Shampoo, Papertowel)
   * @param price        price of the given product
   * @param units        unit of the given product
   * @param age          age of the customer
   */
  public Household(String manufacturer, String productName, String type, double price, int age,
      int units) {
    super(manufacturer, productName, type, price, age);
    this.units = units;
  }

  /**
   * @return units purchased
   */
  public int getUnits() {
    return units;
  }

  /**
   * Number of units to be set
   *
   * @param units set the units of the product
   */
  public void setUnits(int units) {
    this.units = units;
  }

  @Override
  public String toString() {
    System.out.println(
        "\n Manufacturer: " + getManufacturer() + "\n Product Name: " + getProductName()
            + "\n Price: " + getPrice() + "\n Age: " + getAge() + "\n Type: " + getType()
            + "\n Units: " + getUnits());
    return "";
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (!(o instanceof Household householproduct)) return false;
    Household obj = (Household) o;
    if (this.getUnits() != obj.units)
      return false;

    return true;
  }
}
