package com.sparta.plusproject.security;

import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserRepository userRepository;
    public UserDetails getUserDetails(String nickname){
        User user=userRepository.findByNickname(nickname).orElseThrow(()->new UsernameNotFoundException("Not  Found"+nickname));
        return new UserDetailsImpl(user);
    }
}
