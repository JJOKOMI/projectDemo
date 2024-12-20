package com.example.demo.service;

import com.example.demo.dto.Matching.Request.MatchingRequestDto;
import com.example.demo.dto.Matching.Response.MatchingResponseDto;
import com.example.demo.dto.ResponseDto;
import jakarta.persistence.Id;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchingService {
    ResponseDto<List<MatchingResponseDto>> getAllMatchings(Long id);

    ResponseDto<MatchingResponseDto> getMatchingById(Long id);

    ResponseDto<Void> deleteMatching(Long id);

    ResponseDto<MatchingResponseDto> createMatching(MatchingRequestDto dto, Long id);


    ResponseDto<String> getUserRole(String userId);

    void validateUserRole(Long userId, String requiredRole);


}
