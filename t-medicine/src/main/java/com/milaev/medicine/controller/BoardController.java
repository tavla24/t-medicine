package com.milaev.medicine.controller;

import com.milaev.medicine.service.EventService;
import com.milaev.mq.data.ExchangeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private static Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExchangeData> listAccounts() {
        log.info("[board] get request for url /");
        List<ExchangeData> list = eventService.getExchangeData();
        return list;
    }
}
