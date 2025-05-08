package com.example.db;

import java.sql.Date;

public class Hospitalization {
    private int hospitalizationId;
    private int patientId;
    private int wardId;
    private int bedNumber;
    private String diagnosis;
    private int doctorId;
    private Date admissionDate;
    private Date dischargeDate; // nullable

    public Hospitalization(int hospitalizationId, int patientId, int wardId, int bedNumber, String diagnosis,
                           int doctorId, Date admissionDate, Date dischargeDate) {
        this.hospitalizationId = hospitalizationId;
        this.patientId = patientId;
        this.wardId = wardId;
        this.bedNumber = bedNumber;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
    }

    public int getHospitalizationId() {
        return hospitalizationId;
    }

    public void setHospitalizationId(int hospitalizationId) {
        this.hospitalizationId = hospitalizationId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
