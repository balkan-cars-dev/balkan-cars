package com.example.balkan_cars.wishlist;

import com.example.balkan_cars.parts.Part;
import com.example.balkan_cars.parts.PartDto;
import com.example.balkan_cars.parts.PartMapper;
import com.example.balkan_cars.parts.PartRepository;
import com.example.balkan_cars.user.User;
import com.example.balkan_cars.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartWishlistService {

    private final PartWishlistRepository partWishlistRepository;
    private final UserRepository userRepository;
    private final PartRepository partRepository;
    private final PartMapper partMapper;

    public List<PartDto> getUserFavorites(UUID userId) {
        User user = userRepository.findByBusinessId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return partWishlistRepository.findAllByUser(user).stream()
                .map(pw -> partMapper.toDto(pw.getPart()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addToFavorites(UUID userId, UUID partId) {
        User user = userRepository.findByBusinessId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Part part = partRepository.findByBusinessId(partId)
                .orElseThrow(() -> new RuntimeException("Part not found"));

        if (partWishlistRepository.findByUserAndPart(user, part).isEmpty()) {
            PartWishlist wishlist = new PartWishlist();
            wishlist.setUser(user);
            wishlist.setPart(part);
            partWishlistRepository.save(wishlist);
        }
    }

    @Transactional
    public void removeFromFavorites(UUID userId, UUID partId) {
        User user = userRepository.findByBusinessId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Part part = partRepository.findByBusinessId(partId)
                .orElseThrow(() -> new RuntimeException("Part not found"));

        partWishlistRepository.deleteByUserAndPart(user, part);
    }
}
