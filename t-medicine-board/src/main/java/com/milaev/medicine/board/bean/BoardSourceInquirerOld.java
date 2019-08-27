package com.milaev.medicine.board.bean;

import com.milaev.mq.message.StateChangedEvent;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class BoardSourceInquirerOld {

    private static Logger log =  LoggerFactory.getLogger(BoardSourceInquirerOld.class);

    private static final String BOARD_INFO_SOURCE = "http://localhost:8081/t_medicine_war/board/";

    public StateChangedEvent getResponse(){
        StateChangedEvent event = getObjectByJSON(getJSONResponse());
        log.info(event.getText());
        return event;
    }

    public String getJSONResponse(){
        return getJSONResponse(BOARD_INFO_SOURCE);
    }

    public String getJSONResponse(String url){
        String jsonInString = "";
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BOARD_INFO_SOURCE);
        Invocation invocation = webTarget.request().buildGet();
        Response response;
        try {
            response = invocation.invoke();
        } catch (ProcessingException e) {
            log.error("Unable to connect to board info source", e);
            return jsonInString;
        }

        if (response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode()) {
            jsonInString = response.readEntity(String.class);
        }
        client.close();

        return jsonInString;
    }

    public StateChangedEvent getObjectByJSON(String jsonInString){
        ObjectMapper mapper = new ObjectMapper();
        StateChangedEvent event = null;

        try {
            event = mapper.readValue(jsonInString, StateChangedEvent.class);
        } catch (IOException e) {
            log.error("Unable to convert JSON string to Object", e);
        }
        return event;
    }

}
