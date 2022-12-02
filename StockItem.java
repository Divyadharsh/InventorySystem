package problem1;

import java.util.Objects;

/**
 * StockItem class maintains the product quantity and product details
 * @author muruganandham.d
 */
public class StockItem {
  private Product product;
  private int quantity;

  /**
   * Constructor to initialise items and its quantity.
   *
   * @param product  product maintains product details
   * @param quantity - quantity of the product
   */
  public StockItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * @return product information
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Sets the products information
   *
   * @param product sets the product information
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * @return quantity of the available product
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the product
   *
   * @param quantity quantity of the available product
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Checks the status of the stock and allows to buy the item
   *
   * @param pQuantity product quantity requested by the customer
   * @return check the stock and returns true if avilable
   */
  public boolean checkStock(int pQuantity) {    //Check if stock is adequate.
    if (quantity < pQuantity) {
      throw new IllegalArgumentException("There is No enough stock to buy the product");
    }

    return true;
  }

  /**
   * Reduces the quantity of item on purchase
   *
   * @param pQuanttity quantity of the product customer purchased
   */
  public void deductStock(int pQuanttity) {     //deduct stocks.
    quantity -= pQuanttity;
  }

  /**
   * @return tital retail price of the product available in the stock
   */
  public double getTotalValue() {
    return quantity * product.getPrice();
  }


  @Override
  public String toString() {
    System.out.println(
        "Product: " + product.toString() +"\n" +
        "Quantity: " + quantity
    );
    return "";
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        product.getManufacturer() + product.getProductName() + product.getType());
  }

  @Override
  public boolean equals(Object object) {

    if (this == object) return true;
    if (!(object instanceof StockItem stockItem)) return false;
    StockItem obj = (StockItem) object;
    if (this.getQuantity() != obj.quantity)
      return false;

    return true;
  }



}
