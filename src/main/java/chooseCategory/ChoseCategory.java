package chooseCategory;

import parser.GsonParser;
import product.Category;
import Products.Products;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoseCategory {

  private static Map<Integer, String> categoryProducts = new HashMap<>();

  // Выводим категории в консоль
  public static void fill(List<Category> categories) {
      System.out.print(" ");
      System.out.println("       DEVICE STORE           == Наименование == ");
    for (Category category : categories) {
      System.out.print("    Kатегории товара : ");
      System.out.println("Нажмите "+category.getId()+" -> " + category.getTitle());
      categoryProducts.put(category.getId(),category.getTitle());
    }
    try {
      addOurCategory();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public static void addOurCategory() throws IOException {
    // получаем категорию
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("    Введите категорию товара : ");
    int select = Integer.parseInt(br.readLine());
    for (Map.Entry<Integer, String> product : categoryProducts.entrySet()) {
      boolean selected = product.getKey() == select;
      if(selected){
//передаем дальше что бы получить список товаров данной категории
        System.out.println(" ");
        System.out.println("================== "+product.getValue()+ " ==================");
        Products.getProductsByCategory(product.getValue());
      }
    }
  }
}
