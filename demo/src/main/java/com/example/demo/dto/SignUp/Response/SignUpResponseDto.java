package com.example.demo.dto.SignUp.Response;

import com.example.demo.entity.User;
import lombok.Data;

@Data
public class SignUpResponseDto {
    User user;
    public SignUpResponseDto(User user) {
        this.user= user;
    }
}