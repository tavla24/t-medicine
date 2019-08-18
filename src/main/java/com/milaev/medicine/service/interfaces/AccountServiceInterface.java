package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.AccountDTO;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

public interface AccountServiceInterface {

    String PAGE_LIST = "account/list";
    String PAGE_REGISTRATION = "account/registration";

    String URI_LIST = "/admin/account/list";

    List<AccountDTO> getAll();

    AccountDTO getByLogin(String login);

    AccountDTO getById(Long id);

    boolean isLoginUnique(String login);

    void deleteByLogin(String login);

    void update(AccountDTO dto, String oldLogin);

    void insert(AccountDTO dto);

    ModelAndView mavList();

    ModelAndView mavNew();

    ModelAndView mavNew(AccountDTO dto, BindingResult result);

    ModelAndView mavDelete(String login);

    ModelAndView mavEdit(String login);

    ModelAndView mavEdit(AccountDTO dto, BindingResult result, String login);
}
