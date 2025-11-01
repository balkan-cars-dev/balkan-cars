package com.example.balkan_cars.listing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("{id}")
    public ListingDto getById(@PathVariable String id) {
        return listingService.findById(UUID.fromString(id));
    }

    @GetMapping
    public List<ListingDto> getAll() {
        return listingService.findAll();
    }

    @PostMapping
    public ListingDto create(@RequestParam ListingDto listingDto) {
        return listingService.create(listingDto);
    }

    @PutMapping
    public ListingDto update(@RequestParam ListingDto listingDto) {
        return listingService.update(listingDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        listingService.delete(UUID.fromString(id));
    }
}
