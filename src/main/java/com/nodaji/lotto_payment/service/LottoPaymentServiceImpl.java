package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.config.apis.ApiPoint;
import com.nodaji.lotto_payment.domain.dao.LottoPaymentDao;
import com.nodaji.lotto_payment.domain.dto.request.LottoPayRequest;
import com.nodaji.lotto_payment.domain.dto.request.LottoPaymentRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.dto.response.PointResponse;
import com.nodaji.lotto_payment.domain.entity.LottoPayment;
import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.entity.TotalPoint;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import com.nodaji.lotto_payment.domain.repository.TotalPointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LottoPaymentServiceImpl implements LottoPaymentService{
    private final LottoPaymentRepository lottoPaymentRepository;
    private final LottoPaymentDao lottoPaymentDao;
    private final TotalPointRepository totalPointRepository;
    private final ApiPoint apiPoint;

    @Override
    public void save(String userId, List<LottoPaymentRequest> requests) {
        if (requests.isEmpty()) throw new IllegalArgumentException();
        // point 전달 유효한지 체크
//        PointResponse pointResponse = apiPoint.getPoint(requests.get(0).userId());
//        if (pointResponse.amount() < (requests.size() * 1000L)) throw new IllegalArgumentException("포인트가 부족합니다.");

        // payment에 point 전달
//        apiPoint.subtractPoint(requests.get(0).userId(), LottoPayRequest.payRequest("동행복권", requests.size()*1000L));

        Long finalRound = lottoPaymentDao.getRound();
        totalPointRepository.save(new TotalPoint(finalRound, requests.size()*1000L));

        requests.forEach(req -> {
                    lottoPaymentRepository.save(req.toEntity(finalRound, userId));
                }
        );
    }

    @Override
    public List<LottoPaymentResponse> getAllByUserIdAndRoundId(Long round) {
        // 유저 id 필요
        return lottoPaymentRepository.findByRound(round);
    }

}
