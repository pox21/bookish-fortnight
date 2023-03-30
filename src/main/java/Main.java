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

    }
}
