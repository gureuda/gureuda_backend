package me.jae57.gureuda_v1.service.serviceImpl;

import me.jae57.gureuda_v1.dao.AuthDao;
import me.jae57.gureuda_v1.dao.UserDao;
import me.jae57.gureuda_v1.dto.UserToken;
import me.jae57.gureuda_v1.model.MyUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl {

    private UserDao userDao;
    private AuthDao authDao;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserDao userDao,AuthDao authDao, AuthenticationManager authenticationManager){
        this.userDao = userDao;
        this.authDao = authDao;
        this.authenticationManager = authenticationManager;
    }

    public UserToken login(String userName, String userPass, HttpSession session){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, userPass);
        Authentication authentication = authenticationManager.authenticate(token);
        System.out.println("herehere3");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("herehere4");
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        MyUser user = userDao.getUser(userName);
        List<String> auths = authDao.getAuthsByUserName(userName);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String auth : auths){
            authorities.add(new SimpleGrantedAuthority(auth));
        }
        user.setAuthorities(authorities);
        return new UserToken(user.getUsername(), user.getAuthorities(), session.getId());
    }
}
