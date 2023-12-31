package com.sparta.plusproject.controller;

import com.sparta.plusproject.dto.CommonResponseDto;
import com.sparta.plusproject.dto.LoginRequestDto;
import com.sparta.plusproject.dto.SignUpRequestDto;
import com.sparta.plusproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<CommonResponseDto> signUp( @RequestBody @Valid SignUpRequestDto requestDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new CommonResponseDto("양식에 맞게 작성해주세요",HttpStatus.BAD_REQUEST.value()));
        }
        if (requestDto.getPassword().contains(String.valueOf(requestDto.getNickname()))) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("비밀번호안에 닉네임이 있으면 안됩니다.",HttpStatus.BAD_REQUEST.value()));
        }

        try {
            userService.signup(requestDto);
            return ResponseEntity.ok().body(new CommonResponseDto("회원가입 완료", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        try {
            userService.login(loginRequestDto,response);
            return ResponseEntity.ok().body(new CommonResponseDto("로그인 완료",HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

}
