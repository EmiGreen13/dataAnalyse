package security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.entity.Role;
import entity.InternalError;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{

            InternalError internalError = new InternalError();

            security.entity.User username1 = getUserDao().getUserByUsername(username, internalError);

            User user = null;

            if (username1 != null){
                Set<GrantedAuthority> authorities = getAuthorities(username1.getRoles());

                user = new User(username1.getLogin(),
                        username1.getPassword(),
                        username1.getActive(),
                        username1.getAccountNonExpired(),
                        username1.getCredentialsNonExpired(),
                        username1.getAccountNonLocked(),
                        authorities
                );
            }

            return user;
        }
        catch (Exception exception){
            throw new UsernameNotFoundException(exception.getMessage());
        }
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles){
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
