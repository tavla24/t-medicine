package com.milaev.medicine.dto;

import com.milaev.medicine.model.enums.PatientStatus;

public class PatientDTO extends PersonDTO {
    private long insuranceId;
    private String diagnosis;
    private PatientStatus status;
    private DoctorDTO doctor;

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

}
