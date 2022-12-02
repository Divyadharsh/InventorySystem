package problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * class Receipt stores products purchased, productOutofStock,Products removed gives the order summary of the shopping
 * @author muruganandham.d
 */
public class Receipt {

  private float totPrice = 0;
  private ArrayList<StockItem> productBought = new ArrayList<>();
  private HashSet<Product> productsOutOfStock = new HashSet<>();
  private HashSet<Product> productsRemoved = new HashSet<>();

  /**
   * Purchased product by the customer
   *
   * @param product final products present in the cutomers shoppingcart
   */
  public void addProductBought(StockItem product) {

    productBought.add(product);
  }

  /**
   * products taht cannot be substituted or out of stock items
   *
   * @param product product out of stock that is present in the customers shopping cart
   */
  public void addProductsOutofStock(Product product) {
    productsOutOfStock.add(product);
  }

  /**
   * products removed from customers shopping cart because of the age restriction
   *
   * @param product products remove from the stock
   */
  public void addProductsRemoved(Product product) {

    System.out.println(product.getProductName());
    productsRemoved.add(product);
  }

  /**
   * @return total cost of the shopping cart
   */
  public float getTotPrice() {
    return totPrice;
  }

  /**
   * @return returns list of product substituted and purchased
   */
  public ArrayList<StockItem> getProductBought() {
    return productBought;
  }

  /**
   * @return returns list of product that are out of stock
   */
  public HashSet<Product> getProductsOutOfStock() {
    return productsOutOfStock;
  }

  /**
   * @return returns list of product removed because of age restriction
   */
  public HashSet<Product> getProductsRemoved() {
    return productsRemoved;
  }

  /**
   * Calculate total custom of products bought.
   */
  public void totalRetailPrice() {
    for (int i = 0; i < productBought.size(); i++) {
      totPrice += (productBought.get(i).getQuantity() * productBought.get(i).getProduct()
          .getPrice());
    }
  }

  @Override
  public String toString() {

    System.out.println("Shopping Summary"+"\n");
    System.out.println();
    System.out.println("List of Products Purchased: "+"\n");
    for(int item = 0; item < productBought.size(); item++){
      System.out.println(item+": "+ productBought.get(item).getProduct().toString() +" - "+
          "Quantity: "+productBought.get(item).getQuantity()+" - "+"TotalCost: "+productBought.get(item).getProduct().
          getPrice()+"\n");
    }
    System.out.println();
    System.out.println("Out of Stock Products in the Cart: "+"\n");
    System.out.println(Arrays.toString(productsOutOfStock.toArray())+"\n");
    System.out.println();
    System.out.println("Total Cost of items: "+ productsOutOfStock+"\n");

    return "\n";
  }

  @Override
  public int hashCode() {
    return Objects.hash(productBought, productsRemoved, productsRemoved);
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

}
