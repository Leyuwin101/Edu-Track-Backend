package com.example.edutrackbackend.user.dto;

import com.example.edutrackbackend.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    private String username;

    private Role role;
}
