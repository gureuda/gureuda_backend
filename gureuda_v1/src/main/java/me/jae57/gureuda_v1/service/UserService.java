package me.jae57.gureuda_v1.service;

import me.jae57.gureuda_v1.dto.ReqUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String userName);
    UserDetails loadUserByUsername(String userName);
    int addUser(ReqUserDto reqUserDto);
}
