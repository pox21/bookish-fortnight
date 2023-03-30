package order;

import cart.ProductCart;
import chooseCategory.ChoseCategory;
import parser.GsonParser;
import product.Category;
import product.Product;
import user.User;
import user.UsersControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Order {
// Оформление заказа

    public static String confirmInput(String str, int limit) {
        String response = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (response == null || response.isEmpty()) {
            try {
                System.out.println(str);
                response = br.readLine().trim();
                if (response.isEmpty()) {
                    System.out.println(str + " - не должно пустым");
                    response = br.readLine().trim();
                } else if (limit != 0 && response.length() < limit) {
                    str = "Значение не может быть меньше 4-х символов";
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
        char isConfirm = confirmInput("y - да, n - нет", 0).toLowerCase().charAt(0);
        if (isConfirm != 'y') {
            List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");
            assert categories != null;
            System.out.println("Вернуться на главную");
            ChoseCategory.fill(categories);
        } else {
            char isHaveAccount = confirmInput("У вас уже есть аккаунт ? y - да, n - нет", 0).toLowerCase().charAt(0);
            UsersControl control = new UsersControl();
            if (isHaveAccount != 'y') {
                String name = confirmInput("Введите ваше имя: ", 0);
                String email = confirmInput("Введите ваше email: ", 6);
                String password = confirmInput("Введите ваше пароль: ", 4);

                if (control.addUser(new User(name, email, password))) {
                    User user = control.getUserByEmail(email);
                    user.setOrders(productCarts);
                    control.updateUserData(user);
                }
            } else {
                String email = confirmInput("Введите ваше email: ", 6);
                String password = confirmInput("Введите ваше пароль: ", 4);
                if (control.authorization(email, password)) {
                    User user = control.login(email, password);
                    user.setOrders(productCarts);

                    System.out.println("Спасибо за покупку");
                }
            }
        }

    }

}
