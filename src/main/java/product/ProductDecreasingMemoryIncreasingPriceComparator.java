package product;

import java.util.Comparator;

// По уменьшению объема памяти. Если они равны, то по увеличению цены
public class ProductDecreasingMemoryIncreasingPriceComparator implements Comparator<Product> {

  @Override
  public int compare(Product o1, Product o2) {
    if (!o1.getMemory().equals(o2.getMemory())) {
      return -(o1.getMemory().compareTo(o2.getMemory()));
    }
    return o1.getPrice() - o2.getPrice();
  }
}
