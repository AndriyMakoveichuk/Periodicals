package com.epam.periodicals.command.common;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;

import java.util.HashMap;
import java.util.Map;

public class LogOutCommand implements Command {

    private Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {

        Router router = new Router(true, "/login", null);
        return router;
    }
}
