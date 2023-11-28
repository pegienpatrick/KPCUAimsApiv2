package org.aims.kpcu.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity

public class DisbursementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Date doneOn;

    private String comment;

    private Long kpcuUserId;

    public String getStatus()
    {
        SimpleDateFormat sm=new SimpleDateFormat("dd - MMM - yyyy HH:mm:ss");
        if(kpcuUserId==null||doneOn==null)
            return "Not Disbursed";
        else
            return "Disburbed on "+sm.format(doneOn);
    }



    /**
     * get field @Id
     @GeneratedValue(strategy = GenerationType.TABLE)

      *
      * @return id @Id
     @GeneratedValue(strategy = GenerationType.TABLE)

     */
    public Long getId() {
        return this.id;
    }

    /**
     * set field @Id
     @GeneratedValue(strategy = GenerationType.TABLE)

      *
      * @param id @Id
     @GeneratedValue(strategy = GenerationType.TABLE)

     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get field
     *
     * @return doneOn
     */
    public Date getDoneOn() {
        return this.doneOn;
    }

    /**
     * set field
     *
     * @param doneOn
     */
    public void setDoneOn(Date doneOn) {
        this.doneOn = doneOn;
    }

    /**
     * get field
     *
     * @return comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * set field
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * get field
     *
     * @return kpcuUserId
     */
    public Long getKpcuUserId() {
        return this.kpcuUserId;
    }

    /**
     * set field
     *
     * @param kpcuUserId
     */
    public void setKpcuUserId(Long kpcuUserId) {
        this.kpcuUserId = kpcuUserId;
    }
}
