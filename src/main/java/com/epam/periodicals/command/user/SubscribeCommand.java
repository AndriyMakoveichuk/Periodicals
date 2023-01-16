package com.epam.periodicals.command.user;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.dao.UserDao;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.Map;

public class SubscribeCommand implements Command {

    private final PublicationDao publicationDao = PublicationDao.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {
        System.out.println("Parsing Start");
        int publicationId = Integer.parseInt(requestContent.getRequestParameters().get("id")) ;
        System.out.println("Parsing Ended");
        User user = (User) requestContent.getSessionAttributes().get("user");
        Publication publication = publicationDao.get(publicationId);
        sessionAttributes = requestContent.getSessionAttributes();

        if (publication.getPrice() > user.getAccount()) {
            sessionAttributes.put("error", "Not enough money");
            Router router = new Router(true, "/periodicals?command=viewAllPublications", sessionAttributes);
            return router;
        }


        user.addPublication(publication);
        user.setAccount(user.getAccount() - publication.getPrice());
        userDao.update(user);

        sessionAttributes.replace("user", user);
        publicationDao.assignUserToPublication(user.getId(), publicationId);
        Router router = new Router(true,"/periodicals?command=viewAllPublications" ,sessionAttributes );

        return router;
    }
}
