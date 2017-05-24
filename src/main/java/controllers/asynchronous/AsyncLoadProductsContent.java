package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.HierarchyProduct;
import entity.InternalError;
import manager.HierarchyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/load_products")
public class AsyncLoadProductsContent extends ProcessErrorController {


    @Autowired
    private HierarchyDao hierarchyDao;

    @RequestMapping
    public @ResponseBody
    List<HierarchyProduct> getHierarchyProducts(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        List<HierarchyProduct> hierarchyProduct = null;
        Integer hierarchyId = null;
        Integer first = null;
        Integer last = null;

        try{

            try {
                hierarchyId = Integer.parseInt(request.getParameter("id"));
            }
            catch (NumberFormatException exception){
                hierarchyId = null;
            }

            try {
                first = Integer.parseInt(request.getParameter("first"));
            }
            catch (NumberFormatException exception){
                first = null;
            }

            try {
                last = Integer.parseInt(request.getParameter("last"));
            }
            catch (NumberFormatException exception){
                last = null;
            }

            InternalError internalError = new InternalError();

            hierarchyProduct = getHierarchyProducts(hierarchyId, first, last, locale, internalError);

            if(internalError.getErrorNumber() != 0){
                processInternalErrors(response, internalError);
                hierarchyProduct = null;
            }

        }
        catch (Exception exception){
            processExceptions(response, exception);
        }

        return hierarchyProduct;
    }


    private List<HierarchyProduct> getHierarchyProducts(Integer id, Integer first, Integer last, Locale locale, InternalError internalError){
        return hierarchyDao.getHierarchyProducts(id, first, last, locale, internalError);
    }

    @Autowired
    public void setHierarchyDao(HierarchyDao hierarchyDao) {
        this.hierarchyDao = hierarchyDao;
    }
}
