package com.ey.dt.masterdata.controller;

import com.ey.dt.masterdata.entities.Region;
import com.ey.dt.masterdata.payload.request.RegionRequest;
import com.ey.dt.masterdata.payload.response.RegionResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.service.RegionService;
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
@RequestMapping("/masterdataservice/api/v1/region")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegionController {
    private MessageResource messageResource;
    private RegionService regionService;

    /***
     *
     * @return gds regions
     */
    @GetMapping()
    @Operation(description = "Api for Region")
    ResponseEntity<ApiResponse<RegionResponse>> getAllRegion(){
        log.info("RegionController.getRegion() invoked.");
        List<RegionResponse> regionResponses = regionService.getAllRegion();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, messageResource.getMessage(MessageConstant.REGION_GET_SUCCESS),Boolean.TRUE,regionResponses));
    }

    /***
     *
     * @param region
     * @return successful region creation response
     */
    @PostMapping
    @Operation(description = "Api for create Region")
    ResponseEntity<ApiResponse<Object>>createRegion(@Valid @RequestBody RegionRequest region){
        log.info("RegionController.createRegion() invoked.");
        return ResponseEntity.ok(regionService.createRegion(region));
    }

    /***
     *
     * @param id
     * @return region for the specific id
     */
    @GetMapping("/{id}")
    @Operation(description = "Api for get Region by id")
    ResponseEntity<ApiResponse<Object>>getRegion(@Valid @PathVariable long id){
        log.info("RegionController.getRegion() invoked.");
        return ResponseEntity.ok(regionService.getRegion(id));

    }

    /***
     *
     * @param id
     * @return successful region deleted response
     */
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Object>>deleteRegion(@Valid @PathVariable long id){
        log.info("RegionController.getRegion() deleteRegion.");
        return ResponseEntity.ok(regionService.deleteRegion(id));
    }

    /***
     *
     * @param id
     * @param region request payload
     * @return successful region update response
     */
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<Object>>updateRegion(@Valid @PathVariable long id,@RequestBody RegionRequest request){
        log.info("RegionController.updateRegion() deleteRegion.");
        return ResponseEntity.ok(regionService.updateRegion(id,request));
    }

}
