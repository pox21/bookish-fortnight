package chooseCategory;

import parser.GsonParser;
import product.Category;
import products.Products;
import user.User;
import user.UsersControl;
import utils.Colors;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static products.showProducts.ShowProducts.showOrders;
import static utils.Auth.isLogin;
import static utils.Auth.authUser;
import static utils.ReadInput.readIntInput;
import static utils.ReadInput.readQuestionInput;
import static utils.ReadInput.readStringInput;

public class ChoseCategory {

  static List<Category> categories = GsonParser.parseCategories(
      "src/main/resources/categories.json");
  static String colorReset = Colors.RESET.getColor();
  static String colorYellow = Colors.YELLOW.getColor();
  static String colorCyan = Colors.CYAN.getColor();
  static String colorRed = Colors.RED.getColor();
  static String colorPurple = Colors.PURPLE.getColor();

  public static UsersControl control = new UsersControl();
  public static User user;
  private static final Map<Integer, String> categoryProducts = new HashMap<>();

  // Выводим категории в консоль
  public static void runShop() {

    System.out.println(" ");
    System.out.printf("%s%10sAppleСин STORE%11s%s%n", colorYellow, "", "", colorReset);
    System.out.printf("%s%8s== Наименование ==%8s%s%n", colorPurple, "", "", colorReset);
    System.out.printf("%s%5s>>> Категории товаров <<<%5s%s%n", colorPurple, "", "", colorReset);
    System.out.printf("%s%s%s%n", colorCyan, "-".repeat(35), colorReset);
    for (Category category : categories) {
      System.out.printf(
          "%s%d%s: %s- %s %s - %s%n",
          colorRed, category.getId(), colorReset,
          colorCyan, category.getTitle(), category.getIcon(), colorReset
      );
      categoryProducts.put(category.getId(), category.getTitle());
    }
    System.out.printf("%s%s%s%n", colorCyan, "-".repeat(35), colorReset);
    if (!isLogin) {
      char isAccountHas = readQuestionInput("У вас уже есть аккаунт?");
      if (isAccountHas == 'y' || isAccountHas == 'у') {
        userPanel();
      }
    } else {
      System.out.println("Добро пожаловать " + colorPurple + authUser.getName() + colorReset);
    }

    addOurCategory();
  }

  public static void addOurCategory() {
    String formatInfo = String.format("%sВыберите категорию: %s", Colors.GREEN.getColor(),
        colorReset);
    // получаем категорию
    int select = readIntInput(formatInfo, categoryProducts.size());

    for (Map.Entry<Integer, String> product : categoryProducts.entrySet()) {
      boolean selected = product.getKey() == select;
      if (selected) {
//передаем дальше что бы получить список товаров данной категории
        System.out.println();
        System.out.printf(
            "%s%s %-8s %s%s%n",
            colorYellow, "=".repeat(17), product.getValue(), "=".repeat(17), colorReset);
        Products.getProductsByCategory(product.getValue());
      }
    }
  }

  public static void userPanel() {
    if (!auth()) {
      return;
    }
    if (user.getOrders().size() > 0) {
      char isShowOrders = readQuestionInput("Хотите посмотреть свои покупки?");
      if (isShowOrders == 'y') {
        System.out.println(colorPurple + ">> " + user.getName() + " <<" + colorReset);
        showOrders(user.getOrders());
      }
    }
  }

  public static boolean auth() {
    boolean isAuth = false;
    int i = 0;
    do {
      String email = readStringInput("Введите ваш email: ", 6, true);
      String password = readStringInput("Введите ваш пароль: ", 4);
      isAuth = control.authorization(email, password);
      i++;
      if (isAuth) {
        user = control.login(email, password);
        System.out.println("Вы вошли под именем " + Colors.CYAN.getColor() + user.getName()
            + Colors.RESET.getColor());
        isLogin = true;
        authUser = user;
        return true;
      }
    } while (i < 3);

    System.out.println(colorYellow
        + "Нам кажется вы мошенник \uD83D\uDE11! или забыли свои данные, попробуйте как-нибудь в другой раз \uD83D\uDE09"
        + colorReset);
    return false;
  }

}