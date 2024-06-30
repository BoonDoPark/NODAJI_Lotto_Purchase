package com.nodaji.lotto_payment.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TOTAL_POINT")
public class TotalPoint {
    @Id
    @Column(name = "ROUND")
    private Long round;

    @Column(name = "TOTAL_POINT")
    private Long totalPoint;
}
