package me.jae57.gureuda_v1.service.serviceImpl;

import me.jae57.gureuda_v1.dao.AuthDao;
import me.jae57.gureuda_v1.dao.UserDao;
import me.jae57.gureuda_v1.dto.ReqUserDto;
import me.jae57.gureuda_v1.model.MyUser;
import me.jae57.gureuda_v1.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AuthDao authDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao,AuthDao autoDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.authDao = autoDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public int addUser(ReqUserDto reqUserDto) {
        userDao.addUser(MyUser.builder()
                .userName(reqUserDto.getUserName())
                .userPass(passwordEncoder.encode(reqUserDto.getUserPass()))
                .build()
        );
        return authDao.addAuth(reqUserDto.getUserName(), "USER");
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String userName) {
        List<String> stringAuthorities = authDao.getAuthsByUserName(userName);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String authority : stringAuthorities){
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser myUser = userDao.getUser(userName);
        myUser.setAuthorities(getAuthorities(userName));
        return myUser;
    }
}
