package order;

import cart.ProductCart;
import chooseCategory.ChoseCategory;
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
    static List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");


    public static String confirmInput(String str, int limit) {
        String response = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (response == null || response.isEmpty()) {
            try {
                System.out.print(str);
                response = br.readLine().trim();
                if (response.isEmpty()) {
                    System.out.println(str + " - не должно пустым");
                    response = br.readLine().trim();
                } else if (limit != 0 && response.length() < limit) {
                    str = "Значение не может быть меньше " + limit + " символов";
                    response = null;
                }
            } catch (IOException e) {
                System.out.print("Что-то пошло не так... " + str);
            }
        }
        return response;
    }

    public static void confirmOrder(List<ProductCart> productCarts) {
        System.out.println("Оформить заказ ?");
        char isConfirm = confirmInput(" y - 'да', n - 'нет'", 0).toLowerCase().charAt(0);
        if (isConfirm != 'y') {
            assert categories != null;
            System.out.println("Вернуться на главную");
            ChoseCategory.fill(categories);
        } else {
            char isHaveAccount = confirmInput("У вас уже есть аккаунт? (y - 'да', n - 'нет'): ", 0).toLowerCase().charAt(0);
            if (isHaveAccount != 'y') {
                createAccount(productCarts);
            } else {
                auth(productCarts);
            }
        }
    }

    public static boolean createAccount(List<ProductCart> productCarts) {
        String name = "";
        String email = "";
        String password = "";

        boolean isAddedUser = false;
        while (!isAddedUser) {
            name = confirmInput("Введите ваше имя: ", 0);
            email = confirmInput("Введите ваш email: ", 6);
            password = confirmInput("Введите ваш пароль: ", 4);
            isAddedUser = control.addUser(new User(name, email, password));
        }

        User user = control.getUserByEmail(email);
        user.setOrders(productCarts);
        control.updateUserData(user);
        System.out.println(
                "Заказ оформлен ! Подробности отправлены на " + Colors.PURPLE.getColor() + email + Colors.RESET.getColor()
        );

        continueShopping();

        return false;
    }

    public static void auth(List<ProductCart> productCarts) {
        String email = confirmInput("Введите ваш email: ", 6);
        String password = confirmInput("Введите ваш пароль: ", 4);
        do {
            User user = control.login(email, password);
            System.out.println("Рады, что вы снова нас посетили " + Colors.CYAN.getColor() + user.getName() + Colors.RESET.getColor());
            user.setOrders(productCarts);
            control.updateUserData(user);
            continueShopping();
        } while (!control.authorization(email, password));

    }

    public static void continueShopping () {
        System.out.println("Спасибо за покупку");
        char isContinueShopping = confirmInput("Хотите купить что-то ещё ? (y - да, n - нет): ", 0).toLowerCase().charAt(0);
        if (isContinueShopping == 'n') {
            System.out.println("Очень жаль \uD83D\uDE1E");
            System.out.println("Поздравляем вас с покупками");
        } else {
            ChoseCategory.fill(categories);
        }
    }
}
