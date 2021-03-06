package controllers;

import entity.Basket;
import entity.Product;
import entity.ProductToPrice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageController {

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView loadAdminPage() {
        return new ModelAndView("WEB-INF/pages/admin");
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView loadIndexPage(HttpServletRequest request) {
        request.getSession().setAttribute("x", 58);

        return new ModelAndView("WEB-INF/pages/index");
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView loadCatalogPage() {
        return new ModelAndView("WEB-INF/pages/catalog");
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ModelAndView loadTypePage() {
        return new ModelAndView("WEB-INF/pages/type");
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView loadProductPage() { return new ModelAndView("WEB-INF/pages/product"); }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView loadBasketPage(HttpServletRequest request, HttpServletResponse response) {

        if (request.getRemoteUser() == null){
            return new ModelAndView("redirect:/index");
        }

        return new ModelAndView("WEB-INF/pages/basket");
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView loadOrderPage(HttpServletRequest request) {

        return new ModelAndView("WEB-INF/pages/order");
    }

    @RequestMapping(value = "/error")
    public ModelAndView loadErrorPage(HttpServletRequest request) {

        String errorMsg = "";
        int httpErrorCode = getErrorCode(request);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }

        request.setAttribute("error", errorMsg);
        return new ModelAndView("WEB-INF/pages/error");
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView loadTestPage() { return new ModelAndView("WEB-INF/pages/order"); }
}
