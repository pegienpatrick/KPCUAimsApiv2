package org.aims.kpcu.entities;

import org.aims.kpcu.SmsManager;
import org.aims.kpcu.Utils;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long notificationId;

    private UserType userType;

    private Long ownerId;

    private Date sentOn;

    private Date readOn;

    private Boolean isRead;

    private String content;

    @Column(nullable = true)
    private Long loanId;

    private Boolean smsSent;



    public Notification()
    {
        this.sentOn= Utils.getLocalDate();
        this.isRead=false;
        this.readOn=null;
        smsSent=false;
    }

    public void sendSms(String phone)
    {
        smsSent = SmsManager.sendSms(phone,getContent());
    }

    public Boolean getSmsSent() {
        return smsSent;
    }

    public void setSmsSent(Boolean smsSent) {
        this.smsSent = smsSent;
    }





    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public Date getReadOn() {
        return readOn;
    }

    public void setReadOn(Date readOn) {
        this.readOn = readOn;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
}
