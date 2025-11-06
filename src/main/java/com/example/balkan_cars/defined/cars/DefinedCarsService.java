package com.example.balkan_cars.defined.cars;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefinedCarsService {

    private static final String CAR_NOT_FOUND = "Car not found";

    private final DefinedCarsRepository definedCarsRepository;
    private final DefinedCarsMapper definedCarsMapper;
    
    public DefinedCarsService(DefinedCarsRepository carRepository, DefinedCarsMapper carMapper) {
        this.definedCarsRepository = carRepository;
        this.definedCarsMapper = carMapper;
    }

    public List<DefinedCarsDto> findAll() {
        return definedCarsMapper.toDtos(definedCarsRepository.findAll());
    }

    public DefinedCarsDto findById(UUID id) {
        return definedCarsMapper.toDto(
                definedCarsRepository.findByBusinessId(id)
                        .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND))
        );
    }

    @Transactional
    public DefinedCarsDto create(DefinedCarsDto definedCarsDto) {
        DefinedCars car = definedCarsMapper.toEntity(definedCarsDto);
        DefinedCars saved = definedCarsRepository.save(car);

        return definedCarsMapper.toDto(saved);
    }

    @Transactional
    public DefinedCarsDto update(DefinedCarsDto carDto) {
        DefinedCars definedCars = definedCarsRepository.getByBusinessId(carDto.id());
        if (definedCars == null) throw new EntityNotFoundException(CAR_NOT_FOUND);

        definedCarsMapper.updateEntityFromDto(carDto, definedCars);

        return definedCarsMapper.toDto(definedCarsRepository.saveAndFlush(definedCars));
    }

    @Transactional
    public void delete(UUID id) {
        DefinedCars car = definedCarsRepository.getByBusinessId(id);
        if (car != null) {
            definedCarsRepository.delete(car);
        } else {
            throw new EntityNotFoundException(CAR_NOT_FOUND);
        }
    }
}
