package com.springboot.cachingapplication.service;

import com.springboot.cachingapplication.domain.Technology;
import com.springboot.cachingapplication.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TechnologyService {

    @Autowired
    public TechnologyRepository technologyRepository;

    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    public Technology getTechnologyById(UUID id) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        return optionalTechnology.orElseThrow(() -> null);
    }

    public boolean addTechnology(Technology technology) {
        if (null != technology) {
            technology.setCreatedDate(new Date());
            technologyRepository.save(technology);
            return true;
        }
        return false;
    }

    public Technology updateTechnology(Technology technology, UUID id) {
        Technology updatedTechnology;
        Optional<Technology> existingTechnology = technologyRepository.findById(id);

        if (existingTechnology.isPresent()) {
            updatedTechnology = existingTechnology.get();

            if (!updatedTechnology.getTechnologyName().equals(technology.getTechnologyName())) {
                updatedTechnology.setTechnologyName(technology.getTechnologyName());
            }
            if (!updatedTechnology.getDescription().equals(technology.getDescription())) {
                updatedTechnology.setDescription(technology.getDescription());
            }
            if (!updatedTechnology.getRating().equals(technology.getRating())) {
                updatedTechnology.setRating(technology.getRating());
            }
            updatedTechnology.setLastUpdatedDate(new Date());
            technologyRepository.save(updatedTechnology);
            return updatedTechnology;
        }
        return technology;
    }

    public void deleteTechnology(UUID id) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        optionalTechnology.ifPresent(technology -> technologyRepository.delete(technology));
    }
}
