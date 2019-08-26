package com.milaev.medicine.dto;

import com.milaev.medicine.model.enums.PatientStatus;

import java.util.List;

public class PatientDTO extends PersonDTO {
    private String insuranceId;
    private String diagnosis;
    private String status;
    private DoctorDTO doctor;

    private String oldInsuranceId;

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DoctorDTO getDoctor() {
        if (doctor == null)
            doctor = new DoctorDTO();
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public String getOldInsuranceId() {
        return oldInsuranceId;
    }

    public void setOldInsuranceId(String oldInsuranceId) {
        this.oldInsuranceId = oldInsuranceId;
    }

    public boolean isOldInsuranceIdEmpty() {
        return oldInsuranceId.isEmpty();
    }

    public boolean isInsuranceIdEqualsOld() {
        return oldInsuranceId.equals(insuranceId);
    }

    public List<String> getStatuses() {
        return PatientStatus.getPatientStatusList();
    }

}
