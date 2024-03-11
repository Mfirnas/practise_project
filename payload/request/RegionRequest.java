package com.ey.dt.masterdata.payload.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegionRequest {
    private long id;


    private String name;


    private Boolean isActive;
}
