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
        Part part = new Part("Brake discs", new DefinedCars("bmw", "535d", (short)2008),
                SubCategory.BRAKES, State.NEW, 250,"bmw", "Selling new brake discs",(byte)2 ,
                "test.jpg, test2.jpg");
        Part part2 = new Part("timing chain", new DefinedCars("bmw", "340i", (short)2018),
                SubCategory.ENGINE, State.NEW, 500, "febi",
                "Selling new timing chain",(byte)1 , null);
        Part part3 = new Part("Transmission oil", new DefinedCars("vw", "golf", (short)2020),
                SubCategory.TRANSMISSION, State.NEW, 250, "vw",
                "Selling new transmission oil",(byte)1 , null);
        Part part4 = new Part("Brake pads", new DefinedCars("ford", "focus", (short)2021), 
                SubCategory.BRAKES, State.NEW, 250, "brembo",
                "Selling new brake pads",(byte)2 , null);
        partRepository.saveAll(List.of(part, part2, part3, part4));
    }
}
