package com.crfinder.routefinder.service.finder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfinder.routefinder.dto.ConnectionbasedRoute;
import com.crfinder.routefinder.dto.SearchResultsDto;
import com.crfinder.routefinder.dto.TimebasedRoute;

@Service
public class GroupingRouteFinderServiceImpl implements RouteFinderService<SearchResultsDto> {

    @Autowired
    private RouteFinderService<List<TimebasedRoute>> timebasedFinder;
    
    @Autowired
    private RouteFinderService<List<ConnectionbasedRoute>> connectionbasedFinder;
    
    @Override
    public SearchResultsDto findRoutes(String originCity) {
        SearchResultsDto  dto = new SearchResultsDto();
        dto.setTimebasedItenenaries(timebasedFinder.findRoutes(originCity));
        dto.setConnetionbasedIteneraries(connectionbasedFinder.findRoutes(originCity));
        return dto;
    }

}
