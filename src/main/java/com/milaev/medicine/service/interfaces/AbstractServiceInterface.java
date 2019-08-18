package com.milaev.medicine.service.interfaces;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface AbstractServiceInterface <T> {

    List<T> getAll();

}
