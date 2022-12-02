package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

  private String manufacturer;
  private String productName;
  private String type;
  private double price;
  private int age;
  private Product product;

  @BeforeEach
  void setUp() {
    manufacturer = "Amul";
    productName = "Mozzarella";
    type = "Cheese";
    price = 12.29;

    product = new Product(manufacturer, productName, type, price);
  }

  @Test
  void getManufacturer() {
    assertEquals(product.getManufacturer(), manufacturer);
  }

  @Test
  void setManufacturer() {
    product.setManufacturer("Milky Mist");
    assertEquals(product.getManufacturer(), "Milky Mist");
  }

  @Test
  void getProductName() {
    assertEquals(product.getProductName(), productName);
  }

  @Test
  void setProductName() {
    product.setProductName("parmesan");
    assertEquals(product.getProductName(), "parmesan");
  }

  @Test
  void getPrice() {
    assertEquals(product.getPrice(), price);
    product = new Product(manufacturer, productName, type, price, 35);
    assertEquals(product.getAge(), 35);
  }

  @Test
  void setPrice() {
    product.setPrice(10.99);
    assertEquals(product.getPrice(), 10.99);
  }

  @Test
  void getAge() {
    assertEquals(product.getAge(), 0);
  }

  @Test
  void setAge() {
    product.setAge(21);
    assertEquals(product.getAge(), 21);
  }

  @Test
  void getType() {
    assertEquals(product.getType(), type);
  }

  @Test
  void setType() {
    product.setType("Beer");
    assertEquals(product.getType(), "Beer");
  }

  @Test
  void testEquals() {
    Product p1 = new Product("Kingfisher","Bigfish","salmon",18.49);
    Product p2 = new Product("Kingfisher","Bigfish","salmon",18.49);
    Product p3 = new Product("Dolphinhouse","bbfishy","salmon",15.79);

    boolean equal = p1.equals(p2);
    assertTrue(equal);
    boolean equal1 = p3.equals(p3);
    assertTrue(equal1);
    boolean equal2 = p1.equals(null);
    assertFalse(equal2);
    boolean equal3 = p1.equals(p3);
    assertFalse(equal3);
  }

}