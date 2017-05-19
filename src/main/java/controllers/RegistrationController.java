package controllers;

import entity.InternalError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import security.UserDao;
import security.entity.Role;
import security.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView loadRegistrationPage() { return new ModelAndView("WEB-INF/pages/registration"); }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView loadAfterSuccessRegistration(HttpServletRequest request, HttpServletResponse response){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WEB-INF/pages/registration");

        try{

            String username = request.getParameter("username");
            if(!User.isValidLogin(username)){
                modelAndView.addObject("error", "Incorrect login");
            }

            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

            if(!User.isValidPassword(password1, password2)){
                modelAndView.addObject("error", "Incorrect password");
            }

            String email = request.getParameter("email");

            if(User.isValidEmail(email)){
                modelAndView.addObject("error", "Incorrect email");
            }

            User user = User.validUser(username, password1, password2, email);
            List<Role> roles = new ArrayList<Role>(10);
            roles.add(new Role("ROLE_USER"));

            String role = request.getParameter("role");
            if(role != null) {
                roles.add(new Role(role));
            }

            InternalError internalError = new InternalError();

            userDao.addNewUser(user, roles, internalError);

            if(internalError.getErrorNumber() == 0){
                modelAndView.addObject("error", "Success");
                modelAndView.setViewName("WEB-INF/pages/login");
            }
            else{
                modelAndView.addObject("error", internalError.getErrorMessage());
                modelAndView.setViewName("WEB-INF/pages/registration");
            }

        }catch (Exception exception){
            modelAndView.setViewName("WEB-INF/pages/registration");
            modelAndView.addObject("error", exception.getMessage());
        }


        return modelAndView;
    }

}
