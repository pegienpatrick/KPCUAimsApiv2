package org.aims.kpcu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long num;
    private String memberid;
    private String memberName;
    private String phone;
    private String altphone;

    private String bank;

    private String b_account;

    private int trees;

    private double currentCherry;
    private double currentMbuni;
    private String currentSeason;

    private double otheradvances;

    @Transient
    private Double balance=0.0;

    @ManyToOne
    private CFactory factory;

    public Double getLoanLimit()
    {
        double limit=currentCherry*20-getBalance();
        if(limit<0)
            limit=0.0;

        return limit;
    }

    public Double getBalance() {

        if(balance!=null)
            return balance;
        else
            return 0.0;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAltphone() {
        return altphone;
    }

    public void setAltphone(String altphone) {
        this.altphone = altphone;
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

    public int getTrees() {
        return trees;
    }

    public void setTrees(int trees) {
        this.trees = trees;
    }

    public double getCurrentCherry() {
        return currentCherry;
    }

    public void setCurrentCherry(double currentCherry) {
        this.currentCherry = currentCherry;
    }

    public double getCurrentMbuni() {
        return currentMbuni;
    }

    public void setCurrentMbuni(double currentMbuni) {
        this.currentMbuni = currentMbuni;
    }

    public String getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(String currentSeason) {
        this.currentSeason = currentSeason;
    }

    public double getOtheradvances() {
        return otheradvances;
    }

    public void setOtheradvances(double otheradvances) {
        this.otheradvances = otheradvances;
    }

    public CFactory getFactory() {
        return factory;
    }

    public void setFactory(CFactory factory) {
        this.factory = factory;
    }
}
