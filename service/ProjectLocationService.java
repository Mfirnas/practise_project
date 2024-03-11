package com.ey.dt.masterdata.service;

import com.ey.dt.masterdata.payload.response.ProjectLocationResponse;

import java.util.List;

public interface ProjectLocationService {
    List<ProjectLocationResponse> getLocation();
}
