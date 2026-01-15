package com.example.balkan_cars.parts;

import com.example.balkan_cars.user.User;
import com.example.balkan_cars.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PartService {

    private String PART_NOT_FOUND = "Part not found";

    private final PartRepository partsRepository;
    private final PartMapper partsMapper;
    private final UserRepository userRepository;
    
    public PartService(PartRepository partsRepository, PartMapper partsMapper, UserRepository userRepository) {
        this.partsRepository = partsRepository;
        this.partsMapper = partsMapper;
        this.userRepository = userRepository;
    }

    public List<PartDto> findAll() {
        return partsMapper.toDtos(partsRepository.findAll());
    }
    
    public List<PartDto> findBySeller(UUID sellerId) {
        User seller = userRepository.findByBusinessId(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Part> parts = partsRepository.findAllBySeller(seller);
        return parts.stream()
                .map(partsMapper::toDto)
                .collect(Collectors.toList());
    }

    public PartDto findById(UUID id) {
        return partsMapper.toDto(
                partsRepository.findByBusinessId(id)
                        .orElseThrow(() -> new EntityNotFoundException(PART_NOT_FOUND)));
    }

    @Transactional
    public PartDto create(PartDto dto) {
        Part part = partsMapper.toEntity(dto);
        
        // Set seller from sellerId in DTO
        if (dto.sellerId() != null) {
            User seller = userRepository.findByBusinessId(dto.sellerId())
                    .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
            part.setSeller(seller);
        }
        
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
