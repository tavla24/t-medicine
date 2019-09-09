package com.milaev.medicine.board.rest;

import com.milaev.medicine.board.bean.BoardUpdater;
import com.milaev.medicine.board.bean.settings.BoardSettings;
import com.milaev.medicine.board.bean.settings.BoardSettingsItem;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/settings")
public class BoardSettingsRESTfulService {

    private static Logger log =  LoggerFactory.getLogger(BoardSettingsRESTfulService.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Inject
    private BoardSettings settings;

    @Inject
    BoardUpdater boardUpdater;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSettings() throws IOException {
        //String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(settings.getItems());
        //return settings.getItems();

        return Response.ok() //200
                .entity(settings.getItems())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putSettings(String str) throws IOException {
        log.info("===============================> putSettings");

        try {
            List<BoardSettingsItem> list = Arrays.asList(mapper.readValue(str, BoardSettingsItem[].class));
            settings.fillSettings(list);
            boardUpdater.setNewState(true, "update by settings change");
        } catch (IOException e) {
            log.error("Unable to convert JSON string to Object", e);
        }

        return getSettings();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postSettings() throws IOException {
        log.info("===============================> postSettings");
        return getSettings();
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postSettings(List<BoardSettingsItem> list) throws IOException {
//        log.info("===============================> postSettings");
//        return getSettings();
//    }

}
