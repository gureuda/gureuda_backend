package me.jae57.gureuda_v1.service;

import me.jae57.gureuda_v1.dto.ReqUserDto;
import me.jae57.gureuda_v1.model.MyUser;

public interface AuthService{
    MyUser getUser(String userName);
    int addUser(ReqUserDto reqUserDto);
}
