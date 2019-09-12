package com.milaev.medicine.board.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ApplicationScoped
@ServerEndpoint("/board")
public class BoardEndpoint {

    private static Logger log =  LoggerFactory.getLogger(BoardEndpoint.class);

    @Inject
    BoardEndpointPeers peers;

    public BoardEndpoint() {
        log.info("class loaded {}", this.getClass());
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("Session opened, id: {}", session.getId());
        peers.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Message received. Session id: {} Message: {}",
                session.getId(), message);
        try {
            session.getBasicRemote().sendText(String.format("We received your message: %s%n", message));
        } catch (IOException ex) {
            log.error("Error while sending message", ex);
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        log.info("Session closed with id: {}", session.getId());
        peers.remove(session);
    }
}
