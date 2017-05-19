package manager;


import entity.InternalError;
import entity.Product;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDaoManager implements ProductDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getProductByHierarchy(Integer id, Integer first, Integer last, Locale locale, InternalError outputError) {
        List<Product> products = null;
        Product product;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetProductByHierarchy(?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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

                products = new ArrayList<>(count);
                while (resultSet.next()) {
                    product = new Product(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                    products.add(product);
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
                products = null;
            }

        }
        catch (Exception exception){
            products = null;
            outputError.setErrorMessage(exception.getLocalizedMessage());
            outputError.setErrorNumber(exception.hashCode());
        }
        return products;
    }

    @Override
    public Product getProductByProductId(Integer id, Locale locale, InternalError outputError) {

        Product product = null;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetProductByProductId(?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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

                if(count == 1){
                    while (resultSet.next()) {
                        product = new Product(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
                    }
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
                product = null;
            }

        }
        catch (Exception exception){
            product = null;
            outputError.setErrorMessage(exception.getLocalizedMessage());
            outputError.setErrorNumber(exception.hashCode());
        }
        return product;
    }

}
