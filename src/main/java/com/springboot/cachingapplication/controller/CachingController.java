package com.springboot.cachingapplication.controller;

import com.springboot.cachingapplication.domain.Technology;
import com.springboot.cachingapplication.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/tech")
public class CachingController {

    @Autowired
    public TechnologyService technologyService;

    @GetMapping("/test")
    public String testCachingController() {
        return "Hello! Caching controller hits successfully!";
    }

    //    Data in the cache is stored in a key-value pattern. The key is the name stored in the cacheName variable while the
    //    value is the data that is returned by the method annotated with @Cacheable annotation.

    @Cacheable(cacheNames = "technologies")
    @GetMapping("/all")
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        return ResponseEntity.ok(technologyService.getAllTechnologies());
    }

    //  we pass the id as the key, since the cache stores data in the form of key-value pair. When we pass the id of the
    //  technology, we are expecting a technology data associated with that key.

    @Cacheable(cacheNames = "technology", key = "#id")
    @GetMapping("/get-technology")
    public ResponseEntity<Technology> getAllTechnologies(@RequestParam UUID id) {
        return ResponseEntity.ok(technologyService.getTechnologyById(id));
    }

    @GetMapping("/all-tech")
    public ResponseEntity<List<Technology>> getTechnologies() {
        return ResponseEntity.ok(technologyService.getAllTechnologies());
    }

    @PostMapping("/add-technology")
    public ResponseEntity<String> addTechnology(@RequestBody Technology technology) {
        if (technologyService.addTechnology(technology)) {
            return new ResponseEntity<>("Technology successfully added!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Technology successfully added!", HttpStatus.BAD_REQUEST);
    }


    @CachePut(value = "technologies", key = "#p0")
    @PutMapping("/edit-technology")
    public ResponseEntity<Technology> updateTechnology(@RequestBody Technology technology, @RequestParam UUID id) {
        Technology technology1 = technologyService.updateTechnology(technology, id);
        if (null != technology1) {
            return new ResponseEntity<>(technology1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(technology, HttpStatus.BAD_REQUEST);
    }

    //    To delete all items from the cache, we set the attribute allEntries to true.
    @CacheEvict(value = "technologies", key = "#id")
    @DeleteMapping("/delete-technology")
    public ResponseEntity<Void> deleteTechnology(@RequestParam UUID id) {
        technologyService.deleteTechnology(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
