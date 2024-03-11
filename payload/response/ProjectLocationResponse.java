package com.ey.dt.masterdata.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectLocationResponse {
    private Long projectLocationId;
    private String location;
    private Boolean isActive;
}
