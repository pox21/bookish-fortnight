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


import utils.Colors;

import static ForDina.MyShoppingCart.getMyProduct;
import static chooseCategory.ChoseCategory.fill;
import static Products.showProducts.showProducts.showProductsByCategory;
import static Products.sortProducts.sortProducts.sortProducts;

public class Products {
    static String colorRed = Colors.RED.getColor();
    static String colorReset = Colors.RESET.getColor();
    static String colorGreen = Colors.GREEN.getColor();

    static List<Category> categories = GsonParser.parseCategories("src/main/resources/categories.json");
    private static final List<Product> products = GsonParser.parseProducts("src/main/resources/products.json");
    private static final Map<String, List<Product>> myProducts = new HashMap<>();
    private static final List<Product> selectedProduct = new ArrayList<>();

    public static void getProductsByCategory(String category) throws IOException { // получает все продукты по категории

        assert products != null;
        showProductsByCategory(category,products); //показывает продукты по категориям
        System.out.println();
        System.out.println("Хотите отсортировать товары?");
        System.out.print("Введите 'Y', если 'да' или N, если 'нет': ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String choice = br.readLine().trim();

        if(choice.equalsIgnoreCase("Y")){
            // сортирует товары
            sortProducts(category,products);
            return;
        }
        if (choice.isEmpty()) {
            System.out.println(colorRed+ "Некоректный ввод "+colorReset);
            getProductsByCategory(category);
            return;
        }

        getMySelectProducts(products);
    }

    public static void getMySelectProducts(List<Product> products) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите артикул товара: ");
        boolean addMore = true;
        try{
            do {
                int select = Integer.parseInt(br.readLine());

                boolean foundProduct = false;
                for (Product product : products) {
                    if (product.getArticle() == select) {
                        foundProduct = true;
                        System.out.print("Добавить в корзину: " + colorGreen + product.getTitle() + colorReset + ". Введите количество: ");

                        myProducts.put("MY-DEVICE", getQuantitiesOfProducts(product));
                    }
                }

                if (!foundProduct) {
                    System.out.print(colorGreen+"Товар с артикулом "+colorRed + select + " не найден."+colorGreen+" Попробуйте снова: "+colorReset);
                    continue;
                }

                System.out.print("Хотите добавить еще товар из данной категории? Нажмите Y/N: ");
                String yOrN = br.readLine().trim();
                if (yOrN.equalsIgnoreCase("Y")) {
                    getMySelectProducts(products);
                    return;
                }

                    System.out.print("Хотите вернуться к категориям товаров? Нажмите Y/N: ");
                    String yOrNCategory = br.readLine().trim();
                    if (yOrNCategory.equalsIgnoreCase("Y")) {
                        fill(categories);
                        return;
                    }
                    addMore = false;

            } while (addMore);
            getMyProduct(myProducts.get("MY-DEVICE"));

        } catch (IOException e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }catch (NumberFormatException e){
            System.out.println(colorRed+"Ошибка: Можно ввести только артикль товара "+colorReset);
            getMySelectProducts(products);
        }

    }


    public static List<Product> getQuantitiesOfProducts(Product product) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int quantities;
        do {
            try {
                quantities = Integer.parseInt(br.readLine());
                if (quantities > 0) {
                    for (int i = 0; i < quantities; i++) {
                        selectedProduct.add(product);
                    }
                } else {
                    System.out.print(colorRed+"Введите количество больше 0: "+colorReset);
                }
            } catch (NumberFormatException e) {
                System.out.print(colorRed+"Ошибка: введите число больше 0: "+colorReset);
                quantities = 0;
                br.readLine(); // Очистим буфера ввода
            }
        } while (quantities == 0 || quantities < 0);
        return selectedProduct;
    }

}



//    public static void getMySelectProducts(List<Product> products) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println(" ");
//        System.out.print("Введите артикул товара: ");
//
//        boolean addMore = true;
//        try {
//            do {
//                int select = Integer.parseInt(br.readLine());
//
//                boolean foundProduct = false;
//                for (Product product : products) {
//                    if (product.getArticle() == select) {
//                        foundProduct = true;
//                        System.out.print("Добавить в корзину: " + colorGreen + product.getTitle() + colorReset + ". Введите количество: ");
//
//                        int quantities;
//                        do {
//                            try {
//                                quantities = Integer.parseInt(br.readLine());
//                                if (quantities > 0) {
//                                    List<Product> selectedProduct = new ArrayList<>();
//                                    for (int i = 0; i < quantities; i++) {
//                                        selectedProduct.add(product);
//                                    }
//                                    myProducts.put("MY-DEVICE", selectedProduct);
//                                } else {
//                                    System.out.print(colorRed+"Введите количество больше 0: "+colorReset);
//                                }
//                            } catch (NumberFormatException e) {
//                                System.out.print(colorRed+"Ошибка: введите число больше 0: "+colorReset);
//                                quantities = 0;
//                                br.readLine(); // Очистка буфера ввода
//                            }
//                        } while (quantities == 0 || quantities < 0);
//                    }
//                }
//
//                if (!foundProduct) {
//                    System.out.print("Товар с артикулом " + select + " не найден. Попробуйте снова: ");
//                    continue;
//                }
//
//                System.out.print("Хотите добавить еще товар из данной категории? Нажмите Y/N: ");
//                String yOrN = br.readLine();
//
//                if (yOrN.equalsIgnoreCase("N")) {
//                    System.out.print("Хотите вернуться к категориям товаров? Нажмите Y/N: ");
//                    String yOrNCategory = br.readLine();
//                    if (yOrNCategory.equalsIgnoreCase("Y")) {
//                        fill(categories);
//                        return;
//                    }
//                    addMore = false;
//                } else if (yOrN.equalsIgnoreCase("Y")) {
//                    getMySelectProducts(products);
//                    return;
//                }
//            } while (addMore);
//
//            getMyProduct(myProducts.get("MY-DEVICE"));
//
//        } catch (IOException e) {
//            System.out.println( e.getMessage());
//            e.printStackTrace();
//        }catch (NumberFormatException e){
//            System.out.println(colorRed+"Ошибка: Можно ввести только артикль товара "+colorReset);
//            getMySelectProducts(products);
//        }
//    }
