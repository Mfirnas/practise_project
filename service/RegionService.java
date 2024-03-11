package com.ey.dt.masterdata.service;

import com.ey.dt.masterdata.payload.request.RegionRequest;
import com.ey.dt.masterdata.payload.response.RegionResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;

import java.util.List;

public interface RegionService {
    List<RegionResponse> getAllRegion();

    ApiResponse<Object> createRegion(RegionRequest region);

    ApiResponse<Object>   getRegion(long id);

    ApiResponse<Object> deleteRegion(long id);

    ApiResponse<Object> updateRegion(long id, RegionRequest request);
}
