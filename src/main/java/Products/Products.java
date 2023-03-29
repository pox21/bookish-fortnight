package Products;
import parser.GsonParser;
import product.Category;
import product.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ForDina.MyShoppingCart.getMyProduct;
import static chooseCategory.ChoseCategory.fill;

public class Products {
  static List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");

  private static  int quantities = 1;
  private static final List<Product> products =  GsonParser.parseProducts("src/main/resources/products.json");
  private static final Map<String,List<Product>> myProducts = new HashMap<>();
  private static final List<Product> selectedProduct= new ArrayList<>();

  public static void getProductsByCategory(String category) { // получает все продукты по категории
    System.out.println("-".repeat(43));
    System.out.printf("%7s | %15s | %6s | %6s%n", "Артикль", "Наименование", "Память", "Цена");
    System.out.println("-".repeat(43));
    assert products != null;
    for (Product product : products) {
      if (product.getCategory().equalsIgnoreCase(category)) {
        System.out.printf("%7s | ", product.getArticle());
        System.out.printf("%15s | ",product.getTitle());
        System.out.printf("%6s | ", product.getMemory());
        System.out.printf("%6s%n", product.getPrice());
      }
    }
    getMySelectProducts(products);
  }

  public static void getMySelectProducts( List<Product> products) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(" ");
    System.out.print(" Введите артикл товара : ");

    boolean addMore = true;
    try {
      do {
        int select = Integer.parseInt(br.readLine());

        for (Product product : products) {
          boolean article = product.getArticle() == select;

          if (article){
            System.out.print("Хотите купить : "+product.getTitle() +"  Введите количество : "  );

            do {
              quantities = Integer.parseInt(br.readLine());
              if(quantities > 0){
                for (int i = 0; i < quantities; i++) {
                  selectedProduct.add(product);
                }
                myProducts.put("MY-DEVICE",selectedProduct);
              }
              else{
                System.out.print("  Хотите купить : "+product.getTitle() +"  Введите количество больше 0 : "  );
              }
            }while (quantities == 0 || quantities < 0);
          }
        }
          System.out.print(" Хотите добавить еще  товар нажмите Y/N : ");
          String yOrN = br.readLine();

        if(yOrN.equalsIgnoreCase("N")){
          System.out.print(" Хотите вернуться к категориям товаров Y/N : ");
          String yOrNCategory = br.readLine();
          if(yOrNCategory.equalsIgnoreCase("Y")){
          fill(categories);
          return;
          }
          addMore = false;
        }else if(yOrN.equalsIgnoreCase("Y")){
          getMySelectProducts(products);
          return;
        }
      }while (addMore);

      getMyProduct(myProducts);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

//  public static void DDDD() {
//
//    System.out.println("data = " + myProducts);
//
//
//  }
}

