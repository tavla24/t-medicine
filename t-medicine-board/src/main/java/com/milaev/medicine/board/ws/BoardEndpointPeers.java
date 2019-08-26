package com.milaev.medicine.board.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Startup
@Singleton
public class BoardEndpointPeers {

    private static Logger log =  LoggerFactory.getLogger(BoardEndpointPeers.class);

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public void add(Session session) {
        log.info("Add session into peers collection, session id: {}", session.getId());
        sessions.add(session);
    }

    public void remove(Session session) {
        log.info("Removed session from peers collection, session id: {}", session.getId());
        sessions.remove(session);
    }

    public void sendMessage(String message){
        for(Session session: sessions) {
            try {
                session.getBasicRemote().sendText(String.format("Message from BoardEndpointPeers: %s%n", message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
