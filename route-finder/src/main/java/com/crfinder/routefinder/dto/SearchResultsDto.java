package com.crfinder.routefinder.dto;

import java.util.List;

public class SearchResultsDto {

    private List<TimebasedRoute> timebasedItenenaries;
    
    private List<ConnectionbasedRoute> connetionbasedIteneraries;

    public List<TimebasedRoute> getTimebasedItenenaries() {
        return timebasedItenenaries;
    }

    public void setTimebasedItenenaries(List<TimebasedRoute> timebasedItenenaries) {
        this.timebasedItenenaries = timebasedItenenaries;
    }

    public List<ConnectionbasedRoute> getConnetionbasedIteneraries() {
        return connetionbasedIteneraries;
    }

    public void setConnetionbasedIteneraries(List<ConnectionbasedRoute> connetionbasedIteneraries) {
        this.connetionbasedIteneraries = connetionbasedIteneraries;
    }
    
}
