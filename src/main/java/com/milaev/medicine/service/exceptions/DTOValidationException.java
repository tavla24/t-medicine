package com.milaev.medicine.service.exceptions;

import com.milaev.medicine.dto.AccountDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;

public class DTOValidationException extends RuntimeException {
    private BindingResult result;
    private Object dto;
    private ModelAndView model;
    private String loggedinuser;
    private LinkedHashMap<String, Object> container = new LinkedHashMap<>();

    public DTOValidationException(){
    }

    public DTOValidationException(Object dto, BindingResult result) {
        this.result = result;
        this.dto = dto;
    }

    public DTOValidationException(Object dto, BindingResult result, String loggedinuser) {
        this(dto, result);
        this.loggedinuser = loggedinuser;
    }

    public DTOValidationException(Object dto, BindingResult result, ModelAndView model) {
        this(dto, result);
        this.model = model;
    }

    public DTOValidationException(Object dto, BindingResult result, ModelAndView model, String loggedinuser) {
        this(dto, result, model);
        this.loggedinuser = loggedinuser;
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }

    public Object getDTO() {
        return dto;
    }

    public void setDTO(Object account) {
        this.dto = account;
    }

    public ModelAndView getModel() {
        return model;
    }

    public void setModel(ModelAndView model) {
        this.model = model;
    }

    public void setContainer(LinkedHashMap<String, Object> container) {
        this.container = container;
    }

    public String getLoggedinuser() {
        return loggedinuser;
    }

    public void setLoggedinuser(String loggedinuser) {
        this.loggedinuser = loggedinuser;
    }

    public LinkedHashMap<String, Object> getContainer() {
        return container;
    }

    public void addObject(String key, Object obj) {
        container.put(key, obj);
    }
}
