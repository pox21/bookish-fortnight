import com.google.gson.Gson;
import parser.GsonParser;
import product.Category;
import product.Product;

import java.util.*;

public class Main {

    static Map<String, List<Product>> categoryProducts = new HashMap<>();
    static List<Product> products = new ArrayList<>();
    static List<Category> categories = new ArrayList<>();

    public static void fill() { // заполняет мапу категория -> список товаров данной категории
        for (Category category : categories) {
            categoryProducts.put(category.getTitle(), getProductsByCategory(category.getTitle()));
        }
    }

    public static List<Product> getProductsByCategory(String category) { // получает все продукты по категории
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

    public static void printCategories(List<Category> categories) { // печатает категории
        for (Category category : categories) {
            System.out.println(category.getTitle());
        }
    }

    public static void printProducts(List<Product> products) {
        System.out.println("-".repeat(43));
        System.out.printf("%7s | %15s | %6s | %6s%n", "Артикль", "Наименование", "Память", "Цена");
        System.out.println("-".repeat(43));
        for (Product product : products) {
            System.out.printf("%7s | ", product.getArticle());
            System.out.printf("%15s | ",product.getTitle());
            System.out.printf("%6s | ", product.getMemory());
            System.out.printf("%6s%n", product.getPrice());
        }
        System.out.println("-".repeat(43));
    }
    public static void main(String[] args) {
        // Получаем данные
        categories = GsonParser.parseCategories("src/main/resources/categories.json");
        products = GsonParser.parseProducts("src/main/resources/products.json");
        fill();
        // и записываем их в локальные переменные(Списки, Мапы)

        // вывод всех товаров по категориям
        for (String product : categoryProducts.keySet()) {
            System.out.println("========= " + product + " ========");
            printProducts(categoryProducts.get(product));
        }

        System.out.println();
        // получаем нужную категорию и выводим список товаров категории
        System.out.println("========= Компьютеры ========");
        printProducts(getProductsByCategory("Компьютеры"));

    }


}
