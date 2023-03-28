package cart;


import product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public List<ProductCart> products = new ArrayList<>();

    public void addProductToCart(Product product) {
        ProductCart productCart = new ProductCart(
            product.getId(),
            product.getTitle(),
            product.getPrice(),
            product.getDescription(),
            product.getCategory(),
            product.getImage(),
            product.getRating()
        );
        if (products.size() < 1) {
            products.add(productCart);
        } else {
            for (ProductCart p : products) {
                if (product.getId() == p.getId()) {
                    p.setAmount(p.getAmount() + 1);
                    return;
                }
            }
            products.add(productCart);
        }
    }

    public void removeProductToCart(ProductCart product) {
        products.remove(product);
    }


}
