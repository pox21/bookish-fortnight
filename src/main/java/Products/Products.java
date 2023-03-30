package Products;

import parser.GsonParser;
import product.Category;
import product.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import product.ProductDecreasingMemoryIncreasingPriceComparator;
import product.ProductDecreasingPriceDecreasingMemoryComparator;
import product.ProductIncreasingPriceDecreasingMemoryComparator;
import product.ProductIncreasingTitleIncreasingPriceComparator;
import utils.Colors;

import static ForDina.MyShoppingCart.getMyProduct;
import static chooseCategory.ChoseCategory.fill;

public class Products {

    static String colorReset = Colors.RESET.getColor();
    static String colorPurple = Colors.PURPLE.getColor();
    static String colorCyan = Colors.CYAN.getColor();
    static String colorGreen = Colors.GREEN.getColor();
    static List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");
    private static int quantities = 1;
    private static final List<Product> products = GsonParser.parseProducts("src/main/resources/products.json");
    private static final Map<String, List<Product>> myProducts = new HashMap<>();
    private static final List<Product> selectedProduct = new ArrayList<>();

    public static void getProductsByCategory(String category) throws IOException { // получает все продукты по категории

        showProductsByCategory(category); //показывает продукты по категориям
        System.out.println();
        System.out.println("Хотите отсортировать товары?");
        System.out.print("Введите 'Y', если 'да' или N, если 'нет': ");

        sortProducts(category); // сортирует товары
        assert products != null;
        getMySelectProducts(products);
    }

    public static void getMySelectProducts(List<Product> products) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" ");
        System.out.print("Введите артикул товара: ");

        boolean addMore = true;
        try {
            do {
                int select = Integer.parseInt(br.readLine());

                for (Product product : products) {
                    boolean article = product.getArticle() == select;

                    if (article) {
                        System.out.print("Добавить в корзину: " + colorGreen + product.getTitle() + colorReset + ". Введите количество: ");

                        do {
                            quantities = Integer.parseInt(br.readLine());
                            if (quantities > 0) {
                                for (int i = 0; i < quantities; i++) {
                                    selectedProduct.add(product);
                                }
                                myProducts.put("MY-DEVICE", selectedProduct);
                            } else {
                                System.out.print("Добавить в корзину: " + colorGreen + product.getTitle() + colorReset + ". Введите количество больше 0: ");
                            }
                        } while (quantities == 0 || quantities < 0);
                    }
                }
                System.out.print("Хотите добавить еще товар из данной категории? Нажмите Y/N: ");
                String yOrN = br.readLine();

                if (yOrN.equalsIgnoreCase("N")) {
                    System.out.print(" Хотите вернуться к категориям товаров? Нажмите Y/N: ");
                    String yOrNCategory = br.readLine();
                    if (yOrNCategory.equalsIgnoreCase("Y")) {
                        fill(categories);
                        return;
                    }
                    addMore = false;
                } else if (yOrN.equalsIgnoreCase("Y")) {
                    getMySelectProducts(products);
                    return;
                }
            } while (addMore);

            getMyProduct(selectedProduct);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void sortProducts(String category) throws IOException {
        String choice = null;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            choice = br.readLine();
            if (choice.equalsIgnoreCase("N")) {
                assert products != null;
                showProductsByCategory(category);
            } else if (choice.equalsIgnoreCase("Y")) {
                System.out.println();
                System.out.println("Сортировать по: ");
                System.out.println("\t - возрастанию цены:" + colorPurple + " '1'" + colorReset);
                System.out.println("\t - убыванию цены:" + colorPurple + " '2'" + colorReset);
                System.out.println("\t - объему памяти:" + colorPurple + " '3'" + colorReset);
                System.out.println("\t - названию:" + colorPurple + " '4'" + colorReset);
                System.out.print(colorCyan + "введите число: " + colorReset);
                int sortChoice = Integer.parseInt(br.readLine());
                switch (sortChoice) {
                    case 1 -> {
                        assert products != null;
                        products.sort(new ProductIncreasingPriceDecreasingMemoryComparator());
                        showProductsByCategory(category);
                    }
                    case 2 -> {
                        assert products != null;
                        products.sort(new ProductDecreasingPriceDecreasingMemoryComparator());
                        showProductsByCategory(category);
                    }
                    case 3 -> {
                        assert products != null;
                        products.sort(new ProductDecreasingMemoryIncreasingPriceComparator());
                        showProductsByCategory(category);
                    }
                    case 4 -> {
                        assert products != null;
                        products.sort(new ProductIncreasingTitleIncreasingPriceComparator());
                        showProductsByCategory(category);
                    }
                }
            } else {
                System.out.print("Введите 'Y', если 'да' или N, если 'нет': ");
            }
        }
        while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N"));
    }

    public static void showProductsByCategory(String category) {
        System.out.println(Colors.YELLOW.getColor() + "-".repeat(43));
        System.out.printf("%7s | %15s | %6s | %6s%n", "Артикул", "Наименование", "Память", "Цена");
        System.out.println("-".repeat(43));
        assert products != null;
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                System.out.printf("%s%7s | ", Colors.GREEN.getColor(), product.getArticle());
                System.out.printf("%15s | ", product.getTitle());
                System.out.printf("%6s | ", product.getMemory());
                System.out.printf("%s%6s€%s%n", Colors.PURPLE.getColor(), product.getPrice(), colorReset);
            }
        }
    }

    public static void showProductsCart(List<Product> products) {
        System.out.println(Colors.YELLOW.getColor() + "-".repeat(50));
        System.out.printf(" №  | %7s | %15s | %6s | %6s%n", "Артикул", "Наименование", "Память", "Цена");
        System.out.println("-".repeat(50) + colorReset);
        int count = 1;
        for (Product product : products) {
            System.out.printf("%s%2s | ", Colors.GREEN.getColor(), count);
            System.out.printf("%7s | ", product.getArticle());
            System.out.printf("%15s | ", product.getTitle());
            System.out.printf("%6s | ", product.getMemory());
            System.out.printf("%s%6s€%s%n", Colors.PURPLE.getColor(), product.getPrice(), colorReset);
            count++;
        }
    }
}



