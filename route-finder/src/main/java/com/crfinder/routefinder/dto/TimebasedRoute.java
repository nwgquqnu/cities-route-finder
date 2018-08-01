package com.crfinder.routefinder.dto;

import java.util.Date;

public class TimebasedRoute {
    private String route;
    private Date totalTime;

    public TimebasedRoute() {
    }

    public TimebasedRoute(String route, Date totalTime) {
        super();
        this.route = route;
        this.totalTime = totalTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Date totalTime) {
        this.totalTime = totalTime;
    }

}
