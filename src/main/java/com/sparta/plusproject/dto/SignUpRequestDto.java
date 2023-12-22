package com.sparta.plusproject.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$")
    private String nickname;

    @Size(min = 4)
    private String password;
    @Size(min = 4)
    private String checkPassword;
}
