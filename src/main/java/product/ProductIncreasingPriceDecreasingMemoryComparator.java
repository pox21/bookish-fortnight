package product;

import java.util.Comparator;

// По увеличению цены. Если цена одинаковая, то по объему памяти по убыванию
public class ProductIncreasingPriceDecreasingMemoryComparator implements Comparator<Product> {
  @Override
  public int compare(Product o1, Product o2) {
    if (o1.getPrice() != o2.getPrice()){
      return o1.getPrice() - o2.getPrice();
    }
    return -(o1.getMemory().compareTo(o2.getMemory())); // память по убыванию
  }
}
