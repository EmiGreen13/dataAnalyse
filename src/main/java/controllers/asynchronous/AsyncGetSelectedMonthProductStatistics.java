package controllers.asynchronous;

import controllers.ProcessErrorController;
import entity.InternalError;
import entity.statistics.StatisticsMonth;
import entity.statistics.StatisticsMonthUser;
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
@RequestMapping(value = "/get_year_selected_month_statistics_user")
public class AsyncGetSelectedMonthProductStatistics extends ProcessErrorController {

    @Autowired
    StatisticsDao statisticsDao;

    @RequestMapping
    public @ResponseBody
    List<StatisticsMonthUser> getMonthStatistics(
            HttpServletRequest request,
            HttpServletResponse response,
            Locale locale
    ) throws IOException {

        List<StatisticsMonthUser> statisticsMonthsUser = null;

        try{

            Integer month = new Integer(request.getParameter("month"));

            InternalError internalError = new InternalError();

            statisticsMonthsUser = statisticsDao.getYearReceiptsSelectedMonth(month, locale, internalError);

        }
        catch (Exception exception){
            statisticsMonthsUser = null;
            processExceptions(response, exception);
        }

        return statisticsMonthsUser;
    }
}
