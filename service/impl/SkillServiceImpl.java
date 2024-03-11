package com.ey.dt.masterdata.service.impl;

import com.ey.dt.masterdata.entities.Skill;
import com.ey.dt.masterdata.entities.SubSkill;
import com.ey.dt.masterdata.exception.CommonServerException;
import com.ey.dt.masterdata.payload.response.SkillResponse;
import com.ey.dt.masterdata.payload.response.common.ApiResponse;
import com.ey.dt.masterdata.repository.SkillRepository;
import com.ey.dt.masterdata.service.SkillService;
import com.ey.dt.masterdata.utility.MessageConstant;
import com.ey.dt.masterdata.utility.MessageResource;
import com.ey.dt.masterdata.utility.SkillCsvFormat;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SkillServiceImpl implements SkillService {

    private MessageResource messageResource;
    private SkillRepository skillRepository;
    private ModelMapper modelMapper;


    @Override
    public List<SkillResponse> getSkill() {
        try {
            log.info("SkillServiceImpl.getSkill() Invoked.");
            return skillRepository.findActiveSkill().stream().map(skill -> modelMapper.map(skill,SkillResponse.class)).toList();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CommonServerException(e.getMessage());
        }
    }

    /***
     *
     * @param file
     * @return this method is responsible for bulk read skills and sub skills from csv file and persist it to DB
     * @throws IOException
     */
    @Override
    public ApiResponse<Object> AddSkillSet(MultipartFile file) throws IOException {
        log.info("SkillServiceImpl.AddSkillSet() Invoked.");
        Set<Skill>skills=processCsvFile(file);

        List<Skill> exsistingList= skillRepository.findAll();
        skills.removeAll(exsistingList);
        skillRepository.saveAll(skills);


        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message(skills.size()+" "+messageResource.getMessage(MessageConstant.RECORDS_ADDED))
                .success(true)
                .build();
    }

    /***
     *
     * @param file
     * @return this method will responsible to convert the csv data's for objects and return the set of skills
     * @throws IOException
     */
    private Set<Skill> processCsvFile(MultipartFile file) throws IOException {
        log.info("SkillServiceImpl.processCsvFile() Invoked.");
        try(Reader reader=new BufferedReader(new InputStreamReader(file.getInputStream()))){


            HeaderColumnNameMappingStrategy<SkillCsvFormat>mappingStrategy=new HeaderColumnNameMappingStrategy();
            mappingStrategy.setType(SkillCsvFormat.class);

            CsvToBean<SkillCsvFormat>csvToBean =
                    new CsvToBeanBuilder<SkillCsvFormat>(reader)
                            .withMappingStrategy(mappingStrategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)

                            .build();

                    return   csvToBean.parse()
                    .stream()
                    .map(csvLine->Skill.builder()
                            .name(csvLine.getPrimarySkill())
                            .isActive(true)
                            .subSkills(processSubSkill(csvLine.getSubSkill()))
                            .build()
                    )
                    .collect(Collectors.toSet());
        }

    }

    /***
     *
     * @param subSkill
     * @return this method will convert the csv sub skills sub skill object and return the set of sub skills
     */
    private Set<SubSkill> processSubSkill(String subSkill) {
        log.info("SkillServiceImpl.processSubSkill() Invoked.");
        try {
            Set<String> upProcessedSubSkills = Arrays.stream(subSkill.split(",")).collect(Collectors.toSet());

            Set<SubSkill> subSkills = new HashSet<>();

            for (String skillName : upProcessedSubSkills) {
          if(!skillName.isBlank()){
              SubSkill skill = new SubSkill();
              skill.setName(skillName);
              subSkills.add(skill);
          }

            }
            return subSkills;
        }catch (Exception e){
            throw  new CommonServerException(e.getMessage());
        }
    }


}
