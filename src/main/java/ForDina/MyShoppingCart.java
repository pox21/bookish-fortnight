package ForDina;

import product.Product;

import java.util.List;
import java.util.Map;

public class MyShoppingCart {

  public static void getMyProduct(Map<String, List<Product>> myProducts){
    System.out.println(" Уже в корзине "+myProducts);
  }
}
