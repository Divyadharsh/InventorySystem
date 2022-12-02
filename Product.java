package problem1;

import java.util.Objects;

/**
 * Class holds all the details about the product in the store
 * @author muruganandham.d
 */
public class Product {

  private String manufacturer;
  private String productName;

  private String type; // for assumption of the product type between household and grocery products
  private double price;
  private int age;

  /**
   * Constructs the class for the given details
   *
   * @param manufacturer name of the manufacturer
   * @param productName  name of the product
   * @param type         type of the product(cheese,salmon,Beer,shampoo,papertowels)
   * @param price        price of the product
   */
  public Product(String manufacturer, String productName, String type,
      double price) {       //when items don't want to verify age this is invoked
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.age = 0;
    this.type = type;
  }

  /**
   * Constructs the class for the given details and customer sge is added
   *
   * @param manufacturer name of the manufacturer
   * @param productName  name of the product
   * @param type         type of the product(cheese,salmon or Beer)
   * @param price        price of the product
   * @param age          age of the customer
   */
  public Product(String manufacturer, String productName, String type, double price,
      int age) {    //for age restricted product this in invoked
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.age = age;
    this.type = type;
  }

  /**
   * @return returns name of the manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets the name of manufacturer
   *
   * @param manufacturer name of the manufacturer is set
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * @return gets name of the product
   */
  public String getProductName() {
    return productName;
  }

  /**
   * Set name of the product.
   *
   * @param productName sets name of the product
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return get the price of the product
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of the Product.
   *
   * @param price sets the price of the product
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @return gets age of the customer to validate the product purchase
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the customer
   *
   * @param age customer age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return gets the type of the product in grocery and household
   */
  public String getType() {
    return type;
  }

  /**
   * Set the type of the product
   *
   * @param type product type
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(manufacturer + productName + type);
  }

  @Override
  public String toString() {
    System.out.println(
        "\n Manufacturer: " + getManufacturer() + "\n Product Name: " + getProductName() + "\n Price: "
            + getPrice() + "\n Age: " + getAge() + "\n Type: " + getType());
    return "";
  }

  @Override
  public boolean equals(Object object) {

    if (this == object) return true;
    if (!(object instanceof Product product)) return false;
    Product obj = (Product) object;
    if (this.getPrice() != obj.price)
      return false;

    return true;
  }

}
