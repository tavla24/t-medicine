package com.milaev.medicine.dto;

import java.util.List;

public class ViewDoctorDTO {
    private DoctorDTO doctor;
    private AccountDTO account;
    private List<DoctorDTO> doctors;
    private List<AccountDTO> accounts;

    public ViewDoctorDTO(){};

    public ViewDoctorDTO(DoctorDTO doctor, AccountDTO account) {
        this.doctor = doctor;
        this.account = account;
    }

    public DoctorDTO getDoctor() {
        if (doctor == null)
            doctor = new DoctorDTO();
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public AccountDTO getAccount() {
        if (account == null)
            account = new AccountDTO();
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorDTO> doctors) {
        this.doctors = doctors;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
