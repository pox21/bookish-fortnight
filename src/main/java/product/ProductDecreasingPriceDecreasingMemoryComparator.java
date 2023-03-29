package product;

import java.util.Comparator;

// По убыванию цены. Если цена одинаковая, то по уменьшению объема памяти
public class ProductDecreasingPriceDecreasingMemoryComparator implements Comparator<Product> {
  @Override
  public int compare(Product o1, Product o2) {
    if (o1.getPrice() != o2.getPrice()) {
      return o2.getPrice()-o1.getPrice();
    }
    return -(o1.getMemory().compareTo(o2.getMemory())); // память по убыванию
  }
}
