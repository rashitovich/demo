package com.example.demo.model.event;

import java.util.ArrayList;
import java.util.List;

public class Event {

    public static final String SKP_EVENT_TYPE = "viewСertificate";
    public static final String SKP_EVENT_SUBTYPE = "boxSale";

    private String eventType;
    private String eventSubType;
    private List<EventParameter> parameters = new ArrayList<>();

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public void setEventSubType(String eventSubType) {
        this.eventSubType = eventSubType;
    }

    public List<EventParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<EventParameter> parameters) {
        this.parameters = parameters;
    }
}
