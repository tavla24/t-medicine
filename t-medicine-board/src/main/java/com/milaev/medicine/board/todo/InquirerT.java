package com.milaev.medicine.board.todo;

import com.milaev.medicine.board.bean.BoardSourceInquirer;
import com.milaev.mq.message.StateChangedEvent;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class InquirerT <T> { // TODO is it possible?

    private static Logger log =  LoggerFactory.getLogger(BoardSourceInquirer.class);

    private static final String BOARD_INFO_SOURCE = "http://localhost:8081/t_medicine_war/board/";

    public void getResponse(){
        T event = getObjectByJSON(getJSONResponse(), StateChangedEvent.class);
        log.info(event.toString());
    }

    public String getJSONResponse(){
        return getJSONResponse(BOARD_INFO_SOURCE);
    }

    public String getJSONResponse(String url){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BOARD_INFO_SOURCE);
        Invocation invocation = webTarget.request().buildGet();
        Response response = invocation.invoke();
        String jsonInString = "";
        if (response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode()) {
            jsonInString = response.readEntity(String.class);
        }
        client.close();

        return jsonInString;
    }

    public T getObjectByJSON(String jsonInString, Class clazz){
        ObjectMapper mapper = new ObjectMapper();
//        T event = (T) clazz.newInstance();
        T event = null;

        try {
            event = (T) mapper.readValue(jsonInString, clazz);
        } catch (IOException e) {
            log.error("Unable convrt JSON string to Object", e);
        }
        return event;
    }


}
