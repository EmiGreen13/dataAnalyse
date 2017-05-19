package controllers.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Order {

    @RequestMapping(value = {"/booking/order"}, method = RequestMethod.GET)
    public ModelAndView loadOrderPage() {

        return new ModelAndView("booking/WEB-INF/pages/order");
    }

}
