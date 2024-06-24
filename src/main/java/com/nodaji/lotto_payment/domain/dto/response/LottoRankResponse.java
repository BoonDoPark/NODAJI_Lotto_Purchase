package com.nodaji.lotto_payment.domain.dto.response;

import java.util.*;

public record LottoRankResponse(

) {
    public Map<UUID, Integer> makeMaxRankByUser(UUID userId, int rank) {
        Map<UUID, Integer> userRank = new HashMap<>();
        List<Integer> rankList = makeRankList(rank);
        Integer maxRank = Collections.max(rankList);
        userRank.merge(userId, maxRank, Math::min);
        return userRank;
    }

    public List<Integer> makeRankList(int rank) {
        List<Integer> rankList = new ArrayList<>();
        rankList.add(rank);
        return rankList;
    }
}
