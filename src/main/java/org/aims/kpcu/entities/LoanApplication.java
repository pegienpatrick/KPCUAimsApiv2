package org.aims.kpcu.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LoanApplication {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @ManyToOne
    private Member member;

    private ApplicationType applicationType;

    @ManyToOne
    private FManager maker;


    private Date madeOn;

    @OneToOne
    private ApplicationStatus status;

    private Double amount;

    private String bank;
    private String b_account;


    public LoanApplication(LoanApplication loanApplication) {
        this.setNum(loanApplication.getNum());
        this.setMember(loanApplication.getMember());
        this.setApplicationType(loanApplication.getApplicationType());
        this.setMaker(loanApplication.getMaker());
        this.setMadeOn(loanApplication.getMadeOn());
        this.setStatus(loanApplication.getStatus());
        this.setAmount(loanApplication.getAmount());
        this.setBank(loanApplication.getBank());
        this.setB_account(loanApplication.getB_account());
    }

    public LoanApplication()
    {

    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public FManager getMaker() {
        return maker;
    }

    public void setMaker(FManager maker) {
        this.maker = maker;
    }

    public Date getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(Date madeOn) {
        this.madeOn = madeOn;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getB_account() {
        return b_account;
    }

    public void setB_account(String b_account) {
        this.b_account = b_account;
    }


}
