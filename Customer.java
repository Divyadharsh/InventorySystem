package problem1;

import java.util.Objects;

/**
 * Class customer stors the information of customer details
 * @author muruganandham.d
 */
public class Customer {

  private String name;
  private int age;
  private ShoppingCart shoppingCart = new ShoppingCart();
  private Receipt receipt = new Receipt();

  /**
   * Constructor with custoemr name and age details
   *
   * @param name name of the customer
   * @param age  age of the customer
   */
  public Customer(String name, int age) {
    this.name = name;
    this.age = age;
  }

  /**
   * @return returns the name of the customer
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the customer.
   *
   * @param name name of the customer is set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return returns age of the customer
   * */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the customer.
   *
   * @param age age of the customer is set
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * obtaining products from the shopping cart
   *
   * @param product products in the shopping cart
   * @return returns the customer cart details
   */
  public Product getProduct(Product product) {
    return shoppingCart.getProduct(product);
  }

  /**
   * Get the Quantity of product bought by the customer.
   *
   * @param product product and its details in the shopping cart
   * @return quantity of the product in the customer cart
   */
  public int getQuantityOfProduct(Product product) {
    return shoppingCart.getQuantity(product);
  }

  /**
   * @return ordersummary of the customer cart
   */
  public Receipt getReceipt() {
    return receipt;
  }

  /**
   * Addition of default product quantity (i.e 1) for grocery product
   *
   * @param product grocery product to be added in cart
   */
  public void addItem(Grocery product) {
    shoppingCart.addProduct(product);
  }

  /**
   * Addition of default product quantity (i.e 1) for household product
   *
   * @param product household  product to be added in cart
   */
  public void addItem(Household product) {
    shoppingCart.addProduct(product);
  }

  /**
   * Addition of more than one grocery product
   *
   * @param product  product details
   * @param quantity quantity of the product to be added
   */
  public void addItem(Grocery product, int quantity) {
    shoppingCart.addProduct(product, quantity);
  }

  /**
   * Addition of more than one household product
   *
   * @param product  product details
   * @param quantity quantity of the product to be added
   */
  public void addItem(Household product, int quantity) {
    shoppingCart.addProduct(product, quantity);
  }

  /**
   * @return returns total cost of the shopping cart
   */
  public double totPrice() {    //Calculates Total Price
    return shoppingCart.getTotPrice();
  }

  /**
   * checking out shopping cart and clears the cart and remove items from inventory
   */
  public void checkOut() {
    shoppingCart.finalizer(this, receipt);
    clearShoppingCart();
    Inventory.deductInventory(receipt.getProductBought());
  }

  /**
   * clearning customer cart and proceeding to order fulfillment
   */
  private void clearShoppingCart() {
    shoppingCart = new ShoppingCart();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }

  @Override
  public String toString() {
    System.out.println(shoppingCart.toString());
    return "";
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }
}
