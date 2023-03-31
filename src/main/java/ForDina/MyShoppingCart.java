package ForDina;

import cart.Cart;
import product.Product;
import Products.Products;
import utils.Colors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static Products.showProducts.showProducts.showProductsCart;
import static order.Order.confirmOrder;

public class MyShoppingCart {
    static String colorReset = Colors.RESET.getColor();
    static String colorRed = Colors.RED.getColor();
    static String colorCyan = Colors.CYAN.getColor();

    public static void getMyProduct(List<Product> selectedProduct) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        printCart(selectedProduct);

        System.out.print("Хотите удалить все товары из корзины? (y - 'да', n - 'нет'): ");
        String s = br.readLine();
        if (s.equalsIgnoreCase("Y")) {
            selectedProduct.clear();
            printCart(selectedProduct);
            cart.Cart.addProductToCart(selectedProduct);
        }

        if (selectedProduct.size() > 0) removeFromCart(selectedProduct);
    }

    public static void printCart(List<Product> selectedProduct) {
        int sum = 0;

        for (Product product : selectedProduct) {
            sum += product.getPrice();
        }

        if (selectedProduct.size() == 0) {
            System.out.println("Ваша корзина пуста");
        } else {
            System.out.println(colorCyan+"В вашей корзине: "+Colors.PURPLE.getColor() + selectedProduct.size() + " " + getGoodsAddition(selectedProduct.size()) +colorCyan+ ", на сумму: "+Colors.GREEN.getColor() + sum + " €" + colorReset);
            showProductsCart(selectedProduct);
        }
    }

    public static void removeFromCart(List<Product> selectedProduct) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.print("Хотите удалить конкретный товар? (y - 'да', n - 'нет'): ");
            String yOrN = br.readLine();
            if (yOrN.equalsIgnoreCase("Y")) {
                try {
                    System.out.print("Какой товар вы хотели бы удалить? Введите порядковый номер: ");
                    int select = Integer.parseInt(br.readLine());
                    selectedProduct.remove(selectedProduct.get(select - 1));
                    printCart(selectedProduct);
                    cart.Cart.addProductToCart(selectedProduct);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(colorRed+"Введите корректное значение!"+colorReset);
                }

            }
            if (yOrN.equalsIgnoreCase("N")) {
                printCart(selectedProduct);
                cart.Cart.addProductToCart(selectedProduct);
                confirmOrder(cart.Cart.products);
                break;
            }
        } while (selectedProduct.size() > 0);
    }

    public static String getGoodsAddition(int num) {
        int preLastDigit = num % 100 / 10;

        if (preLastDigit == 1) {
            return "товаров";
        }

        return switch (num % 10) {
            case 1 -> "товар";
            case 2, 3, 4 -> "товара";
            default -> "товаров";
        };

    }
}
