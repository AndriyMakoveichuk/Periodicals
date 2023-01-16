package com.epam.periodicals.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Router {
    private final boolean toRedirect;
    private final String address;
    private final Map<String, Object> sessionAttributes;
    private final Map<String, Object> newRequestAttributes;


    public Router(boolean toRedirect, String address) {
        this.toRedirect = toRedirect;
        this.address = address;
        this.sessionAttributes = null;
        this.newRequestAttributes = null;
    }

    public Router(boolean toRedirect, String address, Map<String, Object> sessionAttributes) {
        this.toRedirect = toRedirect;
        this.address = address;
        this.sessionAttributes = sessionAttributes;
        this.newRequestAttributes = null;
    }

    public Router(boolean toRedirect, String address, Map<String, Object> sessionAttributes, Map<String, Object> newRequestAttributes) {
        this.toRedirect = toRedirect;
        this.address = address;
        this.sessionAttributes = sessionAttributes;
        this.newRequestAttributes = newRequestAttributes;
    }

    public boolean isToRedirect() {
        return toRedirect;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public Map<String, Object> getNewRequestAttributes() {
        return newRequestAttributes;
    }
}
