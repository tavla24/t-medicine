package com.milaev.medicine.controller;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.DayNameTypes;
import com.milaev.medicine.model.enums.DayPartTypes;
import com.milaev.medicine.model.enums.HealingType;
import com.milaev.medicine.service.interfaces.RecipeSimpleServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    RecipeSimpleServiceInterface recipeService;

    @GetMapping(value = "/test") // , method = RequestMethod.GET
    public String test3(ModelMap model) {
        RecipeSimpleDTO dto = recipeService.getByInsuranceId("98723509");
        dto.convToDayNamesList();
        dto.convToDayPartsList();
        dto.translate();

        List<String> sourceHealingTypes = HealingType.getTypeList();
        List<String> sourceDayNames = DayNameTypes.getTypeList();
        List<String> sourceDayParts = DayPartTypes.getTypeList();

        model.addAttribute("sourceHealingTypes", sourceHealingTypes);
        model.addAttribute("sourceDayNames", sourceDayNames);
        model.addAttribute("sourceDayParts", sourceDayParts);
        model.addAttribute("recipes", dto);
        return "recipe/registration_simple";
    }

    @PostMapping(value = "/test") // , method = RequestMethod.GET
    public String test3Post(@Valid RecipeSimpleDTO dto, BindingResult result, ModelMap model,
                            RedirectAttributes redirectAttributes) {
        return "recipe/registration";
    }


    @GetMapping(value = "/test2") // , method = RequestMethod.GET
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

    // этот метод будет принимать время методом GET и на его основе
    // отвечать клиенту
    @RequestMapping(value= "/{time}", method = RequestMethod.GET)
    @ResponseBody
    public MyDataObject getMyData(@PathVariable long time) {
        return new MyDataObject(Calendar.getInstance(), "Это ответ метода GET!");
    }

    // этот метод будет принимать Объект MyDataObject и отдавать его клиенту
    // обычно метод PUT используют для сохранения либо для обновления объекта
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public MyDataObject putMyData(@RequestBody MyDataObject md) {
        return md;
    }

    // этот метод будет методом POST отдавать объект MyDataObject
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MyDataObject postMyData() {
        return new MyDataObject(Calendar.getInstance(), "это ответ метода POST!");
    }

    // этот метод будет принимать время методом DELETE
    // и на его основе можно удалит объект
    @RequestMapping(value= "/{time}", method = RequestMethod.DELETE)
    @ResponseBody
    public MyDataObject deleteMyData(@PathVariable long time) {
        return new MyDataObject(Calendar.getInstance(), "Это ответ метода DELETE!");
    }

    class MyDataObject {

        private Calendar time;
        private String message;

        public MyDataObject(Calendar time, String message) {
            this.time = time;
            this.message = message;
        }

        public MyDataObject() {
        }

        public Calendar getTime() {
            return time;
        }

        public void setTime(Calendar time) {
            this.time = time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
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
