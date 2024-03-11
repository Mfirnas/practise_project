package com.ey.dt.masterdata.payload.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GdsLocationRequest {

    @NotNull
    private String location;

    @NotNull
    private Boolean isActive;
}
