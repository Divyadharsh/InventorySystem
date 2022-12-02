package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HouseholdTest {

  private String manufacturer;
  private String productName;
  private String type;
  private double price;
  private int age;
  private int unit;

  private Household household;

  @BeforeEach
  void setUp() {
    manufacturer = "Procter & Gamble";
    productName = "Head&Shoulders";
    type = "Shampoo";
    price = 12.29;
    unit = 5;

    household = new Household(manufacturer, productName, type, price, unit);
  }

  @Test
  void getUnits() {
    assertEquals(household.getUnits(), unit);
  }

  @Test
  void setUnits() {
    household = new Household(manufacturer, productName, type, price, 10, unit);
    household.setUnits(2);
    assertEquals(household.getUnits(), 2);
  }

  @Test
  void testEquals() {
    Household h1 = new Household("Keratin","silkyshine","shampoo",16,2);
    Household h2 = new Household("Keratin","silkyshine","shampoo",16,2);
    Household h3 = new Household("Ogx","superstrong","shampoo",15,1);

    boolean equal = h1.equals(h2);
    assertTrue(equal);
    boolean equal1 = h3.equals(h3);
    assertTrue(equal1);
    boolean equal2 = h1.equals(null);
    assertFalse(equal2);
    boolean equal3 = h1.equals(h3);
    assertFalse(equal3);

  }
}