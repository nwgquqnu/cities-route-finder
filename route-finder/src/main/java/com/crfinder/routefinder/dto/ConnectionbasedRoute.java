package com.crfinder.routefinder.dto;

public class ConnectionbasedRoute {

    private String route;
    private long routeConnections;

    public ConnectionbasedRoute(String route, long routeConnections) {
        this.route = route;
        this.routeConnections = routeConnections;
    }

    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public long getRouteConnections() {
        return routeConnections;
    }
    public void setRouteConnections(long routeConnections) {
        this.routeConnections = routeConnections;
    }
}
