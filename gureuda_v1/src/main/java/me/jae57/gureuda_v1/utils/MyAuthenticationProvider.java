package me.jae57.gureuda_v1.utils;

import me.jae57.gureuda_v1.service.serviceImpl.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class MyAuthenticationProvider implements AuthenticationProvider {

    UserServiceImpl userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String)authentication.getPrincipal();
        String userPass = (String)authentication.getCredentials();
        UserDetails myUser = userService.loadUserByUsername(userName);

        if(!matchPassword(userPass,myUser.getPassword())){
            throw new BadCredentialsException(userName);                                                                                                                                CQSGz
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    public boolean matchPassword(String loginPass, String userPass){
        return loginPass.equals(userPass);
    }
}
