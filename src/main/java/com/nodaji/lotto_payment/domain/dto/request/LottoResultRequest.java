package com.nodaji.lotto_payment.domain.dto.request;

import com.nodaji.lotto_payment.domain.entity.LottoResult;

import java.util.List;

public record LottoResultRequest(
        Integer first,
        Integer second,
        Integer third,
        Integer fourth,
        Integer fifth,
        Integer sixth,
        Integer bonus,
        String createAt
) {
    public LottoResult toEntity() {
        return LottoResult.builder()
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
                .sixth(sixth)
                .bonus(bonus)
                .createAt(createAt)
                .build();
    }

    public List<Integer> makeLottoResultList(LottoResult lottoResult) {
        return List.of(
                lottoResult.getFirst(),
                lottoResult.getSecond(),
                lottoResult.getThird(),
                lottoResult.getFourth(),
                lottoResult.getFifth(),
                lottoResult.getSixth()
        );
    }
}
