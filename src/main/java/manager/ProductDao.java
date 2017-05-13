package manager;


import entity.Hierarchy;
import entity.InternalError;
import entity.Product;

import java.util.List;
import java.util.Locale;

public interface HierarchyDao {
    List<Hierarchy> getNextLevel(Integer id, Integer first, Integer last, Locale locale, InternalError outputError);
    List<Product> getProductByHierarchy(Integer id, Integer first, Integer last, Locale locale, InternalError outputError);
}
