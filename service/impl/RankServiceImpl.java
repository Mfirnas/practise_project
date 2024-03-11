package com.ey.dt.masterdata.service.impl;

import com.ey.dt.masterdata.exception.CommonServerException;
import com.ey.dt.masterdata.payload.response.RankResponse;
import com.ey.dt.masterdata.repository.RankRepository;
import com.ey.dt.masterdata.service.RankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RankServiceImpl implements RankService {
    private RankRepository rankRepository;
    private ModelMapper modelMapper;

    @Override
    public List<RankResponse> getRank() {
        try {
            log.info("RankServiceImpl.getRank() Invoked.");
            return rankRepository.findActiveRank().stream().map(rank -> modelMapper.map(rank,RankResponse.class)).toList();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CommonServerException(e.getMessage());
        }
    }
}
