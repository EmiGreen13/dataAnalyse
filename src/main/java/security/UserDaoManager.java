package security;

import entity.InternalError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import security.entity.Role;
import security.entity.User;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoManager implements UserDao {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getUserByUsername(String username, InternalError internalError){
        User user = null;
        Set<Role> roles = null;
        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spGetUserByUsername(?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            call.setObject(1, username, Types.VARCHAR);
            call.registerOutParameter(2, Types.INTEGER);
            call.registerOutParameter(3, Types.NVARCHAR);


            Boolean result = call.execute();

            if (result){
                //Prepare result set
                ResultSet resultSet = call.getResultSet();
                //row count
                resultSet.last();

                int count = resultSet.getRow();

                //set first value
                resultSet.first();
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getBoolean(5), resultSet.getBoolean(6), resultSet.getBoolean(7), resultSet.getBoolean(8));

                //set before first position
                resultSet.beforeFirst();

                roles = new HashSet<>(count);

                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getInt(1), resultSet.getString(9)));
                }

                user.setRoles(roles);
            }
            //Output parameters
            Integer error = call.getInt(2);
            String errorMessage = call.getString(3);

            //if error occurs
            if (error != 0) {
                user = null;
                internalError.setErrorNumber(error);
                internalError.setErrorMessage(errorMessage);
            }
        }
        catch (Exception exception){
            user = null;
            internalError.setErrorMessage(exception.getLocalizedMessage());
            internalError.setErrorNumber(exception.hashCode());
        }

        return user;
    }

    @Override
    public void addNewUser(User user, List<Role> roles, InternalError internalError) {

        try{
            CallableStatement call = dataSource.getConnection().prepareCall("{ call dataanalyse.spAddNewUser(?,?,?,?,?,?,?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            call.registerOutParameter(1, Types.INTEGER);
            call.setObject(2, user.getLogin(), Types.VARCHAR);
            call.setObject(3, user.getPassword(), Types.VARCHAR);
            call.setObject(4, user.getEmail(), Types.VARCHAR);
            call.setObject(5, user.getActive(), Types.BIT);
            call.setObject(6, user.getAccountNonExpired(), Types.BIT);
            call.setObject(7, user.getCredentialsNonExpired(), Types.BIT);
            call.setObject(8, user.getAccountNonLocked(), Types.BIT);
            call.registerOutParameter(9, Types.INTEGER);
            call.registerOutParameter(10, Types.NVARCHAR);

            call.execute();

            Integer error = call.getInt(9);
            String errorMessage = call.getString(10);

            //if error occurs
            if (error != 0) {
                internalError.setErrorNumber(error);
                internalError.setErrorMessage(errorMessage);
            }
            else{
                Integer userId = call.getInt(1);

                CallableStatement callRole = dataSource.getConnection().prepareCall("{ call dataanalyse.spAddNewUserRole(?,?,?,?) }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                for(int index = 0; index < roles.size(); index++){
                    callRole.setObject(1, userId, Types.INTEGER);
                    callRole.setObject(2, roles.get(index).getRole(), Types.VARCHAR);
                    callRole.registerOutParameter(3, Types.INTEGER);
                    callRole.registerOutParameter(4, Types.NVARCHAR);

                    callRole.execute();

                    error = callRole.getInt(3);
                    errorMessage = callRole.getString(4);

                    if(error != 0){
                        break;
                    }
                }
            }

            if (error != 0) {
                internalError.setErrorNumber(error);
                internalError.setErrorMessage(errorMessage);
            }


        }
        catch (Exception exception){
            user = null;
            internalError.setErrorMessage(exception.getLocalizedMessage());
            internalError.setErrorNumber(exception.hashCode());
        }

    }
}
