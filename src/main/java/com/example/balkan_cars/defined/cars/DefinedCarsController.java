package com.example.balkan_cars.defined.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/definedCars")
public class DefinedCarsController {
    private final DefinedCarsService definedCarsService;

    @Autowired
    public DefinedCarsController(DefinedCarsService definedCarsService) {
        this.definedCarsService = definedCarsService;
    }

    @GetMapping("{id}")
    public DefinedCarsDto getCarById(@PathVariable String id) {
        return definedCarsService.findById(UUID.fromString(id));
    }

    @GetMapping
    public List<DefinedCarsDto> getAllCars() {
        return definedCarsService.findAll();
    }

    @PostMapping
    public DefinedCarsDto createCar(@RequestParam DefinedCarsDto definedCarsDto) {
        return definedCarsService.create(definedCarsDto);
    }

    @PutMapping
    public DefinedCarsDto updateCar(@RequestParam DefinedCarsDto definedCarsDto) {
        return definedCarsService.update(definedCarsDto);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable String id) {
        definedCarsService.delete(UUID.fromString(id));
    }
}
