package ForDina;

import product.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class MyShoppingCart {

  public static void getMyProduct(List<Product> selectedProduct) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    printCart(selectedProduct);

    System.out.print("Хотите удалить все товары из корзины? Нажмите Y/N: ");
    String s = br.readLine();
    if (s.equalsIgnoreCase("Y")) {
      selectedProduct.removeAll(selectedProduct);
      printCart(selectedProduct);
//      return;
    }

    removeFromCart(selectedProduct);


  }

  public static void printCart (List<Product> selectedProduct) {
    int sum = 0;

    for (int i = 0; i < selectedProduct.size(); i++) {
      sum =+ selectedProduct.get(i).getPrice();
    }

    if (selectedProduct.size() == 0) {
      System.out.println("Ваша корзина пуста");
    }
    if (selectedProduct.size() == 1) {
      System.out.println("В вашей корзине: " + selectedProduct.size() + " товар, на сумму: " + sum + " у.е.");
    }
    if (selectedProduct.size() > 1 && selectedProduct.size() < 5 ) {
      System.out.println("В вашей корзине: " + selectedProduct.size() + " товара, на сумму: " + sum + " у.е.");
    }
    if (selectedProduct.size() >= 5 ) {
      System.out.println("В вашей корзине: " + selectedProduct.size() + " товаров, на сумму: " + sum + " у.е.");
    }

    int count = 1;
    for (Product p : selectedProduct) {
      System.out.print(count + ". ");
      System.out.println(p);
      count++;
    }



  }

  public static void removeFromCart (List<Product> selectedProduct) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    do {
      System.out.print("Хотите удалить конкретный товар? Нажмите Y/N: ");
      String yOrN = br.readLine();
      if (yOrN.equalsIgnoreCase("Y")) {
        try {
          System.out.print("Какой товар вы хотели бы удалить? Введите порядковый номер: ");
          int select = Integer.parseInt(br.readLine());
          selectedProduct.remove(selectedProduct.get(select - 1));
          printCart(selectedProduct);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("Введите корректное значение!");
        }

      }
      if (yOrN.equalsIgnoreCase("N")) {
        printCart(selectedProduct);
        break;
      }
    } while (selectedProduct.size() > 0);
  }
}
