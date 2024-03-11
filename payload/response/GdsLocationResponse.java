package com.ey.dt.masterdata.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class GdsLocationResponse {
    private Long gdsLocationId;
    private String location;
    private Boolean isActive;
}
