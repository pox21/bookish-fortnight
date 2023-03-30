package chooseCategory;

import MyError.MyIOException;
import product.Category;
import Products.Products;
import utils.Colors;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoseCategory {
  private static final String reset = Colors.RESET.getColor();
  private static final String yellow = Colors.YELLOW.getColor();
  private static final String cyan = Colors.CYAN.getColor();
  private static final String red = Colors.RED.getColor();
  private static final String green = Colors.GREEN.getColor();
  private static final Map<Integer, String> categoryProducts = new HashMap<>();
  // Выводим категории в консоль
  public static void fill(List<Category> categories) {

      System.out.print(" ");
      System.out.println(yellow+"       DEVICE STORE           == Наименование == "+ reset );
    for (Category category : categories) {
      System.out.print(cyan+"    Kатегории товара : "+reset);
      System.out.println("Нажмите "+cyan+category.getId()+" -> "+green + category.getTitle()+reset+" "+ category.getIcon());
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
    System.out.print(green+"    Введите категорию товара : "+reset);
    int select = 0;
    try {
      select = Integer.parseInt(br.readLine());
      if(select <= 0  ){
        throw new MyIOException(" число должно быть больше 0 " ,select);
      }
    } catch (MyIOException e) {
      System.out.println(red+"    Некорректный ввод : "+ e.getMessage()+reset);
      addOurCategory();
    }catch (NumberFormatException e){
      System.out.println(red+"    Некорректный ввод : введите номер товара "+reset);
      addOurCategory();
    }

    for (Map.Entry<Integer, String> product : categoryProducts.entrySet()) {
      boolean selected = product.getKey() == select;
      if(selected){
//передаем дальше что бы получить список товаров данной категории
        System.out.println(" ");
        System.out.println(yellow+"================== "+product.getValue()+" =================="+reset);
        Products.getProductsByCategory(product.getValue());
      }
    }
  }
}
