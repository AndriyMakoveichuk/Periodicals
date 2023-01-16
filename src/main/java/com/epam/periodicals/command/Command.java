package com.epam.periodicals.command;

import com.epam.periodicals.controller.Router;

public interface Command {

    Router execute(RequestContent requestContent);

}
