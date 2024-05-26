package org.ahmed.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String errorMessage;
}

