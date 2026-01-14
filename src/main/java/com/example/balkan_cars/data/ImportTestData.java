package com.example.balkan_cars.data;

import com.example.balkan_cars.defined.cars.DefinedCars;
import com.example.balkan_cars.enums.FuelType;
import com.example.balkan_cars.enums.State;
import com.example.balkan_cars.enums.SubCategory;
import com.example.balkan_cars.enums.TransmissionType;
import com.example.balkan_cars.parts.Part;
import com.example.balkan_cars.parts.PartRepository;
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
    private PartRepository partRepository;

    @Autowired
    public ImportTestData(CarRepository carRepository,
                          PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        if (enableImport) {
            importCars();
            importParts();
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

    private void importParts() {

        Part brakeDiscs = new Part(
                "Brake discs",
                SubCategory.BRAKES,
                State.NEW,
                250.00,
                "BMW",
                "Selling new original brake discs",
                "BMW 535d 2008",
                (byte) 2,
                "brake1.jpg"
        );

        Part timingChain = new Part(
                "Timing chain",
                SubCategory.ENGINE,
                State.NEW,
                500.00,
                "FEBI",
                "New timing chain set",
                "BMW 340i 2018",
                (byte) 1,
                null
        );

        Part transmissionOil = new Part(
                "Transmission oil",
                SubCategory.TRANSMISSION,
                State.NEW,
                80.00,
                "VW",
                "OEM transmission oil",
                "VW Golf 2020",
                (byte) 4,
                null
        );

        Part brakePads = new Part(
                "Brake pads",
                SubCategory.BRAKES,
                State.NEW,
                120.00,
                "BREMBO",
                "Front brake pads",
                "Ford Focus 2021",
                (byte) 3,
                null
        );

        partRepository.saveAll(
                List.of(brakeDiscs, timingChain, transmissionOil, brakePads)
        );
    }

}
