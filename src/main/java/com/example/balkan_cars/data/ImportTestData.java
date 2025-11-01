package com.example.balkan_cars.data;

import com.example.balkan_cars.enums.FuelType;
import com.example.balkan_cars.enums.TransmissionType;
import com.example.balkan_cars.vehicles.car.Car;
import com.example.balkan_cars.vehicles.car.CarRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportTestData {
    
    @Value("${app.db.init:false}")
    private boolean enableImport;
    private CarRepository carRepository;

    @Autowired
    public ImportTestData(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        if (enableImport) {
            importCars();
        }
    }

    private void importCars() {
        Car car = new Car("WBA2220104969", "BMW", "340i", 2017,
                150000, 350, "white", FuelType.PETROL, TransmissionType.AUTOMATIC);
        Car car2 = new Car("WBA222016563", "VW", "golf", 2009,
                210050, 150, "black", FuelType.DIESEL, TransmissionType.MANUAL);
        Car car3 = new Car("WBA267743365", "FORD", "focus", 2001,
                400000, 115, "gray", FuelType.DIESEL, TransmissionType.MANUAL);
        Car car4 = new Car("WBA254333335", "PORSCHE", "911", 2020,
                60000, 300, "yellow", FuelType.PETROL, TransmissionType.AUTOMATIC);
        carRepository.saveAll(List.of(car, car2, car3, car4));
    }
}
