package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Basket;
import entity.InternalError;
import entity.ProductToPrice;
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
@RequestMapping(value = "/delete_product_from_basket")
public class AsyncDeleteProductFromBasket extends ProcessErrorController {

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
        Integer hierarchyId = null;
        Integer count = 1;

        try{

            try {
                hierarchyId = Integer.parseInt(request.getParameter("hierarchyId"));
            }
            catch (NumberFormatException exception){
                hierarchyId = null;
            }

            try {
                count = Integer.parseInt(request.getParameter("count"));
            }
            catch (NumberFormatException exception){
                count = null;
            }
//
//            InternalError internalError = new InternalError();
//
//            ProductToPrice productToPrice = productDao.getProductByProductToPriceId(hierarchyId, locale, internalError);
//
//            if(productToPrice == null || internalError.getErrorNumber() != 0){
//                processInternalErrors(response, internalError);
//            }
//            else{
//                HttpSession session = request.getSession(true);
//                Basket basket = (Basket)session.getAttribute("basket");
//
//                if(basket != null){
//                    if(count != null){
//                        basket.add(productToPrice, count);
//                    }
//                    else{
//                        basket.add(productToPrice);
//                    }
//                    exitCode = basket.getProducts().size();
//                }
//                else{
//                    Basket basket1 = new Basket();
//                    if(count != null){
//                        basket1.add(productToPrice, count);
//                    }
//                    else{
//                        basket1.add(productToPrice);
//                    }
//                    session.setAttribute("basket", basket1);
//
//                    exitCode = basket1.getProducts().size();
//                }
//            }
        }
        catch (Exception exception){
            exitCode = -1;
            processExceptions(response, exception);
        }

        return exitCode;
    }
}
