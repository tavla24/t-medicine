package com.milaev.medicine.dto;

public class PatientDTO extends PersonDTO {
    private String insuranceId;
    private String diagnosis;
    private String status;// = PatientStatus.ILL.getPatientStatus();
    private DoctorDTO doctor;

    //private RecipeDTO recipe;

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

}
