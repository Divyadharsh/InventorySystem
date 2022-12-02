package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockItemTest {
  private String manufacturer;
  private String productName;
  private String type;
  private double price;
  private int age;

  private Product product;
  private int quantity;
  private StockItem stockItem;

  @BeforeEach
  void setUp() {
    manufacturer = "Amul";
    productName = "Mozzarella";
    type = "Cheese";
    price = 12.29;

    product = new Product(manufacturer, productName, type, price);
    quantity = 10;
    stockItem = new StockItem(product, quantity);
  }

  @Test
  void getProduct() {
    assertEquals(stockItem.getProduct(), product);
  }

  @Test
  void setProduct() {
    product = new Product(manufacturer, productName, type, price, 21);
    stockItem.setProduct(product);
    assertEquals(stockItem.getProduct(), product);
  }

  @Test
  void getQuantity() {
    assertEquals(stockItem.getQuantity(), quantity);
  }

  @Test
  void setQuantity() {
    stockItem.setQuantity(11);
    assertEquals(stockItem.getQuantity(), 11);
  }

  @Test
  void checkStock() {
    assertTrue(stockItem.checkStock(10));
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      stockItem.checkStock(20);
    });
    assertEquals(exception.getMessage(), "There is No enough stock to buy the product");
  }

  @Test
  void deductStock() {
    stockItem.deductStock(5);
    assertEquals(stockItem.getQuantity(), 5);
  }

  @Test
  void getTotalValue() {
    assertEquals(stockItem.getTotalValue(), quantity*price);
  }

  @Test
  void testEquals() {
    StockItem stock1 = new StockItem(new Product("Kingfisher","Bigfish","salmon",18.49),10);
    StockItem stock2 = new StockItem(new Product("Kingfisher","Bigfish","salmon",18.49),10);
    StockItem stock3 = new StockItem(new Product("Aroma","Happybelly","cheese",10.0),2);


    boolean equal = stock1.equals(stock2);
    assertTrue(equal);
    boolean equal1 = stock3.equals(stock3);
    assertTrue(equal1);
    boolean equal2 = stock1.equals(null);
    assertFalse(equal2);
    boolean equal3 = stock1.equals(stock3);
    assertFalse(equal3);
  }


}