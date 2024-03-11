package com.ey.dt.masterdata.service;

import com.ey.dt.masterdata.payload.response.GdsLocationRequest;
import com.ey.dt.masterdata.payload.response.GdsLocationResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;

import java.util.List;

public interface GdsLocationService {
    List<GdsLocationResponse> getLocation();

    ApiResponse<Object> createGdsLocation(GdsLocationRequest gdsLocation);

    ApiResponse<Object> getLocationById(long id);

    ApiResponse<Object> deleteLocation(long id);

    ApiResponse<GdsLocationResponse> updateGDSLocation(long id, GdsLocationRequest location);
}
