package org.aims.kpcu.entities;

import javax.persistence.*;

@Entity
public class FactoryAuthorization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @OneToOne
    private CFactory factory;

    private String authkey;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public CFactory getFactory() {
        return factory;
    }

    public void setFactory(CFactory factory) {
        this.factory = factory;
    }

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }
}
