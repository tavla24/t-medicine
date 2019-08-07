package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/test") // , method = RequestMethod.GET
    public String test(ModelMap model) {
        List<User> lUser = new ArrayList<>();
        lUser.add(new User("My name (list)", "My login (list)"));
        //model.addAttribute("source", lUser);
        model.addAttribute("source", new User("My name", "My login"));
        return "test/test";
    }

    @PostMapping(value = "/test/get") // , method = RequestMethod.GET
    public @ResponseBody
    User ajaxRequest(ModelMap model) {
        List<User> response = new ArrayList<>();
        response.add(new User("ajax name (list)", "ajax login (list)"));
        //return response;
        return new User("ajax name", "ajax login");
    }

    class User{
        private String name;
        private String login;

        public User(String name, String login) {
            this.name = name;
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }
}
