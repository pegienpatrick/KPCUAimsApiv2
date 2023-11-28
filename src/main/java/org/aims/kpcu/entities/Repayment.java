package org.aims.kpcu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @ManyToOne
    private CFactory factory;

    private int year;

    private String season;


    private String payment;


    @OneToMany
    private List<MemberRepayment> repayments;

    private Date whenStarted;

    @ManyToOne
    private FChairman submittingChairman;
    private Date whenSubmitted;

    @ManyToOne
    private KpcuUser receivingKPCU;
    private Date whenReceived;
}
