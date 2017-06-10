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

@RestController
@RequestMapping(value = "/get_count_products_in_basket")
public class AsyncCountOfProductsInBasket extends ProcessErrorController {

    @RequestMapping
    public @ResponseBody
    Integer addProduct(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        Integer count = 0;

        try{

                HttpSession session = request.getSession();
                Basket basket = (Basket)session.getAttribute("basket");

                if(basket != null){
                    count = basket.getProducts().size();
                }

        }
        catch (Exception exception){
            count = 0;
            processExceptions(response, exception);
        }

        return count;
    }
}
