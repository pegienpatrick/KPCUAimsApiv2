package org.aims.kpcu.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRepayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @ManyToOne
    private Member member;

    private Double amount;
}
