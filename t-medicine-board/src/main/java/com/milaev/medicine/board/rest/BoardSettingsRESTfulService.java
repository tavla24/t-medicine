package com.milaev.medicine.board.rest;

import com.milaev.medicine.board.bean.settings.BoardSettings;
import com.milaev.medicine.board.bean.settings.BoardSettingsItem;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

@Path("/settings")
public class BoardSettingsRESTfulService {

    ObjectMapper mapper = new ObjectMapper();

    @Inject
    private BoardSettings settings;

    @Path("/")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public List<BoardSettingsItem> getSettings() throws IOException {
        //String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(settings.getItems());
        return settings.getItems();
    }

}
