package problem1;

import java.util.HashSet;
import java.util.Objects;

/**
 * Class that holds all the type of household products
 * @author muruganandham.d
 */
public class HouseHoldTypes {

  /**
   * created hashset for holding all the household type products
   */
  public static HashSet<String> householdTypeList = new HashSet<>() {{
    add("Paper Towels");
    add("Shampoo");
  }};

  /**
   * Product type to be included in the list
   *
   * @param type adds the type of the product in household list
   */
  public static void add(String type) {
    householdTypeList.add(type);
  }


  @Override
  public String toString() {
    return householdTypeList.toString();
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(householdTypeList);
  }

}
