package cart;

import product.Product;
import product.Rating;

public class ProductCart extends Product {
    private int amount = 1;

    public ProductCart(int id, String title, double price, String description, String category, String image, Rating rating) {
        super(id, title, price, description, category, image, rating);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public double getPrice() {
        return this.price * this.amount;
    }

    @Override
    public String toString() {
        return "Product {" +
                "\n\tid: " + id +
                ",\n\ttitle: '" + title + '\'' +
                ",\n\tamount: " + amount +
                ",\n\tprice: " + (price * amount) +
                ",\n\tdescription: '" + description + '\'' +
                ",\n\tcategory: '" + category + '\'' +
                ",\n\timage: '" + image + '\'' +
                ",\n\trating: " + rating + "\n" +
                '}';
    }
}
