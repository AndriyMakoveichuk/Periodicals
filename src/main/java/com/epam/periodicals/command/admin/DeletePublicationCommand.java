package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;

import java.util.HashMap;
import java.util.Map;

public class DeletePublicationCommand implements Command {

    private final Map<String, Object> sessionAttributes = new HashMap<>();
    PublicationDao publicationDao = PublicationDao.getInstance();

    @Override
    public Router execute(RequestContent requestContent) {
        int id = Integer.parseInt(requestContent.getRequestParameters().get("id"));
        publicationDao.delete(id);
        return new Router(true,"/periodicals?command=managePublications",sessionAttributes);

    }
}
