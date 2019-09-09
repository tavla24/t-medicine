package com.milaev.medicine.board.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getjson")
public class jsonReply extends HttpServlet {

    private static Logger log =  LoggerFactory.getLogger(jsonReply.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("doGet");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = resp.getWriter();
        writer.print("[{\"id\":0,\"title\":\"Show id\",\"show\":false},{\"id\":1,\"title\":\"Show healing type\",\"show\":true}]");
    }


}
