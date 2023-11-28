package org.aims.kpcu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class FChairman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    private String username;

    @JsonIgnore
    private String password;


    private String email;
    private String phone;

    @JsonIgnore
    @ManyToMany
    private List<CFactory> roles;

    @JsonIgnore
    private Date addedon;

    private String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CFactory> getRoles() {
        return roles;
    }

    public void setRoles(List<CFactory> roles) {
        this.roles = roles;
    }

    public Date getAddedon() {
        return addedon;
    }

    public void setAddedon(Date addedon) {
        this.addedon = addedon;
    }
}
