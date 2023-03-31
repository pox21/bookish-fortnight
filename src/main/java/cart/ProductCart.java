package cart;

import product.Product;

public class ProductCart extends Product {
    protected int amount = 1;
    protected int totalPrice = 0;

    public ProductCart(long id, String title, int price, String memory, String category, int article) {
        super(title, id, price, memory, category, article);
    }

    public ProductCart(Product product) {
        super(product.getTitle(), product.getId(), product.getPrice(), product.getMemory(), product.getCategory(), product.getArticle());
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = this.price * this.amount;;
    }

    @Override
    public String toString() {
        return "Product {" +
                "\n\tid: " + id +
                ",\n\ttitle: '" + title + '\'' +
                ",\n\tamount: " + amount +
                ",\n\tprice: " + (price * amount) +
                ",\n\tdescription: '" + memory + '\'' +
                ",\n\tcategory: '" + category + '\'' +
                '}';
    }
}
