package com.milaev.medicine.controller;

import com.milaev.mq.message.StateChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    private static Logger log = LoggerFactory.getLogger(BoardController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StateChangedEvent listAccounts() {
        log.info("[board] get request for url /");
        StateChangedEvent rez = new StateChangedEvent("testREST");
        return rez;
    }
}
