package manager;


import entity.InternalError;
import entity.Product;
import entity.ProductToPrice;

import java.util.List;
import java.util.Locale;

public interface ProductDao {
    List<Product> getProductByHierarchy(Integer id, Integer first, Integer last, Locale locale, InternalError outputError);
    ProductToPrice getProductByProductToPriceId(Integer id, Locale locale, InternalError outputError);
}
