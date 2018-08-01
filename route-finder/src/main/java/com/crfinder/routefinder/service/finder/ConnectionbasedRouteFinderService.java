package com.crfinder.routefinder.service.finder;

import org.springframework.stereotype.Service;

import com.crfinder.routefinder.dto.ConnectionbasedRoute;
import com.crfinder.routefinder.dto.TravelScheduleDto;
import com.crfinder.routefinder.service.search.SearchNode;

@Service
public class ConnectionbasedRouteFinderService extends AbstractRouteFinderService<ConnectionbasedRoute> {

    @Override
    protected SearchNode<String> convertToSearchNode(TravelScheduleDto routeFromCity) {
        return new SearchNode<>(routeFromCity.getDestinationCity(), 1L);
    }

    @Override
    protected ConnectionbasedRoute convertToTimebasedRoute(SearchNode<String> nodeToConvert) {
        return new ConnectionbasedRoute(nodeToConvert.getData(), nodeToConvert.getDistance());
    }

}
