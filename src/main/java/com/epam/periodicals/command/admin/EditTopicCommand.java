package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;

import java.util.HashMap;
import java.util.Map;

public class EditTopicCommand implements Command {

    private Map<String, Object> sessionAttributes = new HashMap<>();


    @Override
    public Router execute(RequestContent requestContent) {
        String id = requestContent.getRequestParameters().get("id");
        sessionAttributes.put("id", id);

        return new Router(true,"/periodicals?command=manageTopics",sessionAttributes);
    }
}
