package products;

import parser.GsonParser;
import product.Category;
import product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import utils.Colors;

import static shoppingCart.MyShoppingCart.getMyProduct;
import static chooseCategory.ChoseCategory.runShop;
import static products.showProducts.ShowProducts.showProductsByCategory;
import static products.sortProducts.SortProducts.sortProducts;
import static utils.ReadInput.readIntInput;
import static utils.ReadInput.readQuestionInput;

public class Products {

  public static String colorRed = Colors.RED.getColor();
  public static String colorReset = Colors.RESET.getColor();
  static String colorGreen = Colors.GREEN.getColor();

  static List<Category> categories = GsonParser.parseCategories(
      "src/main/resources/categories.json");
  private static final List<Product> products = GsonParser.parseProducts(
      "src/main/resources/products.json");
  private static List<Product> myProducts = new ArrayList<>();
  private static final List<Product> selectedProduct = new ArrayList<>();

  public static void getProductsByCategory(String category) { // получает все продукты по категории

    assert products != null;
    showProductsByCategory(category, products); //показывает продукты по категориям
    System.out.println();

    char choice = readQuestionInput("Хотите отсортировать товары?");

    if (choice == 'y' || choice == 'у') {
      // сортирует товары
      sortProducts(category, products);
      return;
    }

    getMySelectProducts(products);
  }

  public static void getMySelectProducts(List<Product> products) {

    boolean addMore = true;
      do {
        int select = readIntInput("Введите артикул товара: ", Integer.MAX_VALUE);

        boolean foundProduct = false;
        for (Product product : products) {
          if (product.getArticle() == select) {
            foundProduct = true;
            System.out.print("Добавить в корзину: " + colorGreen + product.getTitle() + ". " + colorReset);

            myProducts = getQuantitiesOfProducts(product);
          }
        }

        if (!foundProduct) {
          System.out.print(
              colorGreen + "Товар с артикулом " + colorRed + select + " не найден." + colorGreen
                  + " Попробуйте снова: " + colorReset);
          continue;
        }

        char yOrN = readQuestionInput("Хотите добавить еще товар из данной категории?");
        if (yOrN == 'y' || yOrN == 'у') {
          getMySelectProducts(products);
          return;
        }

        char yOrNCategory = readQuestionInput("Хотите добавить товар из другой категории?");
        if (yOrNCategory == 'y' || yOrNCategory == 'у') {
          runShop();
          return;
        }
        addMore = false;

      } while (addMore);
      getMyProduct(myProducts);


      //System.out.println(colorRed + "Ошибка: Можно ввести только артикул товара " + colorReset);
      getMySelectProducts(products);

  }

  public static List<Product> getQuantitiesOfProducts(Product product) {
    int quantities;
    do {
      try {
        quantities = readIntInput("Введите количество: ", Integer.MAX_VALUE);
        if (quantities > 0) {
          for (int i = 0; i < quantities; i++) {
            selectedProduct.add(product);
          }
        } else {
          System.out.print(colorRed + "Введите количество больше 0: " + colorReset);
        }
      } catch (NumberFormatException e) {
        System.out.print(colorRed + "Ошибка: введите число больше 0: " + colorReset);
        quantities = 0;
      }
    } while (quantities == 0 || quantities < 0);
    return selectedProduct;
  }

}

