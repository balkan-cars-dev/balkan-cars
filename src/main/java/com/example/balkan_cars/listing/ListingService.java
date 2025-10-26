package com.example.balkan_cars.listing;

import com.example.balkan_cars.user.UserRepository;
import com.example.balkan_cars.vehicles.car.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ListingService {

    private static final String LISTING_NOT_FOUND = "Listing not found";
    private static final String CAR_NOT_FOUND = "Car not found";
    private static final String USER_NOT_FOUND = "Seller not found";

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public ListingService(
            ListingRepository listingRepository,
            ListingMapper listingMapper,
            CarRepository carRepository,
            UserRepository userRepository
    ) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<ListingDto> findAll() {
        return listingMapper.toDtos(listingRepository.findAll());
    }

    public ListingDto findById(UUID id) {
        Listing listing = listingRepository.findByBusinessId(id)
                .orElseThrow(() -> new EntityNotFoundException(LISTING_NOT_FOUND));
        return listingMapper.toDto(listing);
    }

    @Transactional
    public ListingDto create(ListingDto listingDto) {
        Listing listing = listingMapper.toEntity(listingDto);

        listing.setCar(carRepository.findByBusinessId(listingDto.carId())
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND)));

        listing.setSeller(userRepository.findByBusinessId(listingDto.sellerId())
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND)));

        if (listing.getExtras() == null) {
            listing.setExtras(Set.of());
        }

        Listing saved = listingRepository.save(listing);
        return listingMapper.toDto(saved);
    }

    @Transactional
    public ListingDto update(ListingDto listingDto) {
        Listing listing = listingRepository.getByBusinessId(listingDto.id());
        if (listing == null)
            throw new EntityNotFoundException(LISTING_NOT_FOUND);

        listingMapper.updateListingFromDto(listingDto, listing);

        if (listingDto.carId() != null) {
            listing.setCar(carRepository.findByBusinessId(listingDto.carId())
                    .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND)));
        }

        if (listingDto.sellerId() != null) {
            listing.setSeller(userRepository.findByBusinessId(listingDto.sellerId())
                    .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND)));
        }

        if (listingDto.extras() != null) {
            listing.setExtras(listingDto.extras());
        }

        Listing updated = listingRepository.saveAndFlush(listing);

        return listingMapper.toDto(updated);
    }

    @Transactional
    public void delete(UUID id) {
        Listing listing = listingRepository.getByBusinessId(id);
        if (listing == null) throw new EntityNotFoundException(LISTING_NOT_FOUND);
        listingRepository.delete(listing);
    }
}
