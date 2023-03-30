package product;

import java.util.Comparator;

//По названиям. Если названия одинаковые, то по увеличению цены
public class ProductIncreasingTitleIncreasingPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {

        if (!o1.getTitle().equals(o2.getTitle())) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
        return o1.getPrice() - o2.getPrice();
    }
}

// Вариант со сравнением чисел внутри строки

//    return o1.getPrice()- o2.getPrice();
//    String title1 = o1.getTitle();
//    String title2 = o2.getTitle();
//    String[] title1Parts = title1.split(" ");
//    String[] title2Parts = title2.split(" ");

//    int title1Number = Integer.parseInt(title1Parts[1]);
//    int title2Number = Integer.parseInt(title2Parts[1]);
//
//    if (!title1Parts[0].equals(title2Parts[0])){
//      return title1Parts[0].compareTo(title2Parts[0]);
//    }
//    if (title1Number !=title2Number){
//      return title1Number - title2Number;
//    }
//    return o1.getPrice()- o2.getPrice();

