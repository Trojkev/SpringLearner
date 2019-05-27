package com.kev.Learner.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "isomessage")
public class IsoMessage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mti", nullable = false)
    private String mti;

    @Column(name = "request", nullable = false)
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "trxdate", updatable = false, insertable = false, nullable = false)
    private Date trxDate;

    public IsoMessage() {}

    public IsoMessage(String mti, String request, String response) {
        this.mti = mti;
        this.request = request;
        this.response = response;
    }

    public long getId() {
        return id;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getTrxDate() {
        return this.trxDate;
    }
}
