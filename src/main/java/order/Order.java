package order;

import static utils.Auth.isLogin;
import static utils.Auth.authUser;
import static utils.ReadInput.readQuestionInput;
import static utils.ReadInput.readStringInput;

import cart.ProductCart;
import chooseCategory.ChoseCategory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import parser.GsonParser;
import product.Category;
import user.User;
import user.UsersControl;
import utils.Colors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Order {

  // Оформление заказа
  static UsersControl control = new UsersControl();
  static List<Category> categories = GsonParser.parseCategories(
      "src/main/resources/categories.json");


  public static void confirmOrder(List<ProductCart> productCarts) {
    System.out.println("Оформить заказ ?");
    char isConfirm = readQuestionInput("");
    if (isConfirm != 'y') {
      System.out.println("Вернуться на главную");
      ChoseCategory.runShop();
    } else {
      Map<Integer, List<ProductCart>> order = new HashMap<>();
      order.put((int) new Date().getTime(), productCarts);
      char isHaveAccount = ' ';
      if (!isLogin) {
        isHaveAccount = readQuestionInput("У вас уже есть аккаунт?");
        if (isHaveAccount == 'y' || isHaveAccount == 'у') {
          auth(order);
        } else {
          createAccount(order);
        }
      } else {
        User user = authUser;
        addOrderToUser(user, order);
      }
    }
  }

  public static boolean createAccount(Map<Integer, List<ProductCart>> order) {
    String name = "";
    String email = "";
    String password = "";

    boolean isAddedUser = false;
    while (!isAddedUser) {
      name = readStringInput("Введите ваше имя: ", 0);
      email = readStringInput("Введите ваш email: ", 6, true);
      password = readStringInput("Введите ваш пароль: ", 4);
      isAddedUser = control.addUser(new User(name, email, password));
    }

    User user = control.getUserByEmail(email);
    isLogin = true;
    authUser = user;
    user.setOrders(order);
    control.updateUserData(user);
    System.out.println(
        "Заказ оформлен ! Подробности отправлены на " + Colors.PURPLE.getColor() + email
            + Colors.RESET.getColor()
    );

    continueShopping();

    return false;
  }

  public static void auth(Map<Integer, List<ProductCart>> order) {
    String email = readStringInput("Введите ваш email: ", 6, true);
    String password = readStringInput("Введите ваш пароль: ", 4);
    do {
      User user = control.login(email, password);
      addOrderToUser(user, order);
    } while (!control.authorization(email, password));

  }

  public static void addOrderToUser(User user, Map<Integer, List<ProductCart>> order) {
    Map<Integer, List<ProductCart>> oUser = user.getOrders();
    for (Integer key : order.keySet()) {
      oUser.put(key, order.get(key));
    }
    System.out.println(
        "Рады, что вы снова нас посетили " + Colors.CYAN.getColor() + user.getName()
            + Colors.RESET.getColor());
    user.setOrders(oUser);
    control.updateUserData(user);
    continueShopping();
  }

  public static void continueShopping() {
    System.out.println("Спасибо за покупку");
    char isContinueShopping = readQuestionInput("Хотите купить что-то ещё ?");
    if (isContinueShopping == 'y' || isContinueShopping == 'у') {
      ChoseCategory.runShop();
    } else {
      System.out.println("Очень жаль \uD83D\uDE1E");
      System.out.println("Поздравляем вас с покупками, всего наилучшего!!!");
      System.out.println("\uD83E\uDD73 \uD83E\uDD73 \uD83E\uDD73");
      System.exit(0);
    }
  }
}
