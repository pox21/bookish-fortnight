
package chooseCategory;

import exceptions.MyIOException;
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
    static String colorReset = Colors.RESET.getColor();
    static String colorYellow = Colors.YELLOW.getColor();
    static String colorCyan = Colors.CYAN.getColor();
    static String colorRed = Colors.RED.getColor();
    static String colorPurple = Colors.PURPLE.getColor();
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
                    "%s%d%s: %s- %s  %s %n",
                    colorRed, category.getId(), colorReset,
                    colorCyan, category.getTitle(),category.getIcon() ,colorReset
            );
            categoryProducts.put(category.getId(), category.getTitle());
        }
        System.out.printf("%s%s%s%n", colorCyan, "-".repeat(35), colorReset);
        try {
            System.out.println("У вас есть аккаунт ?");
            addOurCategory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addOurCategory() throws IOException {
        // получаем категорию
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("%sВыберите категорию: %s", Colors.GREEN.getColor(), colorReset);
        int select = 0;
        try {
            select = Integer.parseInt(br.readLine().trim());
            if ((select <= 0) || (select>=4) ) {
                throw new MyIOException(" ", select);
            }
        } catch (MyIOException e) {
            System.out.println(colorRed + " Некорректный ввод : " + e.getMessage() + colorReset);
            addOurCategory();
        } catch (NumberFormatException e) {
            System.out.println(colorRed + " Некорректный ввод : введите номер категории " + colorReset);

            addOurCategory();
        }

        for (Map.Entry<Integer, String> product : categoryProducts.entrySet()) {
            boolean selected = product.getKey() == select;
            if (selected) {
//передаем дальше что бы получить список товаров данной категории
                System.out.println(" ");
                System.out.println(colorYellow + "================== " + product.getValue() + " ==================" + colorReset);
                Products.getProductsByCategory(product.getValue());
            }
        }
    }
}