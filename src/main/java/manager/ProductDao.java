package manager;


import entity.InternalError;
import entity.Product;

import java.util.List;
import java.util.Locale;

public interface ProductDao {
    List<Product> getProductByHierarchy(Integer id, Integer first, Integer last, Locale locale, InternalError outputError);
    Product getProductByProductId(Integer id, Locale locale, InternalError outputError);
}
