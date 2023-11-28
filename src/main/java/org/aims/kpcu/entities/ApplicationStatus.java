package org.aims.kpcu.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    private ApplicationResponse response;

    private String reason;

    @ManyToOne
    private FChairman chairman;
    private Date respondedon;

    @ManyToOne
    private DisbursementStatus disbursementStatus;

    public ApplicationStatus()
    {
        response=ApplicationResponse.PENDING;
        reason="pending";
        chairman=null;
        respondedon=null;
    }

    public ApplicationResponse getResponse() {
        return response;
    }

    public void setResponse(ApplicationResponse response) {
        this.response = response;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public FChairman getChairman() {
        return chairman;
    }

    public void setChairman(FChairman chairman) {
        this.chairman = chairman;
    }

    public Date getRespondedon() {
        return respondedon;
    }

    public void setRespondedon(Date respondedon) {
        this.respondedon = respondedon;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public DisbursementStatus getDisbursementStatus() {
        return disbursementStatus;
    }

    public void setDisbursementStatus(DisbursementStatus disbursementStatus) {
        this.disbursementStatus = disbursementStatus;
    }

    public String getDisbursement()
    {
        SimpleDateFormat sm=new SimpleDateFormat("dd - MMM - yyyy HH:mm:ss");
        if(disbursementStatus==null||disbursementStatus.getKpcuUserId()==null||disbursementStatus.getDoneOn()==null)
            return "Not Disbursed";
        else
            return "Disburbed on "+sm.format(disbursementStatus.getDoneOn());
    }
}


