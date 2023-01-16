package com.epam.periodicals.command.user;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSubscribedCommand implements Command {

    private final PublicationDao publicationDao = PublicationDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private final Map<String, Object> newRequestAttributes = new HashMap<>();
    private List<Publication> publicationsList;




    @Override
    public Router execute(RequestContent requestContent) {
        sessionAttributes = requestContent.getSessionAttributes();
        User user = (User) requestContent.getSessionAttributes().get("user");
        publicationsList = publicationDao.getPublicationsOfUser(user.getId());
        newRequestAttributes.put("publicationsList", publicationsList);

        System.out.println("VIE:");
        for (Publication p: user.getPublications()) {
            System.out.println(p.getTitleEn());
            System.out.println(user.isSubscribed(p.getId()));
        }

        return new Router(false, "/subscribed", sessionAttributes, newRequestAttributes);
    }
}
