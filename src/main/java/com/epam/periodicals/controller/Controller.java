package com.epam.periodicals.controller;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.Commands;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.command.user.RegistrationCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/periodicals")
public class Controller extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestContent requestContent = new RequestContent(req);
        String commandName = req.getParameter("command");
        Command command = Commands.NAME_COMMAND_MAP.get(commandName);
        Router router = command.execute(requestContent);
        HttpSession session = req.getSession();

        if (router.getSessionAttributes() == null) {
            session.invalidate();
        } else {
            router.getSessionAttributes().forEach(session::setAttribute);
        }


        if (router.isToRedirect()) {

            resp.sendRedirect(req.getContextPath() + router.getAddress());
        } else {
            router.getNewRequestAttributes().forEach(req::setAttribute);
            req.getRequestDispatcher(router.getAddress()).forward(req, resp);
        }
    }


}
