package products.showProducts;

import cart.ProductCart;
import java.util.Map;
import product.Product;
import utils.Colors;
import java.util.List;

public class ShowProducts {

  static String colorReset = Colors.RESET.getColor();
  static String colorGreen = Colors.GREEN.getColor();
  static String colorYellow = Colors.YELLOW.getColor();
  static String colorPurple = Colors.PURPLE.getColor();

  //Показываем наименование моделей
  public static void showProductsByCategory(String category, List<Product> products) {
    int repeatSymbolCount = 44;
    System.out.println(colorYellow + "-".repeat(repeatSymbolCount));
    System.out.printf("%7s | %15s | %6s | %6s%n", "Артикул", "Наименование", "Память", "Цена");
    System.out.println("-".repeat(repeatSymbolCount));
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
    int repeatSymbolCount = 50;
    System.out.println(colorYellow + "-".repeat(repeatSymbolCount));
    System.out.printf(" №  | %7s | %15s | %6s | %6s%n", "Артикул", "Наименование", "Память",
        "Цена");
    System.out.println("-".repeat(repeatSymbolCount) + colorReset);
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

  public static void showOrders(Map<Integer, List<ProductCart>> orders) {
    for (Integer key : orders.keySet()) {
      System.out.printf("%55s |%n", "| Номер заказа: " + colorPurple + key + colorReset);
      int repeatSymbolCount = 68;
      System.out.println(colorYellow + "-".repeat(repeatSymbolCount));
      System.out.printf(" №  | %7s | %15s | %6s | %6s | %6s | %6s%n", "Артикул", "Наименование",
          "Память", "Цена", "Кол-во", "Итог");
      System.out.println("-".repeat(repeatSymbolCount) + colorReset);
      int count = 1;
        for (ProductCart product : orders.get(key)) {
          System.out.printf("%s%2s | ", colorGreen, count);
          System.out.printf("%7s | ", product.getArticle());
          System.out.printf("%15s | ", product.getTitle());
          System.out.printf("%6s | ", product.getMemory());
          System.out.printf("%s%6s€ %s|%s ", colorPurple, product.getPrice(), colorGreen, colorReset);
          System.out.printf("%s%6s %s|%s ", colorPurple, product.getAmount(), colorGreen, colorReset);
          System.out.printf("%s%6s€%s%n", colorPurple, product.getTotalPrice(), colorReset);
          count++;
        }

      System.out.println(colorYellow + "-".repeat(repeatSymbolCount) + colorReset);
    }
  }

}
