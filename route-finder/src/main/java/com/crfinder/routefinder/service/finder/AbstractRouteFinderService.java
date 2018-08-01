package com.crfinder.routefinder.service.finder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.crfinder.routefinder.dto.TravelScheduleDto;
import com.crfinder.routefinder.service.CitiesStorageClient;
import com.crfinder.routefinder.service.search.DijkstrasSearch;
import com.crfinder.routefinder.service.search.SearchNode;

public abstract class AbstractRouteFinderService<T> implements RouteFinderService<List<T>> {

    @Autowired
    private CitiesStorageClient storageClient;

    @Override
    public List<T> findRoutes(String originCity) {
        DijkstrasSearch<String> searchService = new DijkstrasSearch<String>(this::findNeighbouringNodes);
        Map<String, SearchNode<String>> citiesMap = searchService.performSearch(originCity);
        return generateSortedRoutesFromCitiesMap(citiesMap);
    }

    protected abstract SearchNode<String> convertToSearchNode(TravelScheduleDto routeFromCity);
    protected abstract T convertToTimebasedRoute(SearchNode<String> nodeToConvert);

    private List<SearchNode<String>> findNeighbouringNodes(SearchNode<String> node) {
        List<TravelScheduleDto> routesFromCity = storageClient.getRoutesFromCity(node.getData());
        return convertToSearchNodes(routesFromCity);
    }

    private List<SearchNode<String>> convertToSearchNodes(List<TravelScheduleDto> routesFromCity) {
        return routesFromCity.stream()
                .map(this::convertToSearchNode)
                .collect(Collectors.toList());
    }


    private List<T> generateSortedRoutesFromCitiesMap(Map<String, SearchNode<String>> citiesMap) {
        return citiesMap.values()
                .stream()
                .map(this::convertToSearchNodeWithConcatenatedRoute)
                .filter(node -> node.getDistance() > 0)
                .sorted((a, b) -> Long.compare(a.getDistance(), b.getDistance()))
                .map(this::convertToTimebasedRoute)
                .collect(Collectors.toList());
    }

    private SearchNode<String> convertToSearchNodeWithConcatenatedRoute(SearchNode<String> nodeToConvert) {
        Deque<String> cities = new LinkedList<>();
        SearchNode<String> lastNode = nodeToConvert;
        while (lastNode != null) {
            cities.addFirst(lastNode.getData());
            lastNode = lastNode.getParent();
        }

        return new SearchNode<>(cities.stream()
                .collect(Collectors.joining(", ")), nodeToConvert.getDistance());
    }

}
