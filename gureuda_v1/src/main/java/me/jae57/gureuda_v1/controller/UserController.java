package me.jae57.gureuda_v1.controller;

import me.jae57.gureuda_v1.dto.ReqUserDto;
import me.jae57.gureuda_v1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> addUser(@RequestBody ReqUserDto reqUserDto) {
        userService.addUser(reqUserDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
