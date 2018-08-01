package com.crfinder.routefinder.service;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crfinder.routefinder.dto.TravelScheduleDto;

@FeignClient("${cities-storage.feign.name}")
public interface CitiesStorageClient {

    @RequestMapping(method = GET, value = "/api/travel-schedule/find-by-city/{city}")
    List<TravelScheduleDto> getRoutesFromCity(@PathVariable("city") String city);
}
