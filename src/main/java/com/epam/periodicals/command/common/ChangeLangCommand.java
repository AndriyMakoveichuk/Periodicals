package com.epam.periodicals.command.common;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;

import java.util.HashMap;
import java.util.Map;

public class ChangeLangCommand implements Command {

    Router router;
    private final Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {
        String language = requestContent.getRequestParameters().get("lang");
        String pageName = requestContent.getRequestParameters().get("pageName");
        sessionAttributes.put("lang", language);
        router = new Router(true,pageName,sessionAttributes);

        return router;
    }


}
