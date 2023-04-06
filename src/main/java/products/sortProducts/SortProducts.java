package products.sortProducts;

import product.*;
import utils.Colors;

import java.io.IOException;
import java.util.List;

import static products.Products.getMySelectProducts;
import static products.showProducts.ShowProducts.showProductsByCategory;
import static utils.ReadInput.readIntInput;

public class SortProducts {

  static String colorReset = Colors.RESET.getColor();
  static String colorRed = Colors.RED.getColor();

  static String colorCyan = Colors.CYAN.getColor();
  static String colorPurple = Colors.PURPLE.getColor();

  public static void sortProducts(String category, List<Product> products) {

    System.out.println("Сортировать по: ");
    System.out.println(colorPurple + "\t '1'" + colorReset + ": возрастанию цены");
    System.out.println(colorPurple + "\t '2'" + colorReset + ": убыванию цены");
    System.out.println(colorPurple + "\t '3'" + colorReset + ": объему памяти");
    System.out.println(colorPurple + "\t '4'" + colorReset + ": названию");

    int sortChoice = readIntInput("Введите число: " + colorReset, 4);
    ;
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
        System.out.println(
            colorRed + "  Неверный ввод " + sortChoice + " введите номер сортировки " + colorReset);
        sortProducts(category, products);
      }
    }
  }
}
