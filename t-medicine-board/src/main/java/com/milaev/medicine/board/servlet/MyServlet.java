package com.milaev.medicine.board.servlet;

import com.milaev.medicine.board.bean.Inquirer;
import com.milaev.medicine.board.ejb.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendMessage")
public class MyServlet extends HttpServlet {

    private static Logger log =  LoggerFactory.getLogger(MyServlet.class);

    @Inject
    private MessageSender sender;

    @Inject
    Inquirer inquirer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        //sender.sendMessage("hello from board");
        inquirer.getResponse();
    }
}
