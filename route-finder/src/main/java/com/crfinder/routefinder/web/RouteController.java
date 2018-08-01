package com.crfinder.routefinder.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crfinder.routefinder.dto.SearchResultsDto;
import com.crfinder.routefinder.service.finder.RouteFinderService;

@RestController
@RequestMapping("/api")
public class RouteController {
    
    @Autowired
    private RouteFinderService<SearchResultsDto> routeFinderService;
    
    @GetMapping("find-routes")
    public SearchResultsDto findRoutes(String originCity) {
        return routeFinderService.findRoutes(originCity);
    }

}
