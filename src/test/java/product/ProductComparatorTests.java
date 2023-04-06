package product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ProductComparatorTests {

  @Test
  public void ProductDecreasingMemoryIncreasingPriceComparator() {

    Product product1 = new Product("Iphone 7", 1, 750, "256 gb", "phone", 2);
    Product product2 = new Product("Iphone 8", 2, 850, "256 gb", "phone", 2);
    Product product3 = new Product("Iphone 12", 3, 900, "512 gb", "phone", 2);

    List<Product> actual = new ArrayList<>();
    actual.add(product1);
    actual.add(product2);
    actual.add(product3);

    List<Product> expected = new ArrayList<>();
    expected.add(product3);
    expected.add(product1);
    expected.add(product2);

    actual.sort(new ProductDecreasingMemoryIncreasingPriceComparator());

    assertEquals(expected, actual);
  }

  @Test
  public void ProductDecreasingPriceDecreasingMemoryComparator() {

    Product product1 = new Product("Iphone 7", 1, 750, "256 gb", "phone", 2);
    Product product2 = new Product("Iphone 8", 2, 900, "256 gb", "phone", 2);
    Product product3 = new Product("Iphone 12", 3, 900, "512 gb", "phone", 2);

    List<Product> actual = new ArrayList<>();
    actual.add(product1);
    actual.add(product2);
    actual.add(product3);

    List<Product> expected = new ArrayList<>();
    expected.add(product3);
    expected.add(product2);
    expected.add(product1);

    actual.sort(new ProductDecreasingPriceDecreasingMemoryComparator());

    assertEquals(expected, actual);
  }

  @Test
  public void ProductIncreasingPriceDecreasingMemoryComparator() {

    Product product1 = new Product("Iphone 7", 1, 750, "128 gb", "phone", 2);
    Product product2 = new Product("Iphone 8", 2, 750, "256 gb", "phone", 2);
    Product product3 = new Product("Iphone 12", 3, 900, "512 gb", "phone", 2);

    List<Product> actual = new ArrayList<>();
    actual.add(product3);
    actual.add(product2);
    actual.add(product1);

    List<Product> expected = new ArrayList<>();
    expected.add(product2);
    expected.add(product1);
    expected.add(product3);

    actual.sort(new ProductIncreasingPriceDecreasingMemoryComparator());

    assertEquals(expected, actual);
  }

  @Test
  public void ProductIncreasingTitleIncreasingPriceComparator() {

    Product product1 = new Product("Iphone 12", 1, 1000, "128 gb", "phone", 2);
    Product product2 = new Product("Iphone 8", 2, 900, "256 gb", "phone", 2);
    Product product3 = new Product("Iphone 8", 3, 950, "512 gb", "phone", 2);

    List<Product> actual = new ArrayList<>();
    actual.add(product3);
    actual.add(product2);
    actual.add(product1);

    List<Product> expected = new ArrayList<>();
    expected.add(product1);
    expected.add(product2);
    expected.add(product3);

    actual.sort(new ProductIncreasingTitleIncreasingPriceComparator());

    assertEquals(expected, actual);
  }
}
