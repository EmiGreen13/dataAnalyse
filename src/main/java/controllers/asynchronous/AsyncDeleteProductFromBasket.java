package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Basket;
import entity.ProductToCount;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/delete_product_from_basket")
public class AsyncDeleteProductFromBasket extends ProcessErrorController {

    @RequestMapping
    public @ResponseBody
    Integer addProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        Integer exitCode = 0;
        Integer productToPriceId = null;

        try{

            try {
                productToPriceId = Integer.parseInt(request.getParameter("productToPriceId"));
            }
            catch (NumberFormatException exception){
                productToPriceId = null;
            }

                HttpSession session = request.getSession(true);
                Basket basket = (Basket)session.getAttribute("basket");

                if(basket != null){
                    Map<Integer, ProductToCount> productToCounts = basket.getProducts();
                    productToCounts.remove(productToPriceId);
                }
        }
        catch (Exception exception){
            exitCode = -1;
            processExceptions(response, exception);
        }

        return exitCode;
    }
}
