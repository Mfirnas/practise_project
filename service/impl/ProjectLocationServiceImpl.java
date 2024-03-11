package com.ey.dt.masterdata.service.impl;

import com.ey.dt.masterdata.exception.CommonServerException;
import com.ey.dt.masterdata.payload.response.ProjectLocationResponse;
import com.ey.dt.masterdata.repository.ProjectLocationRepository;
import com.ey.dt.masterdata.service.ProjectLocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectLocationServiceImpl implements ProjectLocationService {

    private ProjectLocationRepository projectLocationRepository;
    private ModelMapper modelMapper;

    @Override
    public List<ProjectLocationResponse> getLocation() {
        try {
            log.info("LocationServiceImpl.getLocation() invoked.");
            return projectLocationRepository.findActiveProjectLocation().stream()
                    .map(loc -> modelMapper.map(loc, ProjectLocationResponse.class)).sorted(Comparator.comparing(ProjectLocationResponse::getLocation)).toList();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CommonServerException(e.getMessage());
        }
    }
}
