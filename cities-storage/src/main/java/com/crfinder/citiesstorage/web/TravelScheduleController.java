package com.crfinder.citiesstorage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crfinder.citiesstorage.entity.TravelSchedule;
import com.crfinder.citiesstorage.repository.TravelScheduleRepository;

@RestController
@RequestMapping("/api/travel-schedule")
public class TravelScheduleController {

    @Autowired
    private TravelScheduleRepository repository;
    
    @GetMapping("")
    public Iterable<TravelSchedule> findAll() {
        return repository.findAll();
    }
    
    @PostMapping(path = "", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void save(@RequestBody TravelSchedule travelSchedule) {
        repository.save(travelSchedule);
    }
    
    @PostMapping(path = "list", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void save(@RequestBody List<TravelSchedule> schedules) {
        repository.save(schedules);
    }
    
    @GetMapping("find-by-city/{city}")
    public Iterable<TravelSchedule> findByCity(@PathVariable String city) {
        return repository.findByCity(city);
    }
    
    @DeleteMapping("{id}")
    public void delteSchedule(@PathVariable Integer id) {
        repository.delete(id);
    }
}
