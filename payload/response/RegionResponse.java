package com.ey.dt.masterdata.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegionResponse {
    private Long regionId;
    private String name;
    private Boolean isActive;
}
