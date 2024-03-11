package com.ey.dt.masterdata.payload.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponseDTO<T> extends ApiResponse<T> {
    LocalDateTime date;

    public ErrorResponseDTO(HttpStatus status, String message, LocalDateTime date, T data) {
        this.date = date;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
