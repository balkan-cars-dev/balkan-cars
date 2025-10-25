package com.example.balkan_cars.wishlist;

import com.example.balkan_cars.listing.Listing;
import com.example.balkan_cars.listing.ListingRepository;
import com.example.balkan_cars.user.User;
import com.example.balkan_cars.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WishlistService {

    private final String WISHLIST_NOT_FOUND = "Wishlist entry not found";

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistService(WishlistRepository wishlistRepository,
                           UserRepository userRepository,
                           ListingRepository listingRepository,
                           WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.wishlistMapper = wishlistMapper;
    }

    public List<WishlistDto> findAll() {
        return wishlistMapper.toDtos(wishlistRepository.findAll());
    }

    public List<WishlistDto> findByUser(UUID userId) {
        return wishlistMapper.toDtos(wishlistRepository.findAllByUserId(userId));
    }

    @Transactional
    public WishlistDto addToWishlist(UUID userId, UUID listingId) {
        User user = userRepository.findByBusinessId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Listing listing = listingRepository.findByBusinessId(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));

        if (wishlistRepository.findByUserIdAndListingId(userId, listingId).isPresent()) {
            throw new IllegalArgumentException("Listing already in wishlist");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setListing(listing);

        return wishlistMapper.toDto(wishlistRepository.save(wishlist));
    }

    @Transactional
    public void removeFromWishlist(UUID userId, UUID listingId) {
        var wishlist = wishlistRepository.findByUserIdAndListingId(userId, listingId)
                .orElseThrow(() -> new EntityNotFoundException(WISHLIST_NOT_FOUND));
        wishlistRepository.delete(wishlist);
    }
}
