package manager;


import entity.InternalError;
import entity.Product;
import entity.ProductToPrice;
import entity.statistics.StatisticsMonth;

import java.util.List;
import java.util.Locale;

public interface StatisticsDao {
    List<StatisticsMonth> getYearReceiptsPerMonth(Locale locale, InternalError outputError);
}
