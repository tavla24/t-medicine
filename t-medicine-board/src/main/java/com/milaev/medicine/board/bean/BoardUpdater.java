package com.milaev.medicine.board.bean;

import com.milaev.medicine.board.ws.BoardEndpointPeers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.inject.Inject;

@Startup
@Singleton
public class BoardUpdater {

    private static Logger log = LoggerFactory.getLogger(BoardUpdater.class);

    @Inject
    BoardSourceInquirer inquirer;

    @Inject
    BoardDataSource boardDataSource;

    @Inject
    BoardEndpointPeers peers;

    private boolean newData = true;
    private String msg = "";

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void doAction() {
        if (newData) {
            log.info("New data present, update needed");
            String jsonInString = inquirer.getJSONResponse();

            if (!jsonInString.isEmpty()) {
                log.info("jsonInString not empty");
                boardDataSource.setDatasource(inquirer.getResponse());
                log.info("setDatasource");
                peers.sendMessage(msg);
                log.info(" peers.sendMessage(msg), size {}", peers.getPeersCount());
                setNewState(false, "");
                log.info("setNewState");
            }
        }
    }

    @Lock(LockType.WRITE)
    public void setNewState(boolean newData, String msg) {
        this.newData = newData;
        this.msg = msg;
    }
}
