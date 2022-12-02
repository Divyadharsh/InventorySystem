package problem1;

import java.util.HashSet;
import java.util.Objects;

/**
 * Class that holds all the type of grocery products
 * @author muruganandham.d
 */
public class GroceryTypes {

  /**
   * created hashset for holding all the grocery type products
   */
  public static HashSet<String> groceryTypeList = new HashSet<>() {{
    add("Salmon");
    add("Cheese");
    add("Beer");
  }};

  /**
   * Product type to be included in the list
   *
   * @param type adds the type of the product in grocery list
   */
  public static void add(String type) {
    groceryTypeList.add(type);
  }

  @Override
  public String toString() {
    return groceryTypeList.toString();
  }


  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }


  @Override
  public int hashCode() {
    return Objects.hashCode(groceryTypeList);
  }
}
