package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

  private Inventory inventory;
  private Grocery grocery;
  private Household household;

  @BeforeEach
  void setUp() {
    inventory = new Inventory();
    inventory.clearInventory();
    grocery = new Grocery("Amul", "Mozzarella", "Cheese", 12.29, 10);
    household = new Household("Procter & Gamble", "Head and Sholder", "Shampoo", 10.99, 10);
  }

  @Test
  void addStock() {
    inventory.addStock(grocery, 100);
    assertEquals(inventory.getProductFromStock(grocery).getProductName(), "Mozzarella");

    //checking non-trackable grocery items
    grocery = new Grocery("Amul", "Mozzarella", "Temp", 12.29, 10);
    inventory.addStock(grocery, 100);
    assertEquals(inventory.getProductFromStock(grocery), null);

    //checking non-trackable household items
    household = new Household("Procter & Gamble", "Head and Sholder", "temp", 10.99, 10);
    inventory.addStock(household, 100);
    assertEquals(inventory.getProductFromStock(household), null);

    grocery = new Grocery("Amul", "Parmesan", "Cheese", 12.29, 11);
    inventory.addStock(grocery, 100);
    assertEquals(inventory.getProductFromStock(grocery).getProductName(), "Parmesan");

    inventory.addStock(grocery, 100);
    assertEquals(inventory.getQuantity(grocery), 200);
    inventory.deductStock(grocery, 200);

    household = new Household("Procter & Gamble", "Head and Sholder", "Shampoo", 10.99, 10);
    inventory.addStock(household, 100);
    assertEquals(inventory.getProductFromStock(household).getProductName(), "Head and Sholder");

    assertEquals(inventory.getQuantity(household), 100);

    assertEquals(inventory.toString(),"");
  }

  @Test
  void returnStockCheck() {
    inventory.addStock(grocery, 100);
    assertTrue(inventory.returnStockCheck(grocery, 100));
    assertFalse(inventory.returnStockCheck(grocery, 200));

    inventory.addStock(household, 100);
    assertTrue(inventory.returnStockCheck(household, 5));
    assertFalse(inventory.returnStockCheck(household, 200));
  }

  @Test
  void deductStock() {

    inventory.addStock(grocery, 100);
    inventory.addStock(household, 100);

    inventory.deductStock(grocery, 20);
    assertEquals(inventory.getQuantity(grocery), 80);

    inventory.deductStock(household, 100);
    assertEquals(inventory.getQuantity(household), 0);
  }

  @Test
  void totValInventory() {
    inventory.addStock(grocery, 100);
    inventory.addStock(household, 100);
    assertEquals(inventory.totValInventory(),
        grocery.getPrice() * 100 + household.getPrice() * 100);
  }

  @Test
  void testEquals() {
    Inventory inventory1 = inventory;
    assertTrue(inventory.equals(inventory1));
    assertFalse(inventory.equals(new Inventory()));
  }
}