package com.example.demo.controller;

import com.example.demo.common.constant.ApiMappingPattern;
import com.example.demo.dto.Matching.Request.MatchingRequestDto;
import com.example.demo.dto.Matching.Response.MatchingResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.MatchingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ApiMappingPattern.Matching)
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;

    // 모든 매칭 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<MatchingResponseDto>>> getAllMatchings(
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<MatchingResponseDto>> response = matchingService.getAllMatchings(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }


    // 특정 매칭 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<MatchingResponseDto>> getMatchingById(
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MatchingResponseDto> response = matchingService.getMatchingById(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 매칭 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteMatching(
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<Void> response = matchingService.deleteMatching(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 매칭 생성
    @PostMapping
    public ResponseEntity<ResponseDto<MatchingResponseDto>> createMatching(
            @Valid @RequestBody MatchingRequestDto matchingRequestDto,
            @AuthenticationPrincipal Long userId
    ) {
        matchingService.validateUserRole(userId, "DEPENDENT");
        ResponseDto<MatchingResponseDto> response = matchingService.createMatching(matchingRequestDto, userId);
        HttpStatus status = response.isResult() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}




