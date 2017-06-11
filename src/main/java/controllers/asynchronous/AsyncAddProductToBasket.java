package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Basket;
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

    @RequestMapping
    public @ResponseBody
    Integer addProduct(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        Integer exitCode = 0;
        Integer productToPriceId = null;
        Integer count = 1;

        try{

            try {
                productToPriceId = Integer.parseInt(request.getParameter("productToPriceId"));
            }
            catch (NumberFormatException exception){
                productToPriceId = null;
            }

            try {
                count = Integer.parseInt(request.getParameter("count"));
            }
            catch (NumberFormatException exception){
                count = null;
            }

                HttpSession session = request.getSession(true);
                Basket basket = (Basket)session.getAttribute("basket");

                if(basket != null){
                    if(count != null){
                        basket.add(productToPriceId, count);
                    }
                    else{
                        basket.add(productToPriceId);
                    }
                    exitCode = basket.getProducts().size();
                }
                else{
                    Basket basket1 = new Basket();
                    if(count != null){
                        basket1.add(productToPriceId, count);
                    }
                    else{
                        basket1.add(productToPriceId);
                    }
                    session.setAttribute("basket", basket1);

                    exitCode = basket1.getProducts().size();
                }

        }
        catch (Exception exception){
            exitCode = -1;
            processExceptions(response, exception);
        }

        return exitCode;
    }
}
