package problem1;

import static problem1.GroceryTypes.groceryTypeList;
import static problem1.HouseHoldTypes.householdTypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * class Inventory holds all the details of the stock available and tracks the details with shopping
 * @author muruganandham.d
 */
public class Inventory { // here

  /**
   * Grocery stock in the inventory is tracked
   */
  public static HashMap<Integer, StockItem> groceryStock = new HashMap<>();
  /**
   * Household stock in the inventory is tracked
   */
  public static HashMap<Integer, StockItem> householdStock = new HashMap<>();

  /**
   * Default empty construct for objects
   */
  public Inventory() {
  }

  /**
   * Type of the product to check and store in the specific list
   *
   * @param type type of the product is stored
   * @return  Returns 'grocery' if product is a Grocery item or 'household' if product is Household item
   */
  private static HashMap<Integer, StockItem> typeOfProductList(String type) {
    if (groceryTypeList.contains(type)) {
      return groceryStock;
    } else if (householdTypeList.contains(type)) {
      return householdStock;
    } else {
      return null;
    }
  }

  /**
   * method returns product from inventory stock
   *
   * @param product product details to retrieve
   * @return returns product from the inventory
   */
  public Product getProductFromStock(Product product) {
    String temp = product.getManufacturer() + product.getProductName() + product.getType();
    HashMap<Integer, StockItem> stock = typeOfProductList(product.getType());
    if (stock != null && stock.containsKey(temp.hashCode())) {
      return stock.get(temp.hashCode()).getProduct();
    }
    return null;
  }

  /**
   * Calculates and return quantity of the product from inStock to inventory
   *
   * @param product product details to retrieve the quantity
   * @return quantity details from the inventory
   */
  public static int getQuantity(Product product) {
    String temp = product.getManufacturer() + product.getProductName() + product.getType();
    HashMap<Integer, StockItem> stock = typeOfProductList(product.getType());
    return stock.get(temp.hashCode()).getQuantity();
  }

  /**
   * updates quantity to inventory stock
   * @param product  product details
   * @param quantity quantity update
   */
  public void addStock(Product product, int quantity) {
    if (typeOfProductList(product.getType()) == null) {
      return;   // As Product cannot be tracked, it will not be added into Inventory stock.
    }
    HashMap<Integer, StockItem> stock = typeOfProductList(product.getType());
    StockItem stockItem;
    if (!stock.containsKey(product.hashCode())) {
      stockItem = new StockItem(product, quantity);
      stock.put(stockItem.hashCode(), stockItem);
    } else {
      stockItem = stock.get(product.hashCode());
      stockItem.setProduct(product);
      stockItem.setQuantity(stockItem.getQuantity() + quantity);
      stock.put(stockItem.hashCode(), stockItem);
    }
  }

  /**
   * Check on stock for proceeding shopping
   *
   * @param product product customer added in the shopping cart to buy
   * @param quantity quantity of the product from the customer
   * @return true if there is quantity else false
   */
  public static boolean returnStockCheck(Product product, int quantity) {
    HashMap<Integer, StockItem> stock = typeOfProductList(product.getType());
    return stock.get(product.hashCode()).getQuantity() >= quantity;
  }

  /**
   * removes the stock from inventory once it's added to the shopping cart
   *
   * @param product  Product whose stock is to be reduced
   * @param quantity by how much should the stock be reduced
   */
  public static void deductStock(Product product, int quantity) {
    HashMap<Integer, StockItem> stock = typeOfProductList(product.getType());
    StockItem item = stock.get(product.hashCode());
    item.setQuantity(item.getQuantity() - quantity);
  }

  /**
   * Goes through the list of products, so they can be deducted from the list
   *
   * @param products list of products to be removed from inventory stock
   */
  public static void deductInventory(ArrayList<StockItem> products) {
    for (int i = 0; i < products.size(); i++) {
      deductStock(products.get(i).getProduct(), products.get(i).getQuantity());
    }
  }

  /**
   * @return  Returns total retail price of the inventory
   */
  public double totValInventory() {
    double retailprice = 0;

    for (int i : groceryStock.keySet()) {
      retailprice += (groceryStock.get(i).getQuantity() * groceryStock.get(i).getProduct().getPrice());
    }
    for (int i : householdStock.keySet()) {
      retailprice += (householdStock.get(i).getQuantity() * householdStock.get(i).getProduct().getPrice());
    }
    return retailprice;
  }

  /**
   * Clears inventory after purchase is done
   */
  public void clearInventory() {
    groceryStock = new HashMap<>();
    householdStock = new HashMap<>();
  }

  @Override
  public String toString() {
    for (int item : groceryStock.keySet()) {
      System.out.println(
          item + " " + groceryStock.get(item).getProduct().getProductName() + " " + groceryStock.get(item)
              .getQuantity());
    }
    for (int item : householdStock.keySet()) {
      System.out.println(
          item + " " + householdStock.get(item).getProduct().getProductName() + " " + householdStock.get(
                  item)
              .getQuantity());
    }
    return "";
  }

  @Override
  public int hashCode() {
    return Objects.hash(householdStock, groceryStock);
  }

  @Override
  public boolean equals(Object obj) {
    return this == obj;
  }


}
