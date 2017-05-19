package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Hierarchy;
import entity.InternalError;
import manager.HierarchyDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public abstract class AsyncBaseHierarchyController extends ProcessErrorController{

    @RequestMapping
    public @ResponseBody List<Hierarchy> getHierarchy(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException{

        List<Hierarchy> hierarchies = null;
        Integer hierarchyId = null;
        Integer first = null;
        Integer last = null;

        try{

            try {
                hierarchyId = Integer.parseInt(request.getParameter("id"));
            }
            catch (NumberFormatException exception){
                hierarchyId = null;
            }

            try {
                first = Integer.parseInt(request.getParameter("first"));
            }
            catch (NumberFormatException exception){
                first = null;
            }

            try {
                last = Integer.parseInt(request.getParameter("last"));
            }
            catch (NumberFormatException exception){
                last = null;
            }

            InternalError internalError = new InternalError();

            hierarchies = getChildHierarchies(hierarchyId, first, last, locale, internalError);

            if(internalError.getErrorNumber() != 0){
                processInternalErrors(response, internalError);
                hierarchies = null;
            }

        }
        catch (Exception exception){
            processExceptions(response, exception);
        }

        return hierarchies;
    }

    protected abstract List<Hierarchy> getChildHierarchies(Integer id, Integer first, Integer last, Locale locale, InternalError internalError);

}
