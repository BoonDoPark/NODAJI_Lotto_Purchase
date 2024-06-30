package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.repository.TotalPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TotalPointServiceImpl implements TotalPointService {
    private final TotalPointRepository totalPointRepository;


    @Override
    public void matchRank(Map<Integer, List<String>> lottoResultPoint) {

    }
}
