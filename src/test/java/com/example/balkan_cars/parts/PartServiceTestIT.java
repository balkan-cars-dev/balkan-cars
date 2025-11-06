package com.example.balkan_cars.parts;

import com.example.balkan_cars.defined.cars.DefinedCarsDto;
import com.example.balkan_cars.enums.State;
import com.example.balkan_cars.enums.SubCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PartServiceTestIT {

    @Autowired
    private PartService serviceToTest;
    @Autowired
    private PartRepository partRepository;
    
    private final UUID businessId = UUID.randomUUID();
    
    @Test
    void createPart_shouldSaveTheListOfImagesAsSingleString() {
        PartDto result = serviceToTest.create(createPartDto());
        assertEquals(result.imageUriList(), List.of("ph.jpg", "ph2.jpg", "ph3.jpg"));
        Part part = partRepository.getByBusinessId(businessId);
        assertEquals("ph.jpg,ph2.jpg,ph3.jpg", part.getImageUri());
    }
    
    private PartDto createPartDto() {
        return new PartDto(businessId, "brake pads", new DefinedCarsDto(UUID.randomUUID(),
                "BMW" , "M3", (short)2020), SubCategory.BRAKES, State.NEW, 450.20,
                "bmw","selling brake pads",(byte)2 , List.of("ph.jpg", "ph2.jpg", "ph3.jpg"));
    }
}