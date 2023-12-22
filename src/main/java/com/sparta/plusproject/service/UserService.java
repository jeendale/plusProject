package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.LoginRequestDto;
import com.sparta.plusproject.dto.SignUpRequestDto;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.jwt.JwtUtil;
import com.sparta.plusproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final JwtUtil jwtUtil;
    public void signup(SignUpRequestDto requestDto) {
        String nickname=requestDto.getNickname();
        String password=passwordEncoder.encode(requestDto.getPassword());
        if(!passwordEncoder.matches(requestDto.getCheckPassword(), password)){
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }
        if(userRepository.findByNickname(requestDto.getNickname()).isPresent()){
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        User user =new User(nickname,password);
        userRepository.save(user);
    }

    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String nickname =loginRequestDto.getNickname();
        String password = loginRequestDto.getPassword();
        //db에 이름 여부 파악
        User user = userRepository.findByNickname(nickname).orElseThrow(()->new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요"));
        //비밀번호 일치
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("뭐야");
            throw new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요");
        }

        //user.getRole() 사용위해 JWT생성 및 쿠키에 저장
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getNickname()));
    }
}
