package com.ey.dt.masterdata.controller;

import com.ey.dt.masterdata.payload.response.RoleResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.service.RoleService;
import com.ey.dt.masterdata.utility.MessageConstant;
import com.ey.dt.masterdata.utility.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/masterdataservice/api/v1/role")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {
    private MessageResource messageResource;
    private RoleService roleService;

    @GetMapping()
    @Operation(description = "Api for Role")
    ResponseEntity<ApiResponse<RoleResponse>> getRole(){
        log.info("RoleController.getRole() invoked.");
        List<RoleResponse> roleResponses = roleService.getRole();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, messageResource.getMessage(MessageConstant.ROLE_GET_SUCCESS),Boolean.TRUE,roleResponses));
    }
}
