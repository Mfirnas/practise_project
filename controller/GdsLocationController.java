package com.ey.dt.masterdata.controller;

import com.ey.dt.masterdata.payload.response.GdsLocationRequest;
import com.ey.dt.masterdata.payload.response.GdsLocationResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.service.GdsLocationService;
import com.ey.dt.masterdata.utility.MessageConstant;
import com.ey.dt.masterdata.utility.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/masterdataservice/api/v1/gdslocation")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GdsLocationController {
    private GdsLocationService gdsLocationService;
    private MessageResource messageResource;

    @GetMapping
    @Operation(description = "Api for GDS Location")
    ResponseEntity<ApiResponse<GdsLocationResponse>> getGdsLocation(){
        log.info("LocationController.getLocation() invoked.");
        List<GdsLocationResponse> gdsLocationResponses = gdsLocationService.getLocation();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, messageResource.getMessage(MessageConstant.GDS_LOCATION_GET_SUCCESS),Boolean.TRUE, gdsLocationResponses));
    }

    @PostMapping
    @Operation(description = "Api for create GDS Location")
    ResponseEntity<ApiResponse> createLocation(@Valid @RequestBody GdsLocationRequest gdsLocation){
         log.info("LocationController.createLocation() invoked.");
         return  ResponseEntity.ok(gdsLocationService.createGdsLocation(gdsLocation));
    }

    @GetMapping("/{id}")
    @Operation(description = "Api for find GDS Location by id")
    ResponseEntity<ApiResponse> getLocation(@Valid @PathVariable long id){
        log.info("LocationController.getLocation() invoked.");
        return  ResponseEntity.ok(gdsLocationService.getLocationById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Api for delete GDS Location")
    ResponseEntity<ApiResponse> deleteLocation(@Valid @PathVariable long id){
        log.info("LocationController.deleteLocation() invoked.");
        return ResponseEntity.ok(gdsLocationService.deleteLocation(id));
    }

    @PutMapping("/{id}")
    @Operation(description = "Api for update GDS Location")
    ResponseEntity<ApiResponse<GdsLocationResponse>> updateLocation(@Valid @PathVariable long id,@RequestBody GdsLocationRequest location){
        log.info("LocationController.updateLocation() invoked.");
        return  ResponseEntity.ok(gdsLocationService.updateGDSLocation(id,location));
    }


}
