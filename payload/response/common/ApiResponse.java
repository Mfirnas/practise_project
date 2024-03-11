package com.ey.dt.masterdata.payload.response.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {
    protected HttpStatus status;
    protected String message;
    protected boolean success;
    protected T data;
    protected List<T> datalist;

    public ApiResponse(HttpStatus status, String message, boolean success, T data) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, String message, boolean success, List<T> datalist) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.datalist = datalist;
    }

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }


}
