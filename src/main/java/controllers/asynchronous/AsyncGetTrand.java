package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.InternalError;
import entity.statistics.SquateTrand;
import entity.statistics.StatisticsMonth;
import manager.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/get_square_trand")
public class AsyncGetTrand extends ProcessErrorController {

    @Autowired
    StatisticsDao statisticsDao;

    @RequestMapping
    public @ResponseBody
    List<SquateTrand> getSquareTrand(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        List<SquateTrand> squateTrandList = null;
        SquateTrand squateTrand = null;

        try{

            InternalError internalError = new InternalError();

            squateTrandList = statisticsDao.getSquareTrand(internalError);


        }
        catch (Exception exception){
            squateTrandList = null;
            processExceptions(response, exception);
        }

        return squateTrandList;
    }
}
