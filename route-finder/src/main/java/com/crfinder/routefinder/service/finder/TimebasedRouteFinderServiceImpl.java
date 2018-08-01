package com.crfinder.routefinder.service.finder;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.crfinder.routefinder.dto.TimebasedRoute;
import com.crfinder.routefinder.dto.TravelScheduleDto;
import com.crfinder.routefinder.service.search.SearchNode;

@Service
public class TimebasedRouteFinderServiceImpl extends AbstractRouteFinderService<TimebasedRoute> {

    @Override
    protected TimebasedRoute convertToTimebasedRoute(SearchNode<String> nodeToConvert) {
        return new TimebasedRoute(nodeToConvert.getData(), new Date(nodeToConvert.getDistance()));
    }

    @Override
    protected SearchNode<String> convertToSearchNode(TravelScheduleDto routeFromCity) {
        Date arrivalTime = routeFromCity.getArrivalTime();
        Date departureTime = routeFromCity.getDepartureTime();
        long arrivalInMilliseconds = arrivalTime != null ? arrivalTime.getTime() : 0L;
        long departureInMilliseconds = departureTime != null ? departureTime.getTime() : 0L;
        return new SearchNode<>(routeFromCity.getDestinationCity(), arrivalInMilliseconds - departureInMilliseconds);
    }

}
