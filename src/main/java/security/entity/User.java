package security.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "User")
@Table(name = "tblUser")
public class User {
    @Id
    @Column(name = "UserId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Login", length = 50, nullable = false, unique = true)
    private String login;
    @Column(name = "Password", length = 100, nullable = false)
    private String password;
    @Column(name = "Email", length = 50, nullable = false)
    private String email;
    @Column(name = "accountNonExpired", columnDefinition="bool default '1'", nullable = false)
    private Boolean accountNonExpired;
    @Column(name = "credentialsNonExpired", columnDefinition="bool default '1'", nullable = false)
    private Boolean credentialsNonExpired;
    @Column(name = "accountNonLocked", columnDefinition="bool default '0'", nullable = false)
    private Boolean accountNonLocked;
    @Column(name = "Active", columnDefinition="bool default '1'", nullable = false)
    private Boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Role> roles;

    private static final String loginPattern = "";


    public static Boolean isValidParameters(String username, String password1, String password2, String email){
        Boolean result = true;

        try{

        }
        catch (Exception exception){
            result = false;
        }
        return result;
    }


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

    public static Boolean isValidLogin(String login){
        Boolean result = false;

        if (login != null || (login.length() > 0 && login.length() < 50) ){
            Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]{5,50}$");
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
