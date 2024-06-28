package com.nodaji.lotto_payment.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "LOTTO_RESULT")
public class LottoResult {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST")
    private Integer first;

    @Column(name = "SECOND")
    private Integer second;

    @Column(name = "THIRD")
    private Integer third;

    @Column(name = "FOURTH")
    private Integer fourth;

    @Column(name = "FIFTH")
    private Integer fifth;

    @Column(name = "SIXTH")
    private Integer sixth;

    @Column(name = "BONUS")
    private Integer bonus;

    @Column(name = "CREATE_AT")
    private String createAt;
}
