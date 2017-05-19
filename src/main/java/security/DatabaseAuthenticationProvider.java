package security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseAuthenticationProvider implements
        AuthenticationProvider {
    //private static final Logger logger = LoggerFactory
    //.getLogger(DatabaseAuthenticationProvider.class);

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        try{



            //logger.info("authenticate started.");

            String password = authentication.getCredentials().toString();
            String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());

            String userName = authentication.getName();
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (userDetails == null) {
                //logger.error("User not found. UserName=" + userName);
                throw new BadCredentialsException(userName);
            } else if (!userDetails.isEnabled()) {
                //logger.error("Not activated.");

                throw new DisabledException(userName);

            }
            else {

                String crypted = HashPassword.getHashPassword(password);
                if (crypted == null){
                    throw new UnsupportedEncodingException("Can not encode SHA-256");
                }

                if (crypted.equals(userDetails.getPassword())) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    //logger.info("authenticate finished.");
                    return token;
                } else {
                    //logger.error("Password does not match. UserName=" + userName);
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

