package manager;


import entity.InternalError;
import entity.Product;
import entity.ProductToPrice;
import entity.statistics.StatisticsMonth;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StatisticsDaoManager implements StatisticsDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<StatisticsMonth> getYearReceiptsPerMonth(Locale locale, InternalError outputError) {

        List<StatisticsMonth> statisticsMonths = null;
        StatisticsMonth statisticsMonth = null;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spLoadYearReceiptsStatisticPerMonth(?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.registerOutParameter(1, Types.INTEGER);
            call.registerOutParameter(2, Types.NVARCHAR);

            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSet = call.getResultSet();
                //row count
                resultSet.last();
                int count = resultSet.getRow();
                statisticsMonths = new ArrayList<>(count);
                resultSet.beforeFirst();

                    while (resultSet.next()) {
                        statisticsMonth = new StatisticsMonth(resultSet.getInt(1), resultSet.getDouble(2));
                        statisticsMonths.add(statisticsMonth);
                    }
            }
            //Output parameters
            Integer error = call.getInt(1);
            String errorMessage = call.getString(2);

            //Set error variables
            outputError.setErrorNumber(error);
            outputError.setErrorMessage(errorMessage);

            //if error occurs
            if (error != 0){
                statisticsMonths = null;
            }

        }
        catch (Exception exception){
            statisticsMonths = null;
            outputError.setErrorMessage(exception.getLocalizedMessage());
            outputError.setErrorNumber(exception.hashCode());
        }
        return statisticsMonths;
    }
}
