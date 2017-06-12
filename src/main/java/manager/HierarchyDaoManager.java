package manager;

import entity.Hierarchy;
import entity.HierarchyProduct;
import entity.InternalError;
import entity.Product;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HierarchyDaoManager implements HierarchyDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Hierarchy> getNextLevel(Integer id, Integer first, Integer last, Locale locale, InternalError outputError){
        List<Hierarchy> hierarchies = null;
        Hierarchy hierarchy;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetHierarchy(?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.setObject(1, id, Types.INTEGER);
            call.setString(2, locale.getLanguage());

            call.registerOutParameter(3, Types.INTEGER);
            call.registerOutParameter(4, Types.NVARCHAR);

            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSet = call.getResultSet();
                //row count
                resultSet.last();
                int count = resultSet.getRow();
                resultSet.beforeFirst();

                hierarchies = new ArrayList<>(count);
                while (resultSet.next()) {
                    hierarchy = new Hierarchy(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6));
                    hierarchies.add(hierarchy);
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
                hierarchies = null;
            }

        }
        catch (Exception exception){
            hierarchies = null;
            outputError.setErrorMessage(exception.getLocalizedMessage());
            outputError.setErrorNumber(exception.hashCode());
        }
        return hierarchies;
    }

    @Override
    public List<HierarchyProduct> getHierarchyProducts(Integer id, Integer first, Integer last, Locale locale, InternalError internalError) {
        List<HierarchyProduct> hierarchyProducts = null;
        HierarchyProduct hierarchyProduct;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetHierarchyProduct(?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.setObject(1, id, Types.INTEGER);
            call.setString(2, locale.getLanguage());

            call.registerOutParameter(3, Types.INTEGER);
            call.registerOutParameter(4, Types.NVARCHAR);

            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSet = call.getResultSet();
                //row count
                resultSet.last();
                int count = resultSet.getRow();
                resultSet.beforeFirst();

                hierarchyProducts = new ArrayList<>(count);
                while (resultSet.next()) {
                    hierarchyProduct = new HierarchyProduct(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getInt(9),
                            resultSet.getDouble(10)
                    );
                    hierarchyProducts.add(hierarchyProduct);
                }
            }
            //Output parameters
            Integer error = call.getInt(3);
            String errorMessage = call.getString(4);

            //Set error variables
            internalError.setErrorNumber(error);
            internalError.setErrorMessage(errorMessage);

            //if error occurs
            if (error != 0){
                hierarchyProducts = null;
            }

        }
        catch (Exception exception){
            hierarchyProducts = null;
            internalError.setErrorMessage(exception.getLocalizedMessage());
            internalError.setErrorNumber(exception.hashCode());
        }
        return hierarchyProducts;
    }

    @Override
    public List<HierarchyProduct> getRandomProducts(Integer first, Integer last, Locale locale, InternalError outputError) {
        List<HierarchyProduct> hierarchyProducts = null;
        HierarchyProduct hierarchyProduct;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetRandomProduct(?,?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.setString(1, locale.getLanguage());
            call.setObject(2, first);
            call.setObject(3, last);
            call.registerOutParameter(4, Types.INTEGER);
            call.registerOutParameter(5, Types.NVARCHAR);

            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSetRandom = call.getResultSet();
                //row count
                resultSetRandom.last();
                int count = resultSetRandom.getRow();
                resultSetRandom.beforeFirst();

                hierarchyProducts = new ArrayList<>(count);
                while (resultSetRandom.next()) {
                    hierarchyProduct = new HierarchyProduct(
                            resultSetRandom.getInt(1),
                            resultSetRandom.getString(2),
                            resultSetRandom.getInt(3),
                            resultSetRandom.getInt(4),
                            resultSetRandom.getInt(5),
                            resultSetRandom.getInt(6),
                            resultSetRandom.getString(7),
                            resultSetRandom.getString(8),
                            resultSetRandom.getInt(9),
                            resultSetRandom.getDouble(10)
                    );
                    hierarchyProducts.add(hierarchyProduct);
                }
            }
            //Output parameters
            Integer error = call.getInt(4);
            String errorMessage = call.getString(5);

            //Set error variables
            outputError.setErrorNumber(error);
            outputError.setErrorMessage(errorMessage);

            //if error occurs
            if (error != 0){
                hierarchyProducts = null;
            }

        }
        catch (Exception exception){
            hierarchyProducts = null;
            outputError.setErrorMessage(exception.getLocalizedMessage());
            outputError.setErrorNumber(exception.hashCode());
        }
        return hierarchyProducts;
    }

}
