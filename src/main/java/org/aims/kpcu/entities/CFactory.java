package org.aims.kpcu.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class CFactory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    private String fname;

    @Column(nullable = true)
    private Date LastUpdate;

    public Date getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }


}
