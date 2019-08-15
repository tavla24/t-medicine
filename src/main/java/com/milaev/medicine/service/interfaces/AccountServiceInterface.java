package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.AccountDTO;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

public interface AccountServiceInterface {
    List<AccountDTO> getAll();

    AccountDTO getByLogin(String login);

    AccountDTO getById(Long id);

    boolean isLoginUnique(String login);

    void deleteByLogin(String login);

    void update(AccountDTO dto, String oldLogin);

    void insert(AccountDTO dto);

    ModelAndView mavAccountsList();

    ModelAndView mavNewAccount(ModelMap model);

    ModelAndView mavNewAccount(@Valid AccountDTO account, BindingResult result,
                             RedirectAttributes redirectAttributes);

    ModelAndView mavDeleteAccount(String login);

    ModelAndView mavEditAccount(String login);

    ModelAndView mavEditAccount(AccountDTO account, BindingResult result, String login);
}
