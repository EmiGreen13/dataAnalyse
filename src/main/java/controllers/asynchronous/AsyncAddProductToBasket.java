package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Basket;
import entity.InternalError;
import entity.Product;
import manager.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping(value = "/add_product_to_basket")
public class AsyncAddProductToBasket extends ProcessErrorController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping
    public @ResponseBody
    Integer addProduct(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        Integer exitCode = 0;
        Integer hirarchyId = null;
        Integer count = 0;

        try{

            try {
                hirarchyId = Integer.parseInt(request.getParameter("productId"));
            }
            catch (NumberFormatException exception){
                hirarchyId = null;
            }

            try {
                count = Integer.parseInt(request.getParameter("count"));
            }
            catch (NumberFormatException exception){
                count = null;
            }

            InternalError internalError = new InternalError();

            Product product = productDao.getProductByProductId(hirarchyId, locale, internalError);

            if(product == null || internalError.getErrorNumber() != 0){
                processInternalErrors(response, internalError);
            }
            else{
                HttpSession session = request.getSession();
                Basket basket = (Basket)request.getSession().getAttribute("basket");

                if(basket != null){
                    if(count != null){
                        basket.add(product, count);
                    }
                    else{
                        basket.add(product);
                    }
                }
            }
        }
        catch (Exception exception){
            exitCode = -1;
            processExceptions(response, exception);
        }

        return exitCode;
    }
}
