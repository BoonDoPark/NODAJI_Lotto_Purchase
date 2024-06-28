package com.nodaji.lotto_payment.domain.dao;

import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LottoPaymentDaoImpl implements LottoPaymentDao{
    private final LottoResultRepository lottoResultRepository;

    @Override
    public Long getRound() {
        Optional<LottoResult> optionalResult = Optional.ofNullable(lottoResultRepository.findLastRow());
        if (optionalResult.isPresent() && optionalResult.get().getId() != null) {
            return optionalResult.get().getId()+1L;
        } else {
            return 1L; // 로또 처음 시작할때 첫 회차가 없어도 round가 필요
        }
    }
}
