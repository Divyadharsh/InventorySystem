package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private String name;
  private int age;
  private Grocery grocery1, grocery2, grocery3;
  private Household household1, household2, household3;
  private Customer customer;
  Inventory inventory = new Inventory();

  @BeforeEach
  void setUp() {
    name = "John";
    age = 24;

    grocery1 = new Grocery("tess", "Salmon", "Salmon", 12.29, 100);
    grocery2 = new Grocery("tess1", "Salmon", "Salmon", 12.29, 100);
    grocery3 = new Grocery("tess2", "corona", "Beer", 12.29, 21, 100);

    household1 = new Household("Procter & Gamble", "Head and Sholder", "Shampoo", 10.99, 100);
    household2 = new Household("Procter & Gamble Temp", "Head and Sholder", "Shampoo", 10.99, 100);
    household3 = new Household("Procter & Gamble Temp 1", "Head and Sholder", "Shampoo", 10.99, 30, 100);


    customer = new Customer(name, age);
    inventory.clearInventory();

    inventory.addStock(household1, 100);
    inventory.addStock(household2, 10);
    inventory.addStock(household3, 10);

    inventory.addStock(grocery1, 10);
    inventory.addStock(grocery2, 10);
    inventory.addStock(grocery3, 10);
  }

  @Test
  void getName() {
    assertEquals(customer.getName(), name);
  }

  @Test
  void setName() {
    customer.setName("test");
    assertEquals(customer.getName(), "test");
  }

  @Test
  void getAge() {
    assertEquals(customer.getAge(), age);
  }

  @Test
  void setAge() {
    customer.setAge(69);
    assertEquals(customer.getAge(), 69);
  }

  @Test
  void addItem() {
    customer.addItem(grocery1);
    customer.addItem(grocery3);
    customer.addItem(household1);
    assertEquals(customer.getProduct(grocery1).hashCode(), grocery1.hashCode());
    assertEquals(customer.getProduct(household1).hashCode(), household1.hashCode());

  }

  @Test
  void testAddItem() {
    customer.addItem(grocery1);
    customer.addItem(grocery1, 4);
    assertEquals(customer.getQuantityOfProduct(grocery1), 4);
  }

  @Test
  void totPrice() {
    customer.addItem(grocery1, 4);
    customer.addItem(household1, 1);
    assertEquals(customer.totPrice(), household1.getPrice() + grocery1.getPrice()*4);
  }

  @Test
  void checkOut() {

    customer.setAge(20);

    customer.addItem(grocery1, 5);
    customer.addItem(grocery2, 200);
    customer.addItem(grocery3, 5);


    customer.addItem(household1, 5);
    customer.addItem(household2, 50);
    customer.addItem(household3, 5);

    customer.checkOut();

    assertEquals(Inventory.getQuantity(grocery1), 5);   //Post stock deduction check
    assertEquals(Inventory.getQuantity(household1), 95);

    assertEquals(Math.round(customer.getReceipt().getTotPrice()*100)/100.0, 116.4);
  }
}