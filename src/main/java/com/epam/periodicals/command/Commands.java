package com.epam.periodicals.command;

import com.epam.periodicals.command.admin.*;
import com.epam.periodicals.command.common.ChangeLangCommand;
import com.epam.periodicals.command.common.LogOutCommand;
import com.epam.periodicals.command.common.LoginCommand;
import com.epam.periodicals.command.user.*;

import java.util.HashMap;
import java.util.Map;

public enum Commands {

    REGISTRATION("registration", new RegistrationCommand()),
    LOGIN("login", new LoginCommand()),
    LOGOUT("logout", new LogOutCommand()),
    ALL_PUBLICATIONS("viewAllPublications", new AllPublicationsCommand()),
    SUBSCRIBE("subscribe", new SubscribeCommand()),
    ADD_BALANCE("addBalance", new AddBalanceCommand()),
    ALL_SUBSCRIBED("viewSubscribed", new ViewSubscribedCommand()),
    UNSUBSCRIBE("unsubscribe", new UnsubscribeCommand()),
    MANAGE_PUBLICATIONS("managePublications", new ManagePublicationsCommand()),
    EDIT_PUBLICATION("editPublication", new EditPublicationCommand()),
    CHANGE_PUBLICATION("changePublication", new ChangePublicationCommand()),
    DELETE_PUBLICATION("deletePublication", new DeletePublicationCommand()),
    MANAGE_TOPICS("manageTopics", new ManageTopicsCommand()),
    EDIT_TOPIC("editTopic", new EditTopicCommand()),
    CHANGE_TOPIC("changeTopic", new ChangeTopicCommand()),
    DELETE_TOPIC("deleteTopic", new DeleteTopicCommand()),
    MANAGE_USERS("manageUsers", new ManageUsersCommand()),
    BLOCK_USER("blockUser", new BlockUsersCommand()),
    DELETE_USER("deleteUser", new DeleteUserCommand()),
    CHANGE_LANG("changeLang", new ChangeLangCommand());


    private final  String commandName;
    private final Command command;
    public static final Map<String, Command> NAME_COMMAND_MAP = new HashMap<>();

    static {
        for(var command: values()) {
            NAME_COMMAND_MAP.put(command.commandName, command.command);
        }
    }

    Commands(String commandName, Command command) {
        this.commandName = commandName;
        this.command = command;
    }


}
