package com.sparta.plusproject.controller;

import com.sparta.plusproject.dto.CommonResponseDto;
import com.sparta.plusproject.dto.SignUpRequestDto;
import com.sparta.plusproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto){
        try {
            userService.signup(requestDto);
            return ResponseEntity.ok().body(new CommonResponseDto("회원가입 완료", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

}
