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

        Part brakeDiscs = new Part();
        brakeDiscs.setSubCategory(SubCategory.BRAKES);
        brakeDiscs.setState(State.NEW);
        brakeDiscs.setPrice(250.00);
        brakeDiscs.setManufacturer("BMW");
        brakeDiscs.setDescription("Selling new original brake discs");
        brakeDiscs.setCar("BMW 535d 2008");
        brakeDiscs.setQuantity((byte) 2);
        brakeDiscs.setImageUri("brake1.jpg");

        Part timingChain = new Part();
        timingChain.setSubCategory(SubCategory.ENGINE);
        timingChain.setState(State.NEW);
        timingChain.setPrice(500.00);
        timingChain.setManufacturer("FEBI");
        timingChain.setDescription("New timing chain set");
        timingChain.setCar("BMW 340i 2018");
        timingChain.setQuantity((byte) 1);

        Part transmissionOil = new Part();
        transmissionOil.setSubCategory(SubCategory.TRANSMISSION);
        transmissionOil.setState(State.NEW);
        transmissionOil.setPrice(80.00);
        transmissionOil.setManufacturer("VW");
        transmissionOil.setDescription("OEM transmission oil");
        transmissionOil.setCar("VW Golf 2020");
        transmissionOil.setQuantity((byte) 4);

        Part brakePads = new Part();
        brakePads.setSubCategory(SubCategory.BRAKES);
        brakePads.setState(State.NEW);
        brakePads.setPrice(120.00);
        brakePads.setManufacturer("BREMBO");
        brakePads.setDescription("Front brake pads");
        brakePads.setCar("Ford Focus 2021");
        brakePads.setQuantity((byte) 3);

        partRepository.saveAll(
                List.of(brakeDiscs, timingChain, transmissionOil, brakePads)
        );
    }

}
