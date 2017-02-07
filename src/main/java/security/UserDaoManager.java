package security;

import error.Errors;
import org.springframework.stereotype.Repository;
import security.entity.User;
import javax.persistence.*;
import java.sql.SQLException;

@Repository
public class UserDaoManager implements UserDao {

    protected EntityManagerFactory entityManagerFactory;

    public User getUserByUsername(String username){
        User user = null;
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("security.getUserByUsername", User.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            query.setParameter(1, username);

            query.execute();


            user = (User)query.getResultList().get(0);

            entityManager.close();
        }
        catch (Exception exception){
            user = null;
        }

        return user;
    }

    @Override
    public void setNewUser(User user) throws SQLException{
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("security.setUser", User.class);

            String errorMessage = "";

            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(4, String.class, ParameterMode.INOUT);

            query.setParameter(1, user.getLogin());
            query.setParameter(2, user.getPassword());
            query.setParameter(3, user.getEmail());


            query.execute();

            errorMessage = (String) query.getOutputParameterValue(4);

            if(errorMessage == null || errorMessage.length() > 0){
                throw new Exception(errorMessage);
            }

            entityManager.close();
        }
        catch (Exception exception){
            throw new SQLException(exception.getMessage());
        }
    }

    @PersistenceUnit(unitName = "myEmfSecurity")
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
