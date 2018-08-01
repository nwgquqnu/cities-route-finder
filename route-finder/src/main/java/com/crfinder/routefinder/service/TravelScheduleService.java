package com.crfinder.routefinder.service;

import java.util.List;

import com.crfinder.routefinder.dto.TravelScheduleDto;

public interface TravelScheduleService {

    List<TravelScheduleDto> getRoutesFromCity(String city);
}
