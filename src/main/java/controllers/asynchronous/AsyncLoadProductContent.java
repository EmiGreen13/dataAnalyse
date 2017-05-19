package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.InternalError;
import entity.Product;
import manager.ProductDao;
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
@RequestMapping(value = "/load_product_content")
public class AsyncLoadProductContent extends ProcessErrorController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping
    public @ResponseBody
    List<Product> getProduct(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        List<Product> products = null;
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

            products = getProduct(hierarchyId, first, last, locale, internalError);

            if(internalError.getErrorNumber() != 0){
                processInternalErrors(response, internalError);
                products = null;
            }

        }
        catch (Exception exception){
            processExceptions(response, exception);
        }

        return products;
    }

    private List<Product> getProduct(Integer id, Integer first, Integer last, Locale locale, InternalError internalError){
        return productDao.getProductByHierarchy(id, first, last, locale, internalError);
    }

}
