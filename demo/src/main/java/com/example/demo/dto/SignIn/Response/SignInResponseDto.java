package com.example.demo.dto.SignIn.Response;

import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDto {
    private User user;
    private String token;
    private int exprTime;
}