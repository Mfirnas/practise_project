package com.ey.dt.masterdata.controller;

import com.ey.dt.masterdata.payload.response.SkillResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.service.SkillService;
import com.ey.dt.masterdata.utility.MessageConstant;
import com.ey.dt.masterdata.utility.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/masterdataservice/api/v1/skill")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SkillController {
    private MessageResource messageResource;
    private SkillService skillService;

    @GetMapping()
    @Operation(description = "Api for Skill")
    ResponseEntity<ApiResponse<SkillResponse>> getSkill(){
        log.info("SkillController.getSkill() invoked.");
        List<SkillResponse> skillResponses = skillService.getSkill();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, messageResource.getMessage(MessageConstant.SKILL_GET_SUCCESS),Boolean.TRUE,skillResponses));
    }


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse> addSkillSet(@RequestParam("file")MultipartFile file) throws IOException {
        log.info("SkillController.addSkillSet() invoked.");

        return ResponseEntity.ok(skillService.AddSkillSet(file));
    }
}
