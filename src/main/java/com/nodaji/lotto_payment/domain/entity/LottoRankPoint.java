package com.nodaji.lotto_payment.domain.entity;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "LOTTO_RANK_POINT")
public class LottoRankPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PAY_ID")
    private Long payId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ROUND")
    private Long round;

    @Column(name = "RANK")
    private Integer rank;

    @Setter
    @Column(name = "POINT")
    private Long point;
}
