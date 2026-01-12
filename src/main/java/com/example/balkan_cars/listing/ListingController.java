package com.example.balkan_cars.listing;

import com.example.balkan_cars.user.UserService;
import com.example.balkan_cars.vehicles.car.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/listings")
@CrossOrigin(origins = "http://localhost:4200")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService, UserService userService) {
        this.listingService = listingService;
    }

    @GetMapping("/user/{userId}")
    public List<CarDto> getByUser(@PathVariable UUID userId) {
        return listingService.findCarsBySeller(userId);
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
    public ListingDto create(@RequestBody ListingDto listingDto) {return listingService.create(listingDto);}

    @PutMapping
    public ListingDto update(@RequestBody ListingDto listingDto) {
        return listingService.update(listingDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        listingService.delete(UUID.fromString(id));
    }
}
