package Products.sortProducts;

import product.*;
import utils.Colors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static Products.Products.getMySelectProducts;
import static Products.showProducts.showProducts.showProductsByCategory;

public class sortProducts {
  static String colorReset = Colors.RESET.getColor();
  static String colorRed = Colors.RED.getColor();

  static String colorCyan = Colors.CYAN.getColor();
  static String colorPurple = Colors.PURPLE.getColor();

  public static void sortProducts(String category, List<Product> products) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.println("Сортировать по: ");
      System.out.println("\t - возрастанию цены:" + colorPurple + " '1'" + colorReset);
      System.out.println("\t - убыванию цены:" + colorPurple + " '2'" + colorReset);
      System.out.println("\t - объему памяти:" + colorPurple + " '3'" + colorReset);
      System.out.println("\t - названию:" + colorPurple + " '4'" + colorReset);
      System.out.print(colorCyan + "введите число: " + colorReset);
      int sortChoice = Integer.parseInt(br.readLine().trim());
      switch (sortChoice) {
        case 1 -> {
          assert products != null;
          products.sort(new ProductIncreasingPriceDecreasingMemoryComparator());
          showProductsByCategory(category, products);
          getMySelectProducts(products);
        }
        case 2 -> {
          assert products != null;
          products.sort(new ProductDecreasingPriceDecreasingMemoryComparator());
          showProductsByCategory(category, products);
          getMySelectProducts(products);
        }
        case 3 -> {
          assert products != null;
          products.sort(new ProductDecreasingMemoryIncreasingPriceComparator());
          showProductsByCategory(category, products);
          getMySelectProducts(products);
        }
        case 4 -> {
          assert products != null;
          products.sort(new ProductIncreasingTitleIncreasingPriceComparator());
          showProductsByCategory(category, products);
          getMySelectProducts(products);
        }
        default -> {
          System.out.println("  ");
          System.out.println(colorRed + "  Неверный ввод "+sortChoice +" введите номер сортировки "+ colorReset);
          sortProducts(category,products);
        }
      }
    } catch (IOException e) {
      System.out.println(colorRed+"Произошла ошибка : введите число "+colorReset );
      sortProducts(category,products);
    } catch (NumberFormatException e) {
      System.out.println(colorRed+"Введено неверное значение: введите номер сортировки "+colorReset);
      sortProducts(category,products);
    }
  }
}
