package com.example.demo.service.implement;


import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.SignIn.Request.SignInRequestDto;
import com.example.demo.dto.SignUp.Request.SignUpRequestDto;
import com.example.demo.dto.SignIn.Response.SignInResponseDto;
import com.example.demo.dto.SignUp.Response.SignUpResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.User;
import com.example.demo.provider.JwtProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto) {
        String userId = dto.getUserId(); // 아이디

        String password = dto.getPassword(); // 비밀번호
        String confirmPassword = dto.getConfirmPassword(); // 확인

        String name = dto.getName(); //이름

        String email = dto.getEmail(); //이메일

        String phone = dto.getPhone(); // 전화번호
        String gender = dto.getGender(); //성별

        String nickname = dto.getNickname(); // 닉네임

        Date birthDate= dto.getBirthDate();

        String profileImage= dto.getProfileImage();

        String role= dto.getRole();

        String licenseNumber= dto.getLicenseNumber();

        String specialization= dto.getSpecialization();

        String protectorId= dto.getProtectorId();

        SignUpResponseDto data = null;

        try {
            String encodedPassword = bCryptpasswordEncoder.encode(password);

            User user = User.builder()
                    .userId(userId)
                    .password(encodedPassword)
                    .name(name)
                    .phone(phone)
                    .email(email)
                    .nickname(nickname)
                    .birthDate(birthDate)
                    .gender(gender)
                    .profileImage(profileImage)
                    .role(role)
                    .licenseNumber(licenseNumber)
                    .specialization(specialization)
                    .protectorId(protectorId)
                    .build();

            userRepository.save(user);

            data = new SignUpResponseDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


    @Override
    public ResponseDto<SignInResponseDto> login(SignInRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();

        SignInResponseDto data = null;

//        // 1. 유효성 검사 //
//        if (userId == null || userId.isEmpty()) {
//            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
//        }
//
//        if (password == null || password.isEmpty()) {
//            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
//        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            if (!bCryptpasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            String token = jwtProvider.generateJwtToken(userId);
            int exprTime = jwtProvider.getExpiration();

            data = new SignInResponseDto(user, token, exprTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}