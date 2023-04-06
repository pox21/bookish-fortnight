package product;

public class Product {

  protected String title;
  protected long id;
  protected int price;
  protected String memory;
  protected String category;
  protected int article;

  public Product(String title, long id, int price, String memory, String category, int article) {
    this.title = title;
    this.id = id;
    this.price = price;
    this.memory = memory;
    this.category = category;
    this.article = article;
  }

  public int getArticle() {
    return article;
  }

  public void setArticle(int article) {
    this.article = article;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getMemory() {
    return memory;
  }

  public void setMemory(String memory) {
    this.memory = memory;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "{ " +
        "title = '" + title + '\'' +
        ", id = " + id +
        ", price = " + price +
        ", memory = '" + memory + '\'' +
        ", category = '" + category + '\'' +
        '}';
  }
}
