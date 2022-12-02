package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroceryTest {

  private String manufacturer;
  private String productName;
  private String type;
  private double price;
  private int age;
  private Product product;
  private double weight;

  Grocery grocery;

  @BeforeEach
  void setUp() {
    manufacturer = "Amul";
    productName = "Mozzarella";
    type = "Cheese";
    price = 12.29;
    weight = 2.5;

    grocery = new Grocery(manufacturer, productName, type, price, weight);
  }

  @Test
  void getWeight() {
    assertEquals(grocery.getWeight(), weight);
  }

  @Test
  void setWeight() {
    grocery = new Grocery(manufacturer, productName, type, price, 16, weight);
    grocery.setWeight(5.5);
    assertEquals(grocery.getWeight(), 5.5);
  }

  @Test
  void testEquals() {
    Grocery g1 = new Grocery("Kingfisher","Bigfish","salmon",18.49,2.5);
    Grocery g2 = new Grocery("Kingfisher","Bigfish","salmon",18.49,2.5);
    Grocery g3 = new Grocery("Dolphinhouse","bbfishy","salmon",15.79,1.5);

    boolean equal = g1.equals(g2);
    assertTrue(equal);
    boolean equal1 = g3.equals(g3);
    assertTrue(equal1);
    boolean equal2 = g1.equals(null);
    assertFalse(equal2);
    boolean equal3 = g1.equals(g3);
    assertFalse(equal3);

  }
}