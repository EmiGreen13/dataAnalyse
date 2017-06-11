package manager;


import entity.Hierarchy;
import entity.HierarchyProduct;
import entity.InternalError;

import java.util.List;
import java.util.Locale;

public interface HierarchyDao {
    List<Hierarchy> getNextLevel(Integer id, Integer first, Integer last, Locale locale, InternalError outputError);
    List<HierarchyProduct> getHierarchyProducts(Integer id, Integer first, Integer last, Locale locale, InternalError internalError);
    List<HierarchyProduct> getRandomProducts(Integer first, Integer last, Locale locale, InternalError outputError);
}
