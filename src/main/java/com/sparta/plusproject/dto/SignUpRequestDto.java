package com.sparta.plusproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {

    private String nickname;
    private String password;
    private String checkPassword;
}
