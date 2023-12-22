package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.SignUpRequestDto;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    public void signup(SignUpRequestDto requestDto) {
        String nickname=requestDto.getNickname();
        String password= requestDto.getPassword();
        /* passwordEncoder.encode(requestDto.getPassword());*/
        if(userRepository.findByNickname(requestDto.getNickname()).isPresent()){
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        User user =new User(nickname,password);
        userRepository.save(user);
    }
}
