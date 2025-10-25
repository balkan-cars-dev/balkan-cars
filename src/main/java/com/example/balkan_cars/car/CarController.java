package com.example.balkan_cars.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("{id}")
    public CarDto getCarById(@PathVariable String id) {
        return carService.findById(UUID.fromString(id));
    }

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.findAll();
    }

    @PostMapping
    public CarDto createCar(@RequestParam CarDto carDto) {
        return carService.create(carDto);
    }

    @PutMapping
    public CarDto updateCar(@RequestParam CarDto carDto) {
        return carService.update(carDto);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable String id) {
        carService.delete(UUID.fromString(id));
    }
}
