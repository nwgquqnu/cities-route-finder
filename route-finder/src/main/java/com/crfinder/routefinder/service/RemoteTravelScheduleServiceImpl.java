package com.crfinder.routefinder.service;

import static org.springframework.http.HttpMethod.GET;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import com.crfinder.routefinder.dto.TravelScheduleDto;
import com.google.common.collect.ImmutableMap;

@Service
public class RemoteTravelScheduleServiceImpl implements TravelScheduleService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cities-storage.get-routes-from-city}")
    private String getRoutesFromCityUrl;

    @Override
    public List<TravelScheduleDto> getRoutesFromCity(String city) {
        
        ResponseEntity<List<TravelScheduleDto>> responseEntity = restTemplate.exchange(getRoutesFromCityUrl + encodeUriPath(city), GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<TravelScheduleDto>>() {}, ImmutableMap.of("city", city));
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return Collections.emptyList();
        }
        return responseEntity.getBody();
    }
    
    private String encodeUriPath(String path) {
        try {
            return UriUtils.encodePath(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unexpected exception. Java does not support UTF-8 even though it should always support it by specification", e);
        }
    }

}
