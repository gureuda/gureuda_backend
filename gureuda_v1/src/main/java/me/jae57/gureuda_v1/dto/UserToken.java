package me.jae57.gureuda_v1.dto;

import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
    String userName;
    Collection authorities;
    String token;
}
