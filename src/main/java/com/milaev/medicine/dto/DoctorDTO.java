package com.milaev.medicine.dto;

public class DoctorDTO extends PersonDTO {
    private String specialization;

//    private String login;
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("DoctorDTO result: specialization[%s]", specialization));
        return sb.toString();
    }
}
