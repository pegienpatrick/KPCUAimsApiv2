package org.aims.kpcu.entities;

import javax.persistence.*;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @Column(unique = true)
    private String fname;


    @Column(unique = true)
    private String hint;


    /**
     * get field @Id
     @GeneratedValue(strategy = GenerationType.AUTO)

      *
      * @return num @Id
     @GeneratedValue(strategy = GenerationType.AUTO)

     */
    public Long getNum() {
        return this.num;
    }

    /**
     * set field @Id
     @GeneratedValue(strategy = GenerationType.AUTO)

      *
      * @param num @Id
     @GeneratedValue(strategy = GenerationType.AUTO)

     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * get field @Column(unique = true)
     *
     * @return fname @Column(unique = true)

     */
    public String getFname() {
        return this.fname;
    }

    /**
     * set field @Column(unique = true)
     *
     * @param fname @Column(unique = true)

     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * get field @Column(unique = true)
     *
     * @return hint @Column(unique = true)

     */
    public String getHint() {
        return this.hint;
    }

    /**
     * set field @Column(unique = true)
     *
     * @param hint @Column(unique = true)

     */
    public void setHint(String hint) {
        this.hint = hint;
    }
}
