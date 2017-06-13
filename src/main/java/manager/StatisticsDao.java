package manager;


import entity.InternalError;
import entity.Product;
import entity.ProductToPrice;
import entity.statistics.SquateTrand;
import entity.statistics.StatisticsMonth;
import entity.statistics.StatisticsMonthUser;

import java.util.List;
import java.util.Locale;

public interface StatisticsDao {
    List<StatisticsMonth> getYearReceiptsPerMonth(Locale locale, InternalError outputError);
    List<StatisticsMonthUser> getYearReceiptsSelectedMonth(Integer month, Locale locale, InternalError outputError);
    List<SquateTrand> getSquareTrand(InternalError internalError);
}
