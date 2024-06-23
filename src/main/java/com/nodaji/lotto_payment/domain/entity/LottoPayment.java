package com.nodaji.lotto_payment.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "LOTTO_TICKETS")
public class LottoPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @CreatedDate
    @Column(name = "CREATE_AT")
    private String createAt;

    @Column(name = "ROUND")
    private Long round;

    @Column(name = "USER_ID")
    private UUID userId;
}
