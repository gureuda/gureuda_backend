package me.jae57.gureuda_v1.controller;

import me.jae57.gureuda_v1.dto.ReqUserDto;
import me.jae57.gureuda_v1.dto.UserToken;
import me.jae57.gureuda_v1.model.MyUser;
import me.jae57.gureuda_v1.service.UserService;
import me.jae57.gureuda_v1.service.serviceImpl.AuthServiceImpl;
import me.jae57.gureuda_v1.service.serviceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserServiceImpl userService;

    public AuthController(AuthServiceImpl authService,
                          AuthenticationManager authenticationManager,
                          UserServiceImpl userService) {
        this.authService = authService;
        this.userService = userService;
    }

//
//    @PostMapping("/login")
//    public ResponseEntity<UserDetails> addUser(@RequestBody ReqUserDto reqUserDto) {
//        UserDetails userDetails = authService.loadUserByUsername(reqUserDto.getUserName());
//        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
//    }

    @PostMapping("/login")
    public UserToken login(@RequestBody ReqUserDto reqUserDto,
                           HttpSession session) {
        return authService.login(reqUserDto.getUserName(), reqUserDto.getUserPass(), session);
    }
}
