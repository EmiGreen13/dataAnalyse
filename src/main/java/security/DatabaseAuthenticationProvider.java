package security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseAuthenticationProvider implements
        AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        try{

        String password = authentication.getCredentials().toString();
        String userName = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        if (userDetails == null) {

            throw new BadCredentialsException(userName);
        } else if (!userDetails.isEnabled()) {

            throw new DisabledException(userName);
        }
        else {

            if (password.equals(userDetails.getPassword())) {
                //token - системный объект для доступа(код)
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                return token;
            } else {
                throw new BadCredentialsException(userName);
            }
        }
        }
        catch (Exception exception){
            throw new UnsupportedOperationException(exception.getMessage());
        }
    }











    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
