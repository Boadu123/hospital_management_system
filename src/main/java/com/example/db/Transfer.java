package com.example.db;

import java.sql.Date;

public class Transfer {
    private int transferId;
    private int patientId;
    private int fromWardId;
    private int toWardId;
    private Date transferDate;

    public Transfer(int transferId, int patientId, int fromWardId, int toWardId, Date transferDate) {
        this.transferId = transferId;
        this.patientId = patientId;
        this.fromWardId = fromWardId;
        this.toWardId = toWardId;
        this.transferDate = transferDate;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getFromWardId() {
        return fromWardId;
    }

    public void setFromWardId(int fromWardId) {
        this.fromWardId = fromWardId;
    }

    public int getToWardId() {
        return toWardId;
    }

    public void setToWardId(int toWardId) {
        this.toWardId = toWardId;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
}
