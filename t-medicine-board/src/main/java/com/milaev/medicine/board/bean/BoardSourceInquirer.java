package com.milaev.medicine.board.bean;

import com.milaev.mq.data.ExchangeData;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class BoardSourceInquirer {

    private static Logger log =  LoggerFactory.getLogger(BoardSourceInquirer.class);

    private static final String BOARD_INFO_SOURCE = "http://localhost:8081/t_medicine_war/board/";

    private Client client;
    private WebTarget webTarget;
    private Invocation invocation;
    private Response response;
    private ObjectMapper mapper = new ObjectMapper();

    public List<ExchangeData> getResponse(){
        List<ExchangeData> list = getObjectByJSON(getJSONResponse());
        log.info("Received data list");
        return list;
    }

    public String getJSONResponse(){
        String jsonInString = "";

        try {
            response = invocation.invoke();
        } catch (ProcessingException e) {
            log.error("Unable to connect to board info source. (ProcessingException)");
            return jsonInString;
        }

        if (response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode()) {
            jsonInString = response.readEntity(String.class);
        } else {
            log.error("Wrong response from url, Status: {}", response.getStatusInfo().getStatusCode());
        }

        return jsonInString;
    }

    public List<ExchangeData> getObjectByJSON(String jsonInString){
        List<ExchangeData> list = new ArrayList<>();
        try {
            list = Arrays.asList(mapper.readValue(jsonInString, ExchangeData[].class));
        } catch (IOException e) {
            log.error("Unable to convert JSON string to Object", e);
        }
        return list;
    }

    @PostConstruct
    public void createClient(){
        createClient(BOARD_INFO_SOURCE);
    }

    public void createClient(String url){
        closeClient();
        client = ClientBuilder.newClient();
        webTarget = client.target(url);
        invocation = webTarget.request().buildGet();
    }

    @PreDestroy
    public void closeClient(){
        if (client != null)
            client.close();
    }

}
