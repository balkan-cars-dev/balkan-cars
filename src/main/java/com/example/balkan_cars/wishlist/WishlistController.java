package com.example.balkan_cars.wishlist;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final com.example.balkan_cars.wishlist.WishlistService wishlistService;

    public WishlistController(com.example.balkan_cars.wishlist.WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<WishlistDto> getAll() {
        return wishlistService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<WishlistDto> getUser(@PathVariable String userId) {
        return wishlistService.findByUser(UUID.fromString(userId));
    }

    @PostMapping("/user/{userId}/listing/{listingId}")
    public WishlistDto addToWishlist(@PathVariable String userId, @PathVariable String listingId) {
        return wishlistService.addToWishlist(UUID.fromString(userId), UUID.fromString(listingId));
    }

    @DeleteMapping("/user/{userId}/listing/{listingId}")
    public void removeFromWishlist(@PathVariable String userId, @PathVariable String listingId) {
        wishlistService.removeFromWishlist(UUID.fromString(userId), UUID.fromString(listingId));
    }
}
