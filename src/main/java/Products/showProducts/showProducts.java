package Products.showProducts;

import product.Product;
import utils.Colors;
import java.util.List;

public class showProducts {

  static String colorReset = Colors.RESET.getColor();
  static String colorGreen = Colors.GREEN.getColor();
  static String colorYellow = Colors.YELLOW.getColor();
  static String colorPurple = Colors.PURPLE.getColor();

  //Показываем наименование моделей
  public static void showProductsByCategory(String category,List<Product> products) {
    System.out.println(colorYellow + "-".repeat(43));
    System.out.printf("%7s | %15s | %6s | %6s%n", "Артикул", "Наименование", "Память", "Цена");
    System.out.println("-".repeat(43));
    assert products != null;
    for (Product product : products) {
      if (product.getCategory().equalsIgnoreCase(category)) {
        System.out.printf("%s%7s | ", colorGreen, product.getArticle());
        System.out.printf("%15s | ", product.getTitle());
        System.out.printf("%6s | ", product.getMemory());
        System.out.printf("%s%6s€%s%n", colorPurple, product.getPrice(), colorReset);
      }
    }
  }
  //Показываем корзину
  public static void showProductsCart(List<Product> products) {
    System.out.println(colorYellow + "-".repeat(50));
    System.out.printf(" №  | %7s | %15s | %6s | %6s%n", "Артикул", "Наименование", "Память", "Цена");
    System.out.println("-".repeat(50) + colorReset);
    int count = 1;
    for (Product product : products) {
      System.out.printf("%s%2s | ", colorGreen, count);
      System.out.printf("%7s | ", product.getArticle());
      System.out.printf("%15s | ", product.getTitle());
      System.out.printf("%6s | ", product.getMemory());
      System.out.printf("%s%6s€%s%n", colorPurple, product.getPrice(), colorReset);
      count++;
    }
  }

}
