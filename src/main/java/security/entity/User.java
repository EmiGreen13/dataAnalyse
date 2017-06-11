package security.entity;

import security.HashPassword;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private Boolean active;

    private static final Boolean credentialsIsNonExpired = true;
    private static final Boolean accountIsNonLocked = true;
    private static final Boolean isActive = true;
    private static final Boolean accountIsNonExpired = true;

    private Set<Role> roles;

    public User(){}

    public User
            (
                String login,
                String password,
                String email,
                Boolean active,
                Boolean accountNonExpired,
                Boolean credentialsNonExpired,
                Boolean accountNonLocked
            ){
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setActive(active); //активый/неактивный
        this.setAccountNonExpired(accountNonExpired);//аккаунт не просрочен
        this.setCredentialsNonExpired(credentialsNonExpired);// пароль не просрочен
        this.setAccountNonLocked(accountNonLocked);//аккаунт не заблокирован
    }

    public User
    (
        String login,
        String password,
        String email
    ){
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setActive(true);
        this.setAccountNonExpired(true);
        this.setCredentialsNonExpired(true);
        this.setAccountNonLocked(true);
    }

    public User
            (
                    Integer id,
                    String login,
                    String password,
                    String email,
                    Boolean active,
                    Boolean accountNonExpired,
                    Boolean credentialsNonExpired,
                    Boolean accountNonLocked
            ){
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setActive(active);
        this.setAccountNonExpired(accountNonExpired);
        this.setCredentialsNonExpired(credentialsNonExpired);
        this.setAccountNonLocked(accountNonLocked);
    }



    public static User validUser(String login, String password1, String password2, String email)throws Exception{

        User user = null;
        if(isValidLogin(login) && isValidPassword(password1, password2) && isValidEmail(email)){
            user = new User(
                    login,
                    HashPassword.getHashPassword(password1),
                    email,
                    isActive,
                    accountIsNonExpired,
                    credentialsIsNonExpired,
                    accountIsNonLocked
                    );
        }

        return user;
    }

    public static Boolean isValidLogin(String login){
        Boolean result = false;
        if (login != null || (login.length() > 0 && login.length() < 50) ){
            Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]{4,50}$");
            Matcher matcher = pattern.matcher(login);
            result = matcher.matches();
        }
        return result;
    }

    public static Boolean isValidPassword(String password1, String password2){

        Boolean result = false;

        if (password1.equals(password2)){

            if (password1.length() < 5 || password1.length() > 50){
                result = false;
            }
            else{
                result = true;
            }
        }

        return result;
    }

    public static Boolean isValidEmail(String email){
        Boolean result = true;
        if (email != null || (email.length() > 0 && email.length() < 50) ){
//            Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,6}$");
//            Matcher matcher = pattern.matcher(email);
//            result = matcher.matches();
        }
        return result;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
