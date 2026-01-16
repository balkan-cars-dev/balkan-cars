package com.example.balkan_cars.parts;

import com.example.balkan_cars.defined.cars.DefinedCarsDto;
import com.example.balkan_cars.enums.State;
import com.example.balkan_cars.enums.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class PartServiceTestIT {

    @Autowired
    private PartService serviceToTest;
    @Autowired
    private PartRepository partRepository;

    private final UUID businessId = UUID.randomUUID();
    
}