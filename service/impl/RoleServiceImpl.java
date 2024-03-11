package com.ey.dt.masterdata.service.impl;

import com.ey.dt.masterdata.exception.CommonServerException;
import com.ey.dt.masterdata.payload.response.RoleResponse;
import com.ey.dt.masterdata.repository.RoleRepository;
import com.ey.dt.masterdata.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private ModelMapper modelMapper;


    @Override
    public List<RoleResponse> getRole() {
        try {
            log.info("RoleServiceImpl.getRole() Invoked.");
            return roleRepository.findActiveRole().stream().map(role -> modelMapper.map(role,RoleResponse.class)).toList();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CommonServerException(e.getMessage());
        }
    }
}
