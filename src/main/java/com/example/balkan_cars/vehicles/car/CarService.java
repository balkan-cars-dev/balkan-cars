package com.example.balkan_cars.vehicles.car;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private static final String CAR_NOT_FOUND = "Car not found";

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<CarDto> findAll() {
        return carMapper.toDtos(carRepository.findAll());
    }

    public CarDto findById(UUID id) {
        return carMapper.toDto(
                carRepository.findByBusinessId(id)
                        .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND))
        );
    }

    @Transactional
    public CarDto create(CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        Car saved = carRepository.save(car);

        return carMapper.toDto(saved);
    }

    @Transactional
    public CarDto update(CarDto carDto) {
        Car car = carRepository.getByBusinessId(carDto.id());
        if (car == null) throw new EntityNotFoundException(CAR_NOT_FOUND);

        carMapper.updateCarFromDto(carDto, car);

        return carMapper.toDto(carRepository.saveAndFlush(car));
    }

    @Transactional
    public void delete(UUID id) {
        Car car = carRepository.getByBusinessId(id);
        if (car != null) {
            carRepository.delete(car);
        } else {
            throw new EntityNotFoundException(CAR_NOT_FOUND);
        }
    }
}