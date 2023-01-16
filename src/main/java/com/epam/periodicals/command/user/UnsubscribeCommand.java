package com.epam.periodicals.command.user;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.User;

import java.util.List;
import java.util.Map;

public class UnsubscribeCommand implements Command {

    private final PublicationDao publicationDao = PublicationDao.getInstance();


    @Override
    public Router execute(RequestContent requestContent) {
        Map<String, Object> sessionAttributes = requestContent.getSessionAttributes();
        Map<String, String> requestParameters = requestContent.getRequestParameters();
        User user = (User) sessionAttributes.get("user");
        int publicationId = Integer.parseInt(requestParameters.get("id"));
        publicationDao.deleteUserPublication(user.getId(),publicationId);
        user.deletePublication(publicationId);
        sessionAttributes.replace("user", user);


        System.out.println("UNSUBSCRIBE:");
        for (Publication p: user.getPublications()) {
            System.out.println(p.getTitleEn());
            System.out.println(user.isSubscribed(p.getId()));
        }


        Router router = new Router(true, "/periodicals?command=viewSubscribed", sessionAttributes);

        return router;
    }
}
