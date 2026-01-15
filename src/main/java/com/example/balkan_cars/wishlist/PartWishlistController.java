package com.example.balkan_cars.wishlist;

import com.example.balkan_cars.parts.PartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/part-wishlist")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PartWishlistController {

    private final PartWishlistService partWishlistService;

    @GetMapping("/user/{userId}")
    public List<PartDto> getUserFavorites(@PathVariable UUID userId) {
        return partWishlistService.getUserFavorites(userId);
    }

    @PostMapping("/user/{userId}/part/{partId}")
    public void addToFavorites(@PathVariable UUID userId, @PathVariable UUID partId) {
        partWishlistService.addToFavorites(userId, partId);
    }

    @DeleteMapping("/user/{userId}/part/{partId}")
    public void removeFromFavorites(@PathVariable UUID userId, @PathVariable UUID partId) {
        partWishlistService.removeFromFavorites(userId, partId);
    }
}
