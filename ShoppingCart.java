package problem1;

import static problem1.GroceryTypes.groceryTypeList;
import static problem1.HouseHoldTypes.householdTypeList;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class shoppingCart holds the details of purchase (i.e) customer shoppingCart details
 * @author muruganandham.d
 */
public class ShoppingCart {

  private HashMap<Integer, StockItem> itemBought = new HashMap<>();
  private final String GROCERIES = "grocery";
  private final String HOUSEHOLD = "household";
  private final String NONE = "none";

  private final int DEFAULT_QUANTITY = 1;

  /**
   * Returns product's information.
   *
   * @param product product details
   * @return return details of the product
   */
  public Product getProduct(Product product) {
    if (this.itemBought.get(product.hashCode()) != null) {
      return this.itemBought.get(product.hashCode()).getProduct();
    }
    return null;
  }

  /**
   * Quantity customer wants to purchase
   *
   * @param product product details
   * @return returns quantity of the product customer purchased
   */
  public int getQuantity(Product product) {
    return this.itemBought.get(product.hashCode()).getQuantity();
  }

  /**
   * @return total retail price of the customer shopping cart
   */
  public double getTotPrice() {
    double totalCost = 0;
    for (int i : itemBought.keySet()) {
      totalCost +=
          itemBought.get(i).getProduct().getPrice() * itemBought.get(i).getQuantity();
    }
    return  totalCost;
  }
  /**
   * Default quantity 1 for household products
   *
   * @param product Household product to be added into shopping cart
   */
  public void addProduct(Household product) {

    //Check if there's stock left in Inventory to add, else gives alert.
    if (!Inventory.returnStockCheck(product, DEFAULT_QUANTITY)) {
      System.out.println("Demanded quantity more than stock");
      return;
    }
    Household temp = new Household(product.getManufacturer(), product.getProductName(),
        product.getType(), product.getPrice(), product.getUnits());
    itemBought.put(temp.hashCode(), new StockItem(temp, DEFAULT_QUANTITY));
  }

  /**
   * Default quantity 1 for grocery products
   *
   * @param product Grocery product to be added into shopping cart
   */
  public void addProduct(Grocery product) {

    if (!Inventory.returnStockCheck(product, DEFAULT_QUANTITY)) {
      System.out.println("Demanded quantity more than stock");
      return;
    }

    Grocery temp = new Grocery(product.getManufacturer(), product.getProductName(),
        product.getType(), product.getPrice(), product.getWeight());
    itemBought.put(temp.hashCode(), new StockItem(temp, DEFAULT_QUANTITY));
  }

  /**
   * invoked when add grocery products more than one
   *
   * @param product  grocery product to be added into shopping cart
   * @param quantity Quantity to be added
   */
  public void addProduct(Grocery product, int quantity) {
    if (!Inventory.returnStockCheck(product, quantity)) {
      System.out.println("Requested quantity is not available in the stock");
      return;
    }
    itemBought.put(product.hashCode(), new StockItem(product, quantity));
  }

  /**
   * invoked when add household products more than one
   *
   * @param product  household product to be added into shopping cart
   * @param quantity quantity to be added
   */
  public void addProduct(Household product, int quantity) {
    if (!Inventory.returnStockCheck(product, quantity)) {
      System.out.println("Requested quantity is not available in the stock");
      return;
    }
    itemBought.put(product.hashCode(), new StockItem(product, quantity));
  }

  /**
   * Remove Grocery items from the cart
   *
   * @param product Grocery item
   */
  public void removeProduct(Product product) {
    itemBought.remove(product.hashCode());
  }

  /**
   * If the product exists in the cart of the customer
   *
   * @param product Product to check
   * @return Returns true if the product exists in the cart
   */
  public boolean doesExist(Product product) {
    return itemBought.containsKey(product.hashCode());
  }

  /**
   * Returns type of product i.e. if its grocery or household
   *
   * @param type type of product chosen.
   * @return returns the type of product picked up
   */
  private String typeOfProduct(String type) {
    if (groceryTypeList.contains(type)) {
      return GROCERIES;
    } else if (householdTypeList.contains(type)) {
      return HOUSEHOLD;
    } else {
      return NONE;
    }
  }

  /**
   * Substitutes the grocery which is out of stock with other product of same type from inventory
   *
   * @param product  product to be substituted
   * @param quantity quantity of Product bought
   * @return returns product viable for substitution
   */
  private Product getSubGrocery(Product product, int quantity) {
    for (int i : Inventory.groceryStock.keySet()) {
      if (Inventory.groceryStock.get(i).getProduct().getType() != product.getType()) {
        continue;
      }

      Grocery groceryProduct = (Grocery) Inventory.groceryStock.get(i).getProduct();
      if (!Inventory.returnStockCheck(groceryProduct, quantity)) {
        continue;
      }
      if (groceryProduct.getPrice() > product.getPrice()) {
        continue;
      }
      if (((Grocery) product).getWeight() < groceryProduct.getWeight()) {
        continue;
      }
      return groceryProduct;
    }
    return null;
  }

  /**
   * Substitutes the household which is out of stock with other product of same type from inventory
   *
   * @param product  Product to be substituted
   * @param quantity quantity of Product bought
   * @return returns product viable for substitution
   */
  private Product getSubHousehold(Product product, int quantity) {
    for (int i : Inventory.householdStock.keySet()) {
      if (Inventory.householdStock.get(i).getProduct().getType() != product.getType()) {
        continue;
      }

      Household householdProduct = (Household) Inventory.householdStock.get(i).getProduct();
      if (!Inventory.returnStockCheck(householdProduct, quantity)) {
        continue;
      }
      if (householdProduct.getPrice() > product.getPrice()) {
        continue;
      }
      if (((Household) product).getUnits() < householdProduct.getUnits()) {
        continue;
      }
      return householdProduct;
    }
    return null;
  }

  /**
   * Helper class which checks what class of product the product to be substituted is
   *
   * @param product  Product to be substituted
   * @param quantity quantity of the product to be substituted
   * @return returns product viable for substitution
   */
  private Product substitute(Product product, int quantity) {
    switch (typeOfProduct(product.getType())) {
      case GROCERIES -> {
        return getSubGrocery(product, quantity);
      }
      case HOUSEHOLD -> {
        return getSubHousehold(product, quantity);
      }
    }
    return null;
  }

  /**
   * Checks age i.e. if customer is old enough to buy the product else removed
   *
   * @param product product details
   * @param age age of the customer
   */
  private boolean ageCheck(Product product, int age) {
    return product.getAge() <= age;
  }

  /**
   * Checks through all the items, if its to be substituted if it's out of stock or removed if customer is underage for the product
   *
   * @param customer Customer credentials
   * @param receipt  Customer's receipt
   */
  public void finalizer(Customer customer, Receipt receipt) {
    for (int i : itemBought.keySet()) {
      StockItem item = itemBought.get(i);
      if (!ageCheck(item.getProduct(), customer.getAge())) {    //Check Age to remove Grocery
        receipt.addProductsRemoved(item.getProduct());
        continue;
      }

      if (Inventory.returnStockCheck(item.getProduct(), item.getQuantity())) {
        receipt.addProductBought(
            new StockItem(item.getProduct(), item.getQuantity()));
      } else {
        Product p = substitute(item.getProduct(), item.getQuantity());
        if (p != null) {
          receipt.addProductBought(
              new StockItem(p, item.getQuantity()));
        } else {
          receipt.addProductsOutofStock(item.getProduct());
        }
      }
    }
    receipt.totalRetailPrice();
  }


  @Override
  public String toString() {
    String res = "";
    for (int i : itemBought.keySet()) {
      res +=
          itemBought.get(i).getProduct().toString() + "\nQuantity: " + itemBought.get(
              i).getQuantity();
    }
    return res;
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemBought);
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

}
