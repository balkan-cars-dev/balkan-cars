package com.example.balkan_cars.listing;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListingService {

    private final String LISTING_NOT_FOUND = "Listing not found";

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;

    @Autowired
    public ListingService(ListingRepository listingRepository, ListingMapper listingMapper) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
    }

    public List<ListingDto> findAll() {
        return listingMapper.toDtos(listingRepository.findAll());
    }

    public ListingDto findById(UUID id) {
        return listingMapper.toDto(listingRepository.findByBusinessId(id)
                .orElseThrow(() -> new EntityNotFoundException(LISTING_NOT_FOUND)));
    }

    @Transactional
    public ListingDto create(ListingDto listingDto) {
        return listingMapper.toDto(listingRepository.save(listingMapper.toEntity(listingDto)));
    }

    @Transactional
    public ListingDto update(ListingDto listingDto) {
        Listing listing = listingRepository.getByBusinessId(listingDto.id());
        if (listing == null) throw new EntityNotFoundException(LISTING_NOT_FOUND);
        listingMapper.updateListingFromDto(listingDto, listing);
        return listingMapper.toDto(listingRepository.saveAndFlush(listing));
    }

    @Transactional
    public void delete(UUID id) {
        Listing listing = listingRepository.getByBusinessId(id);
        if (listing != null) listingRepository.delete(listing);
    }
}
