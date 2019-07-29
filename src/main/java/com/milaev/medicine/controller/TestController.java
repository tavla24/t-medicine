package com.milaev.medicine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milaev.medicine.model.Account;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Controller
public class TestController {
	// TODO with slf4j
	private static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	// TODO only interfaces? why?
	@Autowired
	private TServiceInterface<Account> accountService;

	@RequestMapping("/f")
	public String hello2(Model model) {
		LOGGER.info("hello started");
		// List<Account> accs = accountService.getByLogin("user");
		// LOGGER.info("accs size: " + accs.size());
		// Account acc = accs.get(0);
		Account acc = accountService.getByLoginSingle("root");
		LOGGER.info("accountService.getById(X) done");
		LOGGER.info(acc.toString());
		model.addAttribute("greeting", "Hello Spring MVC by controller");
		LOGGER.info("/hellosys");
		return "main";
	}
}