package security;

import entity.Basket;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginEventHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);

        HttpSession session = request.getSession(true);

        try {
            if (session.getAttribute("basket") == null){
                Basket basket = new Basket();
                session.setAttribute("basket", basket);
            }
        } catch (Exception e) {
            logger.error("Error in getting User()", e);
        }
    }
}
