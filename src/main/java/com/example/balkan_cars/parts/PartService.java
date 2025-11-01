package com.example.balkan_cars.parts;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PartService {

    private String PART_NOT_FOUND = "Part not found";

    private final PartRepository partsRepository;
    private final PartMapper partsMapper;
    
    public PartService(PartRepository partsRepository, PartMapper partsMapper) {
        this.partsRepository = partsRepository;
        this.partsMapper = partsMapper;
    }

    public List<PartDto> findAll() {
        return partsMapper.toDtos(partsRepository.findAll());
    }

    public PartDto findById(UUID id) {
        return partsMapper.toDto(
                partsRepository.findByBusinessId(id)
                        .orElseThrow(() -> new EntityNotFoundException(PART_NOT_FOUND)));
    }

    @Transactional
    public PartDto create(PartDto dto) {
        Part part = partsMapper.toEntity(dto);
        Part saved = partsRepository.save(part);

        return partsMapper.toDto(saved);
    }

    @Transactional
    public PartDto updatePart(UUID id, PartDto dto) {
        Part existing = partsRepository.getByBusinessId(id);
        if (existing == null) throw new EntityNotFoundException(PART_NOT_FOUND);

        partsMapper.updatePartFromDto(dto, existing);

        return partsMapper.toDto(partsRepository.save(existing));
    }

    @Transactional
    public void deletePart(UUID id) {
        Part part = partsRepository.getByBusinessId(id);
        if (part != null) {
            partsRepository.delete(part);
        } else {
            throw new EntityNotFoundException(PART_NOT_FOUND);
        }
    }
}
