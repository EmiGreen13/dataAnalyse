package manager;


import entity.InternalError;
import entity.Product;
import entity.ProductToPrice;
import entity.statistics.SquateTrand;
import entity.statistics.StatisticsMonth;
import entity.statistics.StatisticsMonthUser;

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

    @Override
    public List<StatisticsMonthUser> getYearReceiptsSelectedMonth(Integer month, Locale locale, InternalError outputError) {
        List<StatisticsMonthUser> statisticsMonthsUser = null;
        StatisticsMonthUser statisticsMonthUser = null;

        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spLoadYearReceiptsStatisticByProductGroupsInMonth(?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.setObject(1, month);
            call.setObject(2, locale.getLanguage());
            call.registerOutParameter(3, Types.INTEGER);
            call.registerOutParameter(4, Types.NVARCHAR);

            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSet = call.getResultSet();
                //row count
                resultSet.last();
                int count = resultSet.getRow();
                statisticsMonthsUser = new ArrayList<>(count);
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    statisticsMonthUser = new StatisticsMonthUser(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDate(6), resultSet.getString(7));
                    statisticsMonthsUser.add(statisticsMonthUser);
                }
            }
            //Output parameters
            Integer error = call.getInt(3);
            String errorMessage = call.getString(4);

            //Set error variables
            outputError.setErrorNumber(error);
            outputError.setErrorMessage(errorMessage);

            //if error occurs
            if (error != 0){
                statisticsMonthsUser = null;
            }

        }
        catch (Exception exception){
            statisticsMonthsUser = null;
            outputError.setErrorMessage(exception.getLocalizedMessage());
            outputError.setErrorNumber(exception.hashCode());
        }
        return statisticsMonthsUser;
    }

    @Override
    public List<SquateTrand> getSquareTrand(InternalError internalError) {

        List<SquateTrand> squateTrandList = null;
        SquateTrand squateTrand = null;

        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetSquareTrand(?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.registerOutParameter(1, Types.INTEGER);
            call.registerOutParameter(2, Types.NVARCHAR);

            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSet = call.getResultSet();
                //row count
                resultSet.last();
                int count = resultSet.getRow();
                resultSet.beforeFirst();

                squateTrandList = new ArrayList<>(count);

                while (resultSet.next()) {
                    squateTrand = new SquateTrand(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDouble(3));
                    squateTrandList.add(squateTrand);
                }
            }
            //Output parameters
            Integer error = call.getInt(1);
            String errorMessage = call.getString(2);

            //Set error variables
            internalError.setErrorNumber(error);
            internalError.setErrorMessage(errorMessage);

            //if error occurs
            if (error != 0){
                squateTrandList = null;
            }

        }
        catch (Exception exception){
            squateTrandList = null;
            internalError.setErrorMessage(exception.getLocalizedMessage());
            internalError.setErrorNumber(exception.hashCode());
        }
        return squateTrandList;
    }
}
