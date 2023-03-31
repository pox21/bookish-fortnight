package cart;


import product.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static List<ProductCart> products = new ArrayList<>();

    public static void addProductToCart(List<Product> selectedProducts) {
        for (Product product : selectedProducts) {
            ProductCart productCart = new ProductCart(
                    product.getId(),
                    product.getTitle(),
                    product.getPrice(),
                    product.getMemory(),
                    product.getCategory(),
                    product.getArticle()
            );
            boolean isAdded = false;
            for (ProductCart cart : products) {
                if (cart.getId() == product.getId()) {
                    cart.setAmount(cart.getAmount() + 1);
                    isAdded = true;
                }
            }
            if (!isAdded) {
                products.add(productCart);
            }
            isAdded = false;
        }
    }

    public void removeProductToCart(ProductCart product) {
        products.remove(product);
    }

    public void removeProducts() {
        products.clear();
    }

}
