package com.epam.periodicals.command;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RequestContent {
    private final Map<String, String> requestParameters = new HashMap<>();
    private final Map<String, Object> sessionAttributes = new HashMap<>();

    public RequestContent(HttpServletRequest request) {
        var parameterMap = request.getParameterMap();
        parameterMap.forEach((key, valuesArr) -> {
            String value = String.join(",", valuesArr);
            requestParameters.put(key, value);
        });

        Enumeration<String> attributes = request.getSession().getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attribute = attributes.nextElement();
            sessionAttributes.put(attribute, request.getSession().getAttribute(attribute));
        }
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

}
