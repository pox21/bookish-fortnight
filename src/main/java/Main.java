import chooseCategory.ChoseCategory;
import parser.GsonParser;
import product.Category;
import utils.Colors;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Получаем данные всех товаров по категориям
        List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");

        assert categories != null;
        ChoseCategory.fill(categories);

//        System.out.printf(
//            "%sHello%s %sworld%s %s!%s",
//            Colors.YELLOW.getColor(), Colors.RESET.getColor(),
//            Colors.CYAN.getColor(), Colors.RESET.getColor(),
//            Colors.PURPLE.getColor(), Colors.RESET.getColor()
//        );
    }
}
