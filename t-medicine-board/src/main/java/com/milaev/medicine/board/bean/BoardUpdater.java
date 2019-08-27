package com.milaev.medicine.board.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.inject.Inject;

@Startup
@Singleton
public class BoardUpdater {

    private static Logger log =  LoggerFactory.getLogger(BoardUpdater.class);

    @Inject
    BoardSourceInquirer inquirer;

    private boolean newData = true;

    @Schedule(second="*", minute="*", hour="*", persistent = false)
    public void doAction(){
        if (newData){
            log.info("New data present, update needed");
            String jsonInString = inquirer.getJSONResponse();
            if (!jsonInString.isEmpty()) {
                setNewData(false);
            }
        }
    }

    @Lock(LockType.WRITE)
    public void setNewData(boolean newData) {
        this.newData = newData;
    }
}
