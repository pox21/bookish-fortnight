import chooseCategory.ChoseCategory;
import parser.GsonParser;
import product.Category;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Получаем данные всех товаров по категориям
        List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");

        assert categories != null;
        ChoseCategory.fill(categories);

//        System.out.printf(
//            "%sHello%s %sworld%s %s!%s%n",
//            Colors.YELLOW.getColor(), Colors.RESET.getColor(),
//            Colors.CYAN.getColor(), Colors.RESET.getColor(),
//            Colors.PURPLE.getColor(), Colors.RESET.getColor()
//        );

//        System.out.printf("%s%11sDEVICE STORE%11s%s%n", Colors.YELLOW.getColor(), "", "", Colors.RESET.getColor());
//        System.out.printf("%s%8s== Наименование ==%8s%s%n", Colors.PURPLE.getColor(), "", "", Colors.RESET.getColor());
//        System.out.printf("%s%5s>>> Категории товаров <<<%4s%s%n", Colors.PURPLE.getColor(), "", "", Colors.RESET.getColor());
//        System.out.printf("%s%s%s%n", Colors.CYAN.getColor(), "-".repeat(30), Colors.RESET.getColor());
//        for (Category category : categories) {
//            System.out.printf(
//                "%s%d%s: %s- %s -%s %n",
//                Colors.RED.getColor(), category.getId(), Colors.RESET.getColor(),
//                Colors.BLUE.getColor(), category.getTitle(), Colors.RESET.getColor()
//            );
//        }
//        System.out.printf("%s%s%s%n", Colors.CYAN.getColor(), "-".repeat(30), Colors.RESET.getColor());
//        System.out.printf("%sВыберите категорию: %s", Colors.GREEN.getColor(), Colors.RESET.getColor());

    }
}
