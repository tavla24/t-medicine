package com.milaev.medicine.board.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class RespFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext reqContext, ContainerResponseContext respContext) throws IOException {
        respContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
    }
}