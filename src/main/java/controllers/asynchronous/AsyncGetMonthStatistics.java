package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.Basket;
import entity.InternalError;
import entity.statistics.StatisticsMonth;
import manager.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/get_year_month_statistics")
public class AsyncGetMonthStatistics extends ProcessErrorController {

    @Autowired
    StatisticsDao statisticsDao;

    @RequestMapping
    public @ResponseBody
    List<StatisticsMonth> getMonthStatistics(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        List<StatisticsMonth> statisticsMonths = null;

        try{

            InternalError internalError = new InternalError();

            statisticsMonths = statisticsDao.getYearReceiptsPerMonth(locale, internalError);

        }
        catch (Exception exception){
            statisticsMonths = null;
            processExceptions(response, exception);
        }

        return statisticsMonths;
    }
}
