package problem1;

import java.util.Objects;

/**
 * Grocery class stores weight of the grocery product and other details by inheritting product class
 * @author muruganandham.d
 */
public class Grocery extends Product {

  private double weight;

  /**
   * Constructs Grocery constructor with the inheritted fields
   *
   * @param manufacturer name of the manufacturer
   * @param productName  name of the product
   * @param type         type of the product (ie cheese, salmon, beer)
   * @param price        price of the given product
   * @param weight       weight of the given product
   */
  public Grocery(String manufacturer, String productName, String type, double price,
      double weight) {
    super(manufacturer, productName, type, price);
    this.weight = weight;
  }

  /**
   * Constructs the Classes accordingly to the given inputs.
   *
   * @param manufacturer name of the manufacturer
   * @param productName  name of the product
   * @param type         type of the product (ie cheese, salmon, beer)
   * @param price        price of the given product
   * @param weight       weight of the given product
   * @param age          age of the customer
   */
  public Grocery(String manufacturer, String productName, String type, double price, int age,
      double weight) {
    super(manufacturer, productName, type, price, age);
    this.weight = weight;
  }

  /**
   * @return weight of the Grocery Item
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Set the weight of purchased item
   *
   * @param weight weight of the product purchased
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    System.out.println(
        "\n Manufacturer: " + getManufacturer() + "\n Product Name: " + getProductName()
            + "\n Price: " + getPrice() + "\n Age: " + getAge() + "\n Type: " + getType()
            + "\n Units: " + getWeight());
    return "";
  }
  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (!(o instanceof Grocery groceryproduct)) return false;
    Grocery obj = (Grocery) o;
    if (this.getWeight() != obj.weight)
      return false;

    return true;
  }
}
