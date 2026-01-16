package com.example.balkan_cars.listing;

import com.example.balkan_cars.user.UserRepository;

import com.example.balkan_cars.vehicles.car.CarDto;
import com.example.balkan_cars.vehicles.car.CarMapper;
import com.example.balkan_cars.vehicles.car.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListingService {

    private static final String LISTING_NOT_FOUND = "Listing not found";
    private static final String CAR_NOT_FOUND = "Car not found";
    private static final String USER_NOT_FOUND = "Seller not found";

    private final ListingRepository listingRepository;
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final UserRepository userRepository;
    private final ListingMapper listingMapper;

    public ListingService(
            ListingRepository listingRepository,
            CarRepository carRepository,
            UserRepository userRepository,
            ListingMapper listingMapper,
            CarMapper carMapper
    ) {
        this.listingRepository = listingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.listingMapper = listingMapper;
        this.carMapper = carMapper;
    }

    public List<ListingDto> findAll() {
        return listingRepository.findAll().stream()
                .map(listingMapper::toDto)
                .collect(Collectors.toList());
    }

    public ListingDto findById(UUID id) {
        Listing listing = listingRepository.findByBusinessId(id)
                .orElseThrow(() -> new EntityNotFoundException(LISTING_NOT_FOUND));
        return listingMapper.toDto(listing);
    }

    public List<ListingDto> findCarsBySeller(UUID sellerId) {
        UUID userId = userRepository.findByBusinessId(sellerId).orElse(null).getId();

        List<Listing> listings = listingRepository.findAllBySellerId(userId);

        return listings.stream()
                .map(listingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ListingDto create(ListingDto dto) {
        Listing listing = listingMapper.toEntity(dto);

        listing.setCar(carRepository.findByBusinessId(dto.carId())
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND)));

        listing.setSeller(userRepository.getByBusinessId(dto.sellerId()));

        listing.setPhone(listing.getSeller().getPhone());
        if (listing.getExtras() == null) listing.setExtras(Set.of());

        return listingMapper.toDto(listingRepository.save(listing));
    }

    @Transactional
    public ListingDto update(ListingDto dto) {
        Listing existing = listingRepository.findByBusinessId(dto.id())
                .orElseThrow(() -> new EntityNotFoundException(LISTING_NOT_FOUND));

        listingMapper.updateListingFromDto(dto, existing);

        if (dto.carId() != null) {
            existing.setCar(carRepository.findByBusinessId(dto.carId())
                    .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND)));
        }

        if (dto.sellerId() != null) {
            existing.setSeller(userRepository.findByBusinessId(dto.sellerId())
                    .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND)));
        }

        return listingMapper.toDto(listingRepository.saveAndFlush(existing));
    }

    @Transactional
    public void delete(UUID id) {
        Listing listing = listingRepository.findByBusinessId(id)
                .orElseThrow(() -> new EntityNotFoundException(LISTING_NOT_FOUND));
        listingRepository.delete(listing);
    }
}
