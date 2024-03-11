package com.ey.dt.masterdata.service.impl;

import com.ey.dt.masterdata.entities.Region;
import com.ey.dt.masterdata.exception.CommonServerException;
import com.ey.dt.masterdata.payload.request.RegionRequest;
import com.ey.dt.masterdata.payload.response.RegionResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.repository.RegionRepository;
import com.ey.dt.masterdata.service.RegionService;
import com.ey.dt.masterdata.utility.MessageConstant;
import com.ey.dt.masterdata.utility.MessageResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;
    private ModelMapper modelMapper;

    private MessageResource messageResource;

    @Override
    public List<RegionResponse> getAllRegion() {
        try {
            log.info("RegionServiceImpl.getRegion() Invoked.");
            return regionRepository.findActiveRegion().stream().map(region -> mapObjectToRegionResponse(region)).toList();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CommonServerException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Object> createRegion(RegionRequest regionRequest) {
        log.info("RegionServiceImpl.createRegion() Invoked.");
      Region region=regionRepository.findByName(regionRequest.getName());
     if(ObjectUtils.isEmpty(region)) {

         try {

             Region data=   regionRepository.save(mapObjecttoRegion(regionRequest));
             return ApiResponse.builder()
                     .status(HttpStatus.OK)
                     .data(mapObjectToRegionResponse(data))
                     .success(true)
                     .message(messageResource.getMessage(MessageConstant.REGION_CREATED))
                     .build();
         } catch (Exception e) {
             log.error(e.getMessage());
             throw new CommonServerException(e.getMessage());
         }
     }else{
         return ApiResponse.builder()
                 .success(true)
                 .status(HttpStatus.OK)
                 .message(messageResource.getMessage(MessageConstant.REGION_ALREADY_EXIST))
                 .build();
     }

    }


    @Override
    public ApiResponse<Object> getRegion(long id) {
        log.info("RegionServiceImpl.createRegion() Invoked.");
        try{
            Optional<Region> data=regionRepository.findById(id);
            return ApiResponse.builder()
                    .status(HttpStatus.OK)
                    .message(messageResource.getMessage(MessageConstant.REGION_GET_SUCCESS))
                    .data(mapObjectToRegionResponse(data))
                    .success(true)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw  new CommonServerException(e.getMessage());
        }

    }

    @Override
    public ApiResponse<Object> deleteRegion(long id) {
        log.info("RegionServiceImpl.deleteRegion() Invoked.");
        if(regionRepository.findById(id).isEmpty()){
            return ApiResponse.builder()
                    .status(HttpStatus.OK)
                    .message(messageResource.getMessage(MessageConstant.REGION_NOT_FOUND))
                    .success(true)
                    .build();
        }
        else {
            try {
                regionRepository.deleteById(id);
               return ApiResponse.builder()
                       .status(HttpStatus.OK)
                       .success(true)
                       .message(messageResource.getMessage(MessageConstant.REGION_DELETED_SUCCESSFULLY))
                       .build();

            }catch (Exception e){
                log.error(e.getMessage());
                throw  new CommonServerException(e.getMessage());
            }
        }
    }

    @Override
    public ApiResponse<Object> updateRegion(long id, RegionRequest request) {
        log.info("RegionServiceImpl.deleteRegion() Invoked.");
        ApiResponse response=new ApiResponse();
        response.setStatus(HttpStatus.OK);
        response.setSuccess(true);
        if (regionRepository.findById(id).isPresent()){

            try {
                regionRepository.findById(id).map(region -> {
                    region.setName(request.getName());
                    region.setIsActive(request.getIsActive());
                    Region data = regionRepository.save(region);
                    response.setData(mapObjectToRegionResponse(data));
                    response.setMessage(messageResource.getMessage(MessageConstant.REGION_UPDATED));
                    return region;
                });
                return response;
            }catch (Exception e){
                log.error(e.getMessage());
                throw new CommonServerException(e.getMessage());
            }
        }
        response.setMessage(messageResource.getMessage(MessageConstant.REGION_NOT_FOUND));
        return response;
    }

    /***
     * This method will map the current dto object to Region object.
     * @param DTO object
     * @return Region Object
     */
    private Region mapObjecttoRegion(Object requestObject){

        return  modelMapper.map(requestObject,Region.class);
    }

    /***
     * This method will map the current object to regionRespone Object
     * @param requestObject
     * @return regionResponse
     */
    private RegionResponse mapObjectToRegionResponse(Object requestObject){
        return  modelMapper.map(requestObject,RegionResponse.class);
    }
}
