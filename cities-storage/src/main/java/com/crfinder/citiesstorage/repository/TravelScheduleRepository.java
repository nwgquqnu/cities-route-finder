package com.crfinder.citiesstorage.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crfinder.citiesstorage.entity.TravelSchedule;

public interface TravelScheduleRepository extends CrudRepository<TravelSchedule, Integer>{
    List<TravelSchedule> findByCity(String city);
}
