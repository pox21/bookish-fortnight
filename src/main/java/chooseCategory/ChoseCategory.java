
package chooseCategory;

import cart.ProductCart;
import exceptions.MyIOException;
import product.Category;
import products.Products;
import user.User;
import user.UsersControl;
import utils.Colors;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static products.showProducts.ShowProducts.showOrders;
import static utils.ReadInput.readStringInput;

public class ChoseCategory {
    static String colorReset = Colors.RESET.getColor();
    static String colorYellow = Colors.YELLOW.getColor();
    static String colorCyan = Colors.CYAN.getColor();
    static String colorRed = Colors.RED.getColor();
    static String colorPurple = Colors.PURPLE.getColor();

    public static UsersControl control = new UsersControl();
    public static User user;
    private static final Map<Integer, String> categoryProducts = new HashMap<>();

    // Выводим категории в консоль
    public static void fill(List<Category> categories) {

        System.out.println(" ");
        System.out.printf("%s%11sDEVICE STORE%11s%s%n", colorYellow, "", "", colorReset);
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
        try {
            char isAccountHas = readStringInput("У вас уже есть аккаунт? (y - 'да', n - 'нет'): ", 0).toLowerCase().charAt(0);
            if (isAccountHas == 'y') {
                userPanel();
            }
            addOurCategory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addOurCategory() throws IOException {
        String formatInfo = String.format("%sВыберите категорию: %s", Colors.GREEN.getColor(), colorReset);
        // получаем категорию
        int select = readInt(formatInfo, categoryProducts.size());

        for (Map.Entry<Integer, String> product : categoryProducts.entrySet()) {
            boolean selected = product.getKey() == select;
            if (selected) {
//передаем дальше что бы получить список товаров данной категории
                System.out.println();
                System.out.printf(
                    "%s%s %-8s %s%s%n",
                    colorYellow, "=".repeat(17), product.getValue(),"=".repeat(17), colorReset);
                Products.getProductsByCategory(product.getValue());
            }
        }
    }

    public static void userPanel() throws IOException {
        if (!auth()) return;
        if (user.getOrders().size() > 0) {
            char isShowOrders = readStringInput("Хотите посмотреть свои покупки? (y - 'да', n - 'нет'): ", 0).toLowerCase().charAt(0);
            if (isShowOrders == 'y') {
                System.out.println(colorPurple + ">> " + user.getName() + " <<" + colorReset);
                showOrders(user.getOrders());
            }
        }
    }

    public static boolean auth() {
        boolean isAuth = false;
        int i =0;
        do {
            String email = readStringInput("Введите ваш email: ", 6);
            String password = readStringInput("Введите ваш пароль: ", 4);
            isAuth = control.authorization(email, password);
            i++;
            if (isAuth) {
                user = control.login(email, password);
                System.out.println("Вы вошли под именем " + Colors.CYAN.getColor() + user.getName() + Colors.RESET.getColor());
                return true;
            }
        } while (i<3);

        System.out.println(colorYellow + "Нам кажется вы мошенник \uD83D\uDE11! или забыли свои данные, попробуйте как-нибудь в другой раз \uD83D\uDE09" + colorReset);
        return false;
    }



    // метод для принятия ввода с клавиатуры
    public static int readInt(String info, int limit) {
        int response = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (response == 0 || response > limit) {
            try {
                System.out.print(info);
                response = Integer.parseInt(br.readLine().trim());
                if (response < 1 || response > limit) {
                    throw new MyIOException(" ", response);
                }
            } catch (IOException | MyIOException e) {
                System.out.println(colorRed + " Некорректный ввод : " + e.getMessage() + colorReset);
            }  catch (NumberFormatException e) {
                System.out.println(colorRed + " Некорректный ввод : введите номер категории " + colorReset);
            }
        }

        return response;
    }
}