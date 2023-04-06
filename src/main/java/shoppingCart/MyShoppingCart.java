package shoppingCart;

import cart.Cart;
import product.Product;
import utils.Colors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static products.showProducts.ShowProducts.showProductsCart;
import static order.Order.confirmOrder;
import static utils.ReadInput.readIntInput;
import static utils.ReadInput.readQuestionInput;

public class MyShoppingCart {

  static String colorReset = Colors.RESET.getColor();
  static String colorRed = Colors.RED.getColor();
  static String colorCyan = Colors.CYAN.getColor();

  public static void getMyProduct(List<Product> selectedProduct) {
    printCart(selectedProduct);

    char s = readQuestionInput("Хотите удалить все товары из корзины?");
    if (s == 'y' || s == 'у') {
      selectedProduct.clear();
      printCart(selectedProduct);
      Cart.removeProducts();
    }

      if (selectedProduct.size() > 0) {
          removeFromCart(selectedProduct);
      }
  }

  public static void printCart(List<Product> selectedProduct) {
    int sum = 0;

    for (Product product : selectedProduct) {
      sum += product.getPrice();
    }

    if (selectedProduct.size() == 0) {
      System.out.println("Ваша корзина пуста");
    } else {
      System.out.println(
          colorCyan + "В вашей корзине: " + Colors.PURPLE.getColor() + selectedProduct.size() + " "
              + getGoodsAddition(selectedProduct.size()) + colorCyan + ", на сумму: "
              + Colors.GREEN.getColor() + sum + " €" + colorReset);
      showProductsCart(selectedProduct);
    }
  }

  public static void removeFromCart(List<Product> selectedProduct) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    do {
      char yOrN = readQuestionInput("Хотите удалить конкретный товар?");
      if (yOrN == 'y' || yOrN == 'у') {
        try {
          int select = readIntInput("Какой товар вы хотели бы удалить? Введите порядковый номер: ", selectedProduct.size());
          selectedProduct.remove(selectedProduct.get(select - 1));
          printCart(selectedProduct);
        } catch (IndexOutOfBoundsException e) {
          System.err.println("Введите корректное значение!");
        }

      } else {
        printCart(selectedProduct);
        Cart.addProductToCart(selectedProduct);
        confirmOrder(Cart.products);
        break;
      }
    } while (selectedProduct.size() > 0);
  }

  public static String getGoodsAddition(int num) {
    int preLastDigit = num % 100 / 10;

    if (preLastDigit == 1) {
      return "товаров";
    }

    return switch (num % 10) {
      case 1 -> "товар";
      case 2, 3, 4 -> "товара";
      default -> "товаров";
    };

  }
}
