package com.example.balkan_cars.parts;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parts")
public class PartController {

    private final PartService partsService;

    @Autowired
    public PartController(PartService partsService) {
        this.partsService = partsService;
    }

    @GetMapping
    public List<PartDto> getAllParts() {
        return partsService.findAll();
    }

    @GetMapping("/{id}")
    public PartDto getPartById(@PathVariable String id) {
        return partsService.findById(UUID.fromString(id));
    }

    @GetMapping("/seller/{sellerId}")
    public List<PartDto> getPartsBySeller(@PathVariable String sellerId) {
        return partsService.findBySeller(UUID.fromString(sellerId));
    }

    @PostMapping
    public PartDto createPart(@Valid @RequestBody PartDto dto) {
        return partsService.create(dto);
    }

    @PutMapping("/{id}")
    public PartDto updatePart(@PathVariable String id, @Valid @RequestBody PartDto dto) {
        return partsService.updatePart(UUID.fromString(id), dto);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable String id) {
        partsService.deletePart(UUID.fromString(id));
    }
}
