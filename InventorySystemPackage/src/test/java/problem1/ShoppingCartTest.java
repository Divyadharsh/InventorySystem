package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

  Grocery grocery;
  Household household;
  ShoppingCart shoppingCart;
  Inventory inventory = new Inventory();

  @BeforeEach
  void setUp() {
    grocery = new Grocery("Amul", "Parmesan", "Cheese", 12.29, 10);
    household = new Household("Procter & Gamble", "Head and Sholder", "Shampoo", 10.99, 10);
    inventory = new Inventory();
    inventory.addStock(grocery, 100);
    inventory.addStock(household, 100);
    shoppingCart = new ShoppingCart();
  }

  @Test
  void addProduct() {

    inventory.clearInventory();

    inventory.addStock(grocery, 0);
    shoppingCart.addProduct(grocery);
    assertNull(shoppingCart.getProduct(grocery));

    inventory.addStock(grocery, 10);
    shoppingCart.addProduct(grocery);
    assertEquals(shoppingCart.getProduct(grocery).getManufacturer(), "Amul");

    inventory.addStock(household, 10);
    shoppingCart.addProduct(household);
    assertEquals(shoppingCart.getProduct(household).getManufacturer(), "Procter & Gamble");

    household = new Household("Procter", "Head", "Shampoo", 10.99, 10);
    inventory.addStock(household, 0);
    shoppingCart.addProduct(household);
    assertEquals(shoppingCart.getProduct(household), null);
  }

  @Test
  void testAddProduct() {

    shoppingCart.addProduct(grocery, 10);
    assertEquals(shoppingCart.getQuantity(grocery), 10);

    household = new Household("Amul", "Parmesan", "Shampoo", 12.29, 1);
    inventory.addStock(household, 100);
    shoppingCart.addProduct(household, 10);
    assertEquals(shoppingCart.getQuantity(household), 10);
  }

  @Test
  void removeGrocery() {
    shoppingCart.removeProduct(grocery);
    assertFalse(shoppingCart.doesExist(grocery));

    shoppingCart.removeProduct(household);
    assertFalse(shoppingCart.doesExist(household));
  }

  @Test
  void testDoesExist() {
    assertFalse(shoppingCart.doesExist(household));
    assertFalse(shoppingCart.doesExist(grocery));

    shoppingCart.addProduct(grocery);
    assertFalse(shoppingCart.doesExist(household));
    assertTrue(shoppingCart.doesExist(grocery));

    shoppingCart.addProduct(household);
    assertTrue(shoppingCart.doesExist(household));
    assertTrue(shoppingCart.doesExist(grocery));
  }

  @Test
  void getTotPriceTest() {
    shoppingCart.addProduct(grocery, 10);
    shoppingCart.addProduct(household, 10);

    assertEquals(shoppingCart.getTotPrice(),
        (grocery.getPrice() * 10) + (household.getPrice() * 10));
  }

  @Test
  void testFinalizerGrocery() {
    Receipt receipt = new Receipt();

    shoppingCart = new ShoppingCart();
    inventory.clearInventory();
    grocery = new Grocery("Amul", "Parmesan", "Cheese", 12.29, 10);
    inventory.addStock(grocery, 10);
    shoppingCart.addProduct(grocery, 20);

    grocery = new Grocery("Amul 1", "Parmesan", "Cheese", 10.99, 20);
    inventory.addStock(grocery, 30);

    grocery = new Grocery("Amul 2", "Parmesan", "Cheese", 10.99, 30);
    inventory.addStock(grocery, 1);

    shoppingCart.finalizer(new Customer("test", 10), receipt);
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 0);

    shoppingCart.addProduct(grocery, 1);
    shoppingCart.finalizer(new Customer("test", 10), receipt);
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 10.99);

    shoppingCart = new ShoppingCart();
    inventory.clearInventory();
    receipt = new Receipt();

    grocery = new Grocery("Amul", "Parmesan", "Cheese", 12.29, 10);
    inventory.addStock(grocery, 10);
    shoppingCart.addProduct(grocery, 20);

    grocery = new Grocery("Amul 1", "Parmesan", "Cheese", 10.99, 20);
    inventory.addStock(grocery, 30);

    grocery = new Grocery("Amul 2", "Parmesan", "Cheese", 10.99, 30);
    inventory.addStock(grocery, 25);

    grocery = new Grocery("Amul 3", "Parmesan", "Cheese", 10.99, 30);
    inventory.addStock(grocery, 25);
    shoppingCart.addProduct(grocery, 20);
    Inventory.deductStock(grocery, 10);

    shoppingCart.finalizer(new Customer("test", 10), receipt);
    receipt.toString();
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 219.8);

    shoppingCart = new ShoppingCart();
    inventory.clearInventory();
    receipt = new Receipt();

    grocery = new Grocery("Amul", "Parmesan", "Cheese", 12.29, 30);
    inventory.addStock(grocery, 10);
    shoppingCart.addProduct(grocery, 20);

    grocery = new Grocery("Amul 1", "Parmesan", "Cheese", 11.99, 10);
    inventory.addStock(grocery, 30);

    grocery = new Grocery("Amul 3", "Parmesan", "Cheese", 10.99, 30);
    inventory.addStock(grocery, 25);
    shoppingCart.addProduct(grocery, 20);
    Inventory.deductStock(grocery, 10);

    shoppingCart.finalizer(new Customer("test", 10), receipt);
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 0);
  }

  @Test
  void testFinalizerHousehold() {
    Receipt receipt = new Receipt();

    shoppingCart = new ShoppingCart();
    inventory.clearInventory();
    household = new Household("Amul", "Parmesan", "Shampoo", 12.29, 10);
    inventory.addStock(household, 10);
    shoppingCart.addProduct(household, 20);

    household = new Household("Amul 1", "Parmesan", "Shampoo", 10.99, 20);
    inventory.addStock(household, 30);

    household = new Household("Amul 2", "Parmesan", "Shampoo", 10.99, 30);
    inventory.addStock(household, 1);

    shoppingCart.finalizer(new Customer("test", 10), receipt);
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 0);

    shoppingCart.addProduct(household, 1);
    shoppingCart.finalizer(new Customer("test", 10), receipt);
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 10.99);

    shoppingCart = new ShoppingCart();
    inventory.clearInventory();
    receipt = new Receipt();

    household = new Household("Amul", "Parmesan", "Shampoo", 12.29, 10);
    inventory.addStock(household, 10);
    shoppingCart.addProduct(household, 20);

    household = new Household("Amul 1", "Parmesan", "Shampoo", 10.99, 20);
    inventory.addStock(household, 30);

    household = new Household("Amul 2", "Parmesan", "Shampoo", 10.99, 30);
    inventory.addStock(household, 25);

    household = new Household("Amul 3", "Parmesan", "Shampoo", 10.99, 30);
    inventory.addStock(household, 25);
    shoppingCart.addProduct(household, 20);
    Inventory.deductStock(household, 10);

    shoppingCart.finalizer(new Customer("test", 10), receipt);
    receipt.toString();
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 219.8);

    shoppingCart = new ShoppingCart();
    inventory.clearInventory();
    receipt = new Receipt();

    household = new Household("Amul", "Parmesan", "Shampoo", 12.29, 30);
    inventory.addStock(household, 10);
    shoppingCart.addProduct(household, 20);

    household = new Household("Amul 1", "Parmesan", "Shampoo", 11.99, 20);
    inventory.addStock(household, 30);

    household = new Household("Amul 3", "Parmesan", "Shampoo", 10.99, 30);
    inventory.addStock(household, 25);
    shoppingCart.addProduct(household, 20);
    Inventory.deductStock(household, 10);

    shoppingCart.finalizer(new Customer("test", 10), receipt);
    receipt.toString();
    assertEquals((receipt.getTotPrice() * 100) / 100.0, 0);
  }
}