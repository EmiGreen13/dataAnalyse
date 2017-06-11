package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Basket;
import entity.InternalError;
import entity.ProductToCount;
import manager.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/show_basket")
public class AsyncShowBasket extends ProcessErrorController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping
    public @ResponseBody
    Set<ProductToCount> getProductToPrices(HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {

        Set<ProductToCount> productToCounts = null;
        try{
                HttpSession session = request.getSession(true);
                Basket basket = (Basket)session.getAttribute("basket");

                if(basket != null){
                    productToCounts =  new HashSet<>(basket.getProducts().values());
                    InternalError internalError = new InternalError();

                    for(ProductToCount productToCount: productToCounts){
                        productToCount.setProductToPrice(productDao.getProductByProductToPriceId(productToCount.getProductToPriceId(), locale, internalError));
                    }

                    if(internalError.getErrorNumber() != 0){
                        processInternalErrors(response, internalError);
                    }
                }
        }
        catch (Exception exception){
            processExceptions(response, exception);
        }
        return productToCounts;
    }
}
