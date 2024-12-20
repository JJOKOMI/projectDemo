package com.example.demo.service;

import com.example.demo.dto.SignIn.Request.SignInRequestDto;
import com.example.demo.dto.SignUp.Request.SignUpRequestDto;
import com.example.demo.dto.SignIn.Response.SignInResponseDto;
import com.example.demo.dto.SignUp.Response.SignUpResponseDto;
import com.example.demo.dto.ResponseDto;


public interface AuthService {

    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseDto<SignInResponseDto> login(SignInRequestDto dto);
}