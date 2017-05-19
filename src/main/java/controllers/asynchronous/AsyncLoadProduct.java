package controllers.asynchronous;

import entity.Hierarchy;
import entity.InternalError;
import manager.HierarchyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/load_product")
public class AsyncLoadProduct extends AsyncBaseHierarchyController {


    protected HierarchyDao hierarchyDao;

    @Override
    protected List<Hierarchy> getChildHierarchies(Integer id, Integer first, Integer last, Locale locale, InternalError internalError){
        return hierarchyDao.getNextLevel(id, first, last, locale, internalError);
    }

    @Autowired
    public void setHierarchyDao(HierarchyDao hierarchyDao) {
        this.hierarchyDao = hierarchyDao;
    }
}

