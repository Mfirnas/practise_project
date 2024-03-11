package com.ey.dt.masterdata.service;

import com.ey.dt.masterdata.payload.response.SkillResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface SkillService {
    List<SkillResponse> getSkill();

    ApiResponse<Object> AddSkillSet(MultipartFile file) throws IOException;


}
