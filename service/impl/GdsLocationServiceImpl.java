package com.ey.dt.masterdata.service.impl;

import com.ey.dt.masterdata.entities.GdsLocation;
import com.ey.dt.masterdata.exception.CommonServerException;
import com.ey.dt.masterdata.payload.response.GdsLocationRequest;
import com.ey.dt.masterdata.payload.response.GdsLocationResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.repository.GdsLocationRepository;
import com.ey.dt.masterdata.service.GdsLocationService;
import com.ey.dt.masterdata.utility.MessageConstant;
import com.ey.dt.masterdata.utility.MessageResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class GdsLocationServiceImpl implements GdsLocationService {

    private GdsLocationRepository locationRepository;
    private ModelMapper modelMapper;

    private MessageResource messageResource;

    /***
     * Method for list of locations
     * @return list of GDS Locations
     */
    @Override
    public List<GdsLocationResponse> getLocation() {
        try {
            log.info("LocationServiceImpl.getLocation() invoked.");
            return locationRepository.findActiveGDSLocation().stream()
                    .map(loc -> modelMapper.map(loc, GdsLocationResponse.class)).sorted(Comparator.comparing(GdsLocationResponse::getLocation)).toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CommonServerException(e.getMessage());
        }
    }

    /***
     *
     * @param gdsLocation
     * @return response for successful location creation
     *
     */
    @Override
    public ApiResponse<Object> createGdsLocation(GdsLocationRequest gdsLocation) {

        List<GdsLocation>gdsLocations=locationRepository.findByLocation(gdsLocation.getLocation());

         if(gdsLocations.isEmpty()) {

           try {
               log.info("LocationServiceImpl.CreateGdsLocation() invoked.");
               GdsLocation gdsLocation1=modelMapper.map(gdsLocation,GdsLocation.class);
               GdsLocation data=    locationRepository.save(gdsLocation1);
               GdsLocationResponse response=modelMapper.map(data,GdsLocationResponse.class);
               return ApiResponse.builder()
                       .status(HttpStatus.OK)
                       .data(response)
                       .success(true)
                       .message(messageResource.getMessage(MessageConstant.GDS_LOCATION_CREATE_SUCCESS))
                       .build();
           } catch (Exception e) {
               log.error(e.getMessage());
               throw new CommonServerException(e.getMessage());
           }
       }
       else {

           return ApiResponse.builder()
                   .status(HttpStatus.OK)
                   .success(true)
                   .message(messageResource.getMessage(MessageConstant.GDS_LOCATION_ALREADY_EXIST))
                   .build();
       }
    }

    /***
     *
     * @param id
     * @return response for successful retrieve location
     */
    @Override
    public ApiResponse<Object> getLocationById(long id) {
        log.info("LocationServiceImpl.getLocationById() invoked.");
        try {


             Optional<GdsLocation> location = locationRepository.findById(id);
             GdsLocationResponse data=modelMapper.map(location,GdsLocationResponse.class);
            return ApiResponse.builder()
                    .status(HttpStatus.OK)
                    .data(data)
                    .message(messageResource.getMessage(MessageConstant.GDS_LOCATION_GET_SUCCESS))
                    .success(true)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw  new CommonServerException(e.getMessage());
        }


    }

    /***
     *
     * @param id
     * @return response for successful location delete
     */
    @Override
    public ApiResponse<Object> deleteLocation(long id) {
        log.info("LocationServiceImpl.deleteLocation() invoked.");
        if(locationRepository.findById(id).isPresent()) {

               locationRepository.deleteById(id);
                  try {
                      return ApiResponse.builder()
                              .status(HttpStatus.OK)
                              .success(true)
                              .message(messageResource.getMessage(MessageConstant.GDS_LOCATION_DELETE_SUCCESS))
                              .build();
                  }catch (Exception e){
                      log.error(e.getMessage());
                      throw new CommonServerException(e.getMessage());
                  }

           }
        else {
               return  ApiResponse.builder()
                       .status(HttpStatus.OK)
                       .success(true)
                       .message(messageResource.getMessage(MessageConstant.GDS_LOCATION_NOT_FOUND))
                       .build();
           }

    }

    /***
     *
     * @param id
     * @param location
     * @return response for successful update location
     */
    @Override
    public ApiResponse<GdsLocationResponse> updateGDSLocation(long id, GdsLocationRequest location) {
        log.info("LocationServiceImpl.deleteLocation() invoked.");

        ApiResponse response=new ApiResponse();
        response.setStatus(HttpStatus.OK);
        response.setSuccess(true);

        if(locationRepository.findById(id).isPresent()) {
            try {
                locationRepository.findById(id).map(locations -> {
                    locations.setLocation(location.getLocation());
                    locations.setIsActive(location.getIsActive());
                    locationRepository.save(locations);
                    response.setMessage(messageResource.getMessage(MessageConstant.GDS_LOCATION_UPDATE_SUCCESS));
                    GdsLocationResponse data=modelMapper.map(locations,GdsLocationResponse.class);
                    response.setData(data);
                     return locations;
                });
                return response;
            }catch (Exception e){
                log.error(e.getMessage());
                throw  new CommonServerException(e.getMessage());
            }
        }
          response.setSuccess(true);
        response.setMessage(messageResource.getMessage(MessageConstant.GDS_LOCATION_NOT_FOUND));
         return response;

    }

}
